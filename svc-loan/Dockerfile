FROM gradle:5.1-jdk as builder
MAINTAINER vishal
USER root
ENV appDir=/home/gradle/app
ENV protosDir=/home/gradle/domain-protos/build/distributions/
RUN mkdir -p $protosDir
COPY --from=domain_protos /home/gradle/app/build/distributions/domain-protos.tgz $protosDir/
RUN mkdir -p $appDir
WORKDIR $appDir
ADD . $appDir
RUN ./gradlew --refresh-dependencies
RUN gradle clean build -x test
#--------------------------------------------------
FROM java:8-alpine
ENV KAFKA_BROKER_URL=localhost:9092 \
    LOAN_CONSUME_TOPIC=loan_intake \
    LOAN_PUBLISH_TOPIC=loan \
    PAYMENT_CONSUME_TOPIC=payment \
    PAYMENT_SERVICE_HOST=http://localhost:3000 \
    STATSD_SERVER_HOST=localhost

COPY --from=builder /home/gradle/app/build/libs/svc-loan.jar .
COPY --from=builder /home/gradle/app/data ./data
CMD ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.kafka.bootstrap-servers=${KAFKA_BROKER_URL}", \
            "-Dloan.topic.consume=${LOAN_CONSUME_TOPIC}", \
            "-Dloan.topic.produce=${LOAN_PUBLISH_TOPIC}", \
            "-Dpayment.topic.produce=${PAYMENT_CONSUME_TOPIC}", \
            "-Dservice.payments.uri=${PAYMENT_SERVICE_HOST}", \
            "-Dmanagement.metrics.export.statsd.host=${STATSD_SERVER_HOST}", \
            "-jar", \
            "svc-loan.jar"]
EXPOSE 8080
