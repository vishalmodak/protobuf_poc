# frozen_string_literal: true

class LoanConsumer < ApplicationConsumer
  subscribes_to ENV['LOAN_TOPIC_CONSUME'], start_from_beginning: false

  def process(message)
    puts message.value
    loan = Com::Lending::Proto::Loan.decode(message.value)
    puts loan.inspect
  end

end
