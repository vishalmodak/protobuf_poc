# frozen_string_literal: true

class PaymentProducer < ApplicationConsumer
  subscribes_to ENV['PAYMENT_TOPIC_CONSUME'], start_from_beginning: false

  def process(message)
    puts message.value
    data_hash = JSON.parse(message.value)
    loan_num = JsonPath.on(data_hash, '$.loan.loanNumber')

    data = JSON.parse(Payments_by_loanNumber[loan_num.first])
    paymentList = Com::Lending::Proto::PaymentList.new
    data['payments'].map do |payment|
      # puts payment['loanNumber'] + "/" + payment['sourcePaymentNumber']

      paymentList.payments << Com::Lending::Proto::Payment.new(
          paid: payment['paid'],
          datePaid: !payment['datePaid'].nil? ? payment['datePaid'] : "",
          amountInCents: payment['amountInCents'].to_i,
          loanNumber: payment['loanNumber'],
          sourceAccountNumber: payment['sourceAccountNumber'],
          sourcePaymentNumber: payment['sourcePaymentNumber'],
          sourceObligationNumber: payment['sourceObligationNumber']
      )
    end
    puts paymentList
    DeliveryBoy.deliver(Com::Lending::Proto::PaymentList.encode(paymentList), topic: ENV['PAYMENT_TOPIC_PRODUCE'], key: loan_num.first)
  end

end
