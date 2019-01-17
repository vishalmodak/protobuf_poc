# frozen_string_literal: true

class PaymentConsumer < ApplicationConsumer
  subscribes_to "payment_intake", start_from_beginning: false

  def process(message)
    data_hash = JSON.parse(message.value)
    loan_num = JsonPath.on(data_hash, '$.loan.loanNumber')

    data = JSON.parse(Payments_by_loanNumber[loan_num.first])
    paymentList = Com::Lending::Proto::PaymentList.new
    data['payments'].map do |payment|
      # puts payment['loanNumber'] + "/" + payment['sourcePaymentNumber']

      paymentList.payments << Com::Lending::Proto::Payment.new(
          paid: payment['paid'],
          datePaid: !payment['datePaid'].nil? ? payment['datePaid'] : "",
          amountInCents: payment['amoutInCents'].to_i,
          loanNumber: payment['loanNumber'],
          sourceAccountNumber: payment['sourceAccountNumber'],
          sourcePaymentNumber: payment['sourcePaymentNumber'],
          sourceObligationNumber: payment['sourceObligationNumber']
      )
    end

    DeliveryBoy.deliver(paymentList, topic: "payment")
  end

end
