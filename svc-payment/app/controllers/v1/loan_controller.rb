require 'faraday'

module V1
  class LoanController < ApplicationController

    def lookupLoan
      conn = Faraday.new do |faraday|
        faraday.response :logger
        faraday.adapter :net_http
      end

      url = ENV['GET_LOAN_URL'] + params[:loan_number]

      response = conn.get do |req|
        req.url url
        req.headers['Accept'] = 'application/x-protobuf'
      end

      puts response.status
      puts response.body

      loan = Com::Lending::Proto::Loan.decode(response.body)

      respond_to do |format|
        format.json { render json: loan }
        format.protobuf { render plain: loan.to_proto, content_type: 'application/x-protobuf' }
      end
    end
  end
end

