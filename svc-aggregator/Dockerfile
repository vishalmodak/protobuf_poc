FROM golang:1.10-stretch as builder
RUN apt-get install git
ENV appDir=/go/src/protobuf_poc/svc-aggregator
RUN mkdir -p $appDir
WORKDIR $appDir
ADD . $appDir
RUN go get .
RUN go build .
CMD ["/go/src/protobuf_poc/svc-aggregator/svc-aggregator"]

FROM golang:1.10-stretch
ENV TZ Etc/GMT
RUN apt-get install tzdata
#RUN apk --no-cache add ca-certificates
WORKDIR /root
COPY --from=builder /go/src/protobuf_poc/svc-aggregator/svc-aggregator .
CMD ["./svc-aggregator"]
EXPOSE 9000
