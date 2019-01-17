FROM java:8-alpine
MAINTAINER vishal

ENV KAFKA_BROKER_URL=localhost:9092 \
    LOAN_CONSUME_TOPIC=loan_intake \
    LOAN_PUBLISH_TOPIC=loan \
    PAYMENT_CONSUME_TOPIC=payment \
    appDir=/usr/src/app

RUN mkdir -p $appDir
WORKDIR $appDir

ADD build/libs/svc-loan.jar .
ADD data ./data

CMD ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.kafka.bootstrap-servers=${KAFKA_BROKER_URL}", \
            "-Dkafka.topic.consume=${KAFKA_BROKER_URL}", \
            "-Dkafka.topic.produce=${KAFKA_BROKER_URL}", \            
            "-jar", \
            "svc-loan.jar"]

EXPOSE 8080
