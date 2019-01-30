require 'faraday'

module V1
  class LoanController < ApplicationController

    def lookupLoan
      metricName = "lookupLoan"
      if request.headers["Accept"] == "application/x-protobuf"
        metricName += ".protobuf"
      elsif request.headers["Accept"] == "application/json"
        metricName += ".json"
      end
      puts metricName
      StatsD.measure(metricName) do
        conn = Faraday.new do |faraday|
          faraday.response :logger
          faraday.adapter :net_http
        end

        url = ENV['GET_LOAN_URL'] + params[:loan_number]

        response = conn.get do |req|
          req.url url
          req.headers['Accept'] = request.headers['Accept']
        end

        if request.headers["Accept"] == "application/x-protobuf"
          loan = Com::Lending::Proto::Loan.decode(response.body)
        elsif request.headers["Accept"] == "application/json"
          loan = response.body
        end

        respond_to do |format|
          format.json { render json: loan }
          format.protobuf { render plain: loan.to_proto, content_type: 'application/x-protobuf' }
        end
      end
    end
  end
end

