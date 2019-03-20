FROM golang:1.12-stretch as builder
RUN apt-get update && apt-get -y install git protobuf-compiler unzip
# Install Protoc 3.7
RUN curl -OL https://github.com/protocolbuffers/protobuf/releases/download/v3.7.0/protoc-3.7.0-linux-x86_64.zip
# Unzip
RUN unzip protoc-3.7.0-linux-x86_64.zip -d protoc3
# Move protoc to /usr/local/bin/
RUN mv protoc3/bin/* /usr/local/bin/
# Move protoc3/include to /usr/local/include/
RUN mv protoc3/include/* /usr/local/include/

ENV appDir=/go/src/protobuf_poc/svc-aggregator
RUN mkdir -p $appDir
RUN mkdir -p $appDir/extracted-protos
RUN mkdir -p $appDir/generated-protos
WORKDIR $appDir
ADD . $appDir

COPY --from=domain_protos /home/gradle/app/build/distributions/domain-protos.tgz .
RUN tar -C extracted-protos --strip-components 1 -xvf domain-protos.tgz
RUN go get -u github.com/golang/protobuf/protoc-gen-go
RUN protoc extracted-protos/*.proto --go_out=generated-protos -I extracted-protos
RUN go get .
RUN go build .
#---------------------------------------------
FROM golang:1.12-stretch
ENV TZ Etc/GMT
RUN apt-get install tzdata
WORKDIR /root
COPY --from=builder /go/src/protobuf_poc/svc-aggregator/svc-aggregator .
CMD ["./svc-aggregator"]
EXPOSE 9000
