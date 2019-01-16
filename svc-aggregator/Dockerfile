FROM golang:1.9-alpine as builder
RUN apk add --update glide git
ENV appDir=/go/src/protobuf_poc/svc-aggregator
RUN mkdir -p $appDir
WORKDIR $appDir
ADD . $appDir
RUN go get .
RUN go build .
CMD ["/go/src/protobuf_poc/svc-aggregator/svc-aggregator"]

FROM alpine:latest
ENV TZ Etc/GMT
RUN apk add --update tzdata
RUN apk --no-cache add ca-certificates
WORKDIR /root
COPY --from=builder /go/src/protobuf_poc/svc-aggregator/svc-aggregator .
CMD ["./svc-aggregator"]
EXPOSE 9000
