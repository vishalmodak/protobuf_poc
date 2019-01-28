FROM gradle:5.1-alpine as protos_builder
MAINTAINER vishal

ENV appDir=/home/gradle/app

RUN mkdir -p $appDir
WORKDIR $appDir

ADD . $appDir
RUN gradle clean build
