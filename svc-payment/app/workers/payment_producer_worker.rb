class PaymentProducerWorker
  include Sidekiq::Worker
  sidekiq_options retry: false

  def perform
    payment = Com::Lending::Proto::Payment.new(loanNumber: "2015CA169772974", paid: true, datePaid: "2015-12-23", amountInCents: 12151,
      sourceAccountNumber: "8601860", sourceObligationNumber: "11544267", sourcePaymentNumber: "2012323")

    paymentList = Com::Lending::Proto::PaymentList.new
    paymentList.payments << payment

    DeliveryBoy.deliver(Com::Lending::Proto::PaymentList.encode(paymentList), topic: "payment")
  end
end
