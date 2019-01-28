FROM golang:1.10-stretch as builder
RUN apt-get update && apt-get -y install git golang-goprotobuf-dev
#libprotobuf-dev protobuf-compiler
# Install protoc 3.6.0.
# RUN mkdir -p /usr/src/protoc/ \
#   && curl --location https://github.com/google/protobuf/releases/download/v3.6.0/protoc-3.6.0-linux-x86_64.zip > /usr/src/protoc/protoc-3.6.0.zip \
#   && cd /usr/src/protoc/ \
#   && unzip protoc-3.6.0.zip \
#   && rm protoc-3.6.0.zip \
#   && ln -s /usr/src/protoc/bin/protoc /usr/local/bin/protoc


ENV appDir=/go/src/protobuf_poc/svc-aggregator
RUN mkdir -p $appDir
RUN mkdir -p $appDir/extracted-protos
RUN mkdir -p $appDir/generated-protos
WORKDIR $appDir
ADD . $appDir

# RUN go get -u github.com/golang/protobuf/{proto,protoc-gen-go}

COPY --from=domain_protos /home/gradle/app/build/distributions/domain-protos.tgz .
RUN tar -C extracted-protos --strip-components 1 -xvf domain-protos.tgz
RUN protoc extracted-protos/*.proto --go_out=generated-protos -I extracted-protos
RUN go get .
RUN go build .
#---------------------------------------------
FROM golang:1.10-stretch
ENV TZ Etc/GMT
RUN apt-get install tzdata
#RUN apk --no-cache add ca-certificates
WORKDIR /root
COPY --from=builder /go/src/protobuf_poc/svc-aggregator/svc-aggregator .
CMD ["./svc-aggregator"]
EXPOSE 9000
