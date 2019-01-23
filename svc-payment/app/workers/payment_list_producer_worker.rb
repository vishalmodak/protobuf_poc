class PaymentListProducerWorker
  include Sidekiq::Worker
  sidekiq_options retry: false

  def perform
    payments = Payments_by_loanNumber["2015CA169772974"]
    data = JSON.parse(payments)
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

    DeliveryBoy.deliver(Com::Lending::Proto::PaymentList.encode(paymentList), topic: "payment")
  end
end
