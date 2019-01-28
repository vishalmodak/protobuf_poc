DeliveryBoy.configure do |config|
  config.client_id = "svc-payment"
  config.brokers = ENV['KAFKA_BROKER_ADDR']
end
