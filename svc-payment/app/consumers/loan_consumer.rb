# frozen_string_literal: true

class LoanConsumer < ApplicationConsumer
  subscribes_to "loan", start_from_beginning: false

  def process(message)
    puts message.value
    loan = Com::Lending::Proto::Loan.decode(message.value)
    puts loan.inspect
  end

end
