package com.lending;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerde;
import com.lending.proto.Loan;
import com.lending.proto.PaymentList;

@Service
public class LoanAggregator {
    
    private static final Logger log = LoggerFactory.getLogger(LoanAggregator.class);


    @Autowired
    private StreamsConfig streamsConfig;
    
    private KafkaStreams streams;
    
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    
    @PostConstruct
    public void process() {
        StreamsBuilder builder = new StreamsBuilder();

        Serde<String> stringSerde = Serdes.String();
        
        Serde<Loan> loanSerde = new KafkaProtobufSerde<>(Loan.parser());
        Serde<PaymentList> paymentsSerde = new KafkaProtobufSerde<>(PaymentList.parser());
        
        
        KStream<String, Loan> loanStream = builder.stream("loan", Consumed.with(stringSerde, loanSerde));
      
        loanStream.groupBy((key, loan) -> loan.getSourceAccountNumber(), Serialized.with(stringSerde, loanSerde))
                    .windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1)))
                    .count(Materialized.as("account-store"))
                    .toStream()
                    .foreach(new ForeachAction<Windowed<String>, Long>() {
                        public void apply(Windowed<String> key, Long value) {
                            String start = df.format(new Date(key.window().start()));
                            String end = df.format(new Date(key.window().end()));
                            log.info(key.key() + " : " + start + "-" + end + " : " + value);
                        }
                    });

        //join 2 ktables
//        KTable<String, Loan> loanTable = builder.table("loan", Consumed.with(stringSerde, loanSerde));
//        KTable<String, PaymentList> paymentsTable = builder.table("payment", Consumed.with(stringSerde, paymentsSerde));
//        loanTable.join(paymentsTable, 
//                        (loan, payments) -> "loan: " + loan.getLoanNumber() + " has payments: " + payments.getPaymentsCount())
//                 .toStream()
//                 .foreach(new ForeachAction<String, String>() {
//                     public void apply(String key, String value) {
//                         log.info(value);
//                     }
//                 });
        
        //join kstream & ktable
//        KStream<String, Loan> loanStream = builder.stream("loan", Consumed.with(stringSerde, loanSerde));
//        KTable<String, PaymentList> paymentsTable = builder.table("payment", Consumed.with(stringSerde, paymentsSerde));
//        loanStream.leftJoin(paymentsTable, (loan, payments) -> "loan: " + loan.getLoanNumber() + " has payments: " + payments.getPaymentsCount())
//        .foreach(new ForeachAction<String, String>() {
//            public void apply(String key, String value) {
//                log.info(value);
//            }
//         });
        
        
        //join 2 kstreams
//        KStream<String, Loan> loanStream = builder.stream("loan", Consumed.with(stringSerde, loanSerde));
//        KStream<String, PaymentList> paymentsStream = builder.stream("payment", Consumed.with(stringSerde, paymentsSerde));
//        long joinWindowSizeMs = TimeUnit.MINUTES.toMillis(1); // 5 minutes
//        long windowRetentionTimeMs = TimeUnit.MINUTES.toMillis(5);
//        loanStream.join(paymentsStream, 
//                            (loan, payments) -> "loan: " + loan.getLoanNumber() + " has payments: " + ((PaymentList)payments).getPaymentsCount(), 
//                            JoinWindows.of(joinWindowSizeMs).until(windowRetentionTimeMs),
//                            Joined.with(stringSerde, loanSerde, paymentsSerde))
//                  .foreach(new ForeachAction<String, String>() {
//                          public void apply(String key, String value) {
//                              log.info(value);
//                          }
//                  });
        
        streams = new KafkaStreams(builder.build(), streamsConfig);

        streams.cleanUp();
        streams.start();
        
        log.info("Stream started....");

    }
    
    @PreDestroy
    public void closeStream() {
        streams.close();
    }
}
