package com.lending.loan.kafka;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerializer;
import com.googlecode.protobuf.format.JsonFormat;
import com.lending.proto.Loan;

@Service
public class LoanPublisher {
    
    private static final Logger log = LoggerFactory.getLogger(LoanPublisher.class);

    @Autowired
    private KafkaTemplate<String, Loan> kafkaTemplate;
    
    @Value("${loan.topic.produce}")
    private String producerTopic;

    public void send(String payload) {
        Loan.Builder loanBuilder = Loan.newBuilder();
        try {
            new JsonFormat().merge(new ByteArrayInputStream(payload.getBytes()), loanBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Loan loan = loanBuilder.build();
        log.info("sending loan='{}' to topic='{}'", loan, producerTopic);
        
        Message<Loan> loanMsg = MessageBuilder
                .withPayload(loan)
                .setHeader(KafkaHeaders.TOPIC, producerTopic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, loan.getLoanNumber())
                .build();
        
        kafkaTemplate.send(loanMsg);
//        kafkaTemplate.send(producerTopic, loan);
    }

}
