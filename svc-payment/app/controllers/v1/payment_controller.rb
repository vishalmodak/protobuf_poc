module V1
  class PaymentController < ApplicationController

    # GET /payments/:payment_number
    def index

    end

    # GET /payments/:loan_number
    def lookup_payments_loan_number
      payments = Payments_by_loanNumber[params[:loan_number]]
      data = JSON.parse(payments)
      paymentList = Com::Lending::Proto::PaymentList.new

      paymentsArray = []

      data['payments'].map do |payment|
        # puts payment['loanNumber'] + "/" + payment['sourcePaymentNumber']

        paymentsArray << Com::Lending::Proto::Payment.new(
            paid: payment['paid'],
            datePaid: !payment['datePaid'].nil? ? payment['datePaid'] : "",
            amountInCents: payment['amountInCents'].to_i,
            loanNumber: payment['loanNumber'],
            sourceAccountNumber: payment['sourceAccountNumber'],
            sourcePaymentNumber: payment['sourcePaymentNumber'],
            sourceObligationNumber: payment['sourceObligationNumber']
        )
      end

      dup = params[:dup]? params[:dup].to_i : 1

      dup.times do
        paymentList.payments += paymentsArray
      end

      respond_to do |format|
        format.json { render json: paymentList }
        format.protobuf { render plain: paymentList.to_proto, content_type: 'application/x-protobuf' }
      end

    end

  end
end