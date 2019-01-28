# svc-aggregator

An aggregator service in Go consuming Loan, Payment & PaymentList protobuf messages

## To extract protos

```
tar -C extracted-protos --strip-components 1 -xvf ../domain-protos/build/distributions/domain-protos.tgz
```
Creates a dir `extracted-protos` with `*.proto` files

## To generate GO structs from protos

```
protoc extracted-protos/*.proto --go_out=generated-protos -I extracted-protos
```
Creates a dir `generated-protos` with generated Golang structs from Proto definitions
