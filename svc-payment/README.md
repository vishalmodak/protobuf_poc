# Payment Service

This service uses Racecar to consume & send messages to Kafka

## Generating Ruby models from protos

```
bundle exec rake generate_proto
```

This extracts the `.proto` files from `domain-protos/build/distributions/domain-protos.tgz` and generates the corresponding Ruby models.
The Ruby model classes are created in `lib/gen/protos`.

## Running Consumers

In `app/consumer`, there are 2 classes -  LoanConsumer & PaymentProducer

LoanConsumer - Consumes loan messages from `loan` topic & prints to console
PaymentProducer - Consumer loan number from `payment_intake` topic & publishes the list of payments to `payment` topic

Each consumer or producer is run as a separate thread by Racecar

```
bundle exec racecar LoanConsumer

bundle exec racecar PaymentProducer
```
