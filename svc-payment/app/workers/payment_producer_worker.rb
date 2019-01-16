class PaymentProducerWorker
  include Sidekiq::Worker
  sidekiq_options retry: false

  def perform
    payment = Com::Lending::Proto::Payment.new(loanNumber: "2015CA169772974", paid: true, datePaid: "2015-12-23", amoutInCents: 12151,
      sourceAccountNumber: "8601860", sourceObligationNumber: "11544267", sourcePaymentNumber: "2012323")

    DeliveryBoy.deliver(Com::Lending::Proto::Payment.encode(payment), topic: "payment")
  end
end
