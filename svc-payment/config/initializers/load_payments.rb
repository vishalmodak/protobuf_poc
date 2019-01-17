require 'jsonpath'

Payments_by_loanNumber = Hash.new

Dir.glob("#{Rails.root}/data/**/*").each do |file|
  # Rails.logger.info file
  # file = File.join(Rails.root, 'data', '2015CA169772974.json')
  contents = File.read(file)
  data_hash = JSON.parse(contents)
  # Rails.logger.info data_hash
  loan_num = JsonPath.on(data_hash, '$.payments[0].loanNumber')
  Payments_by_loanNumber[loan_num.first] = contents
end