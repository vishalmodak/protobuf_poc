package com.lending;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerde;
import com.lending.proto.Loan;

@SpringBootApplication
@EnableKafka
//@EnableKafkaStreams
//@EnableBinding(KafkaStreamsProcessor.class)
public class SvcStreamAggregatorApplication {
    
    private static final Logger log = LoggerFactory.getLogger(SvcStreamAggregatorApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SvcStreamAggregatorApplication.class, args);
	}

//    @StreamListener
    public void process(@Input("loan") KStream<String, Loan> loanStream, @Input("payment") KStream<String, Loan> paymentStream) {
        log.info("Loan Stream: " + loanStream);
    }
    
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
      Map<String, Object> streamProperties = new HashMap<>();
      streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "svc-stream-aggregator");
      streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
      streamProperties.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
      streamProperties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");
      return new StreamsConfig(streamProperties);
    }
}
