package main

import (
	"flag"
	"github.com/Shopify/sarama"
	"github.com/golang/protobuf/proto"
	log "github.com/sirupsen/logrus"
	"github.com/subosito/gotenv"
	lg "log"
	"net/http"
	"os"
	"protobuf_poc/svc-aggregator/generated-protos"
	"strings"
)

var (
	loanTopic    string
	paymentTopic string
	brokerURI    string
	logLevel     string
	logger       *log.Logger
	config       *sarama.Config
	httpAddr     string
	loanURL      string
)

func init() {
	gotenv.Load(".env")

	logger = log.New()
	logger.SetLevel(log.DebugLevel)

	loanTopic = os.Getenv("LOAN_TOPIC_NAME")
	logger.Debug("LOAN_TOPIC_NAME: ", os.Getenv("LOAN_TOPIC_NAME"))
	paymentTopic = os.Getenv("PAYMENT_TOPIC_NAME")
	logger.Debug("PAYMENT_TOPIC_NAME: ", paymentTopic)
	brokerURI = os.Getenv("KAFKA_BROKER_URI")
	logger.Debug("KAFKA_BROKER_URI: ", brokerURI)
	httpAddr = os.Getenv("ADDR")
	logger.Debug("ADDR: ", httpAddr)
	loanURL = os.Getenv("GETLOAN_URL")
	logger.Debug("GETLOAN_URL: ", loanURL)

	config = sarama.NewConfig()
	// config.Producer.Partitioner = sarama.NewManualPartitioner
	config.Consumer.Return.Errors = true
	//verbose debugging (comment this line to disabled verbose sarama logging)
	sarama.Logger = lg.New(os.Stdout, "[sarama] ", lg.LstdFlags)
}

func main() {
	flag.Parse()

	var producerURL []string
	if strings.Contains(brokerURI, ",") {
		producerURL = strings.Split(brokerURI, ",")
	} else {
		producerURL = append(producerURL, brokerURI)
	}

	// go startLoanConsumer(config, loanTopic, producerURL)
	//
	// go startPaymentConsumer(config, paymentTopic, producerURL)

	// Start the server
	logger.Printf("Starting server on :%s", httpAddr)
	err := http.ListenAndServe(httpAddr, setupRoutes())
	if err != nil {
		logger.Fatalf("Failure starting server: %s", err)
	}
	logger.Info("Aggregator Service started....")
}

func startPaymentConsumer(config *sarama.Config, topic string, producerURL []string) {

	consumer, err := sarama.NewConsumer(producerURL, config)
	if err != nil {
		logger.Fatalf("Failed to start consumer: %s", err)
	}

	defer func() {
		if err := consumer.Close(); err != nil {
			log.Fatalln(err)
		}
	}()

	partitions, err := consumer.Partitions(topic)
	if err != nil {
		logger.Fatalf("Failed to get partitions: %s", err)
	}
	logger.Printf("%d partitions for topic: %s", len(partitions), topic)

	partitionConsumer, err := consumer.ConsumePartition(topic, 0, sarama.OffsetNewest)
	if err != nil {
		logger.Errorf("Failed to consume partition: %s", err)
	}

	defer func() {
		if err := partitionConsumer.Close(); err != nil {
			log.Fatalln(err)
		}
	}()
	logger.Printf("Listening to topic=%s ...", topic)

	for m := range partitionConsumer.Messages() {
		readPaymentProto(m.Value)
	}
}

func readPaymentProto(encodedMsg []byte) {
	paymentList := new(com_lending_proto.PaymentList)
	err := proto.Unmarshal(encodedMsg, paymentList)
	if err != nil {
		logger.Errorf("unmarshaling error: %s", err)
	}
	logger.Println(proto.MarshalTextString(paymentList))
}

func startLoanConsumer(config *sarama.Config, topic string, producerURL []string) {

	consumer, err := sarama.NewConsumer(producerURL, config)
	if err != nil {
		logger.Fatalf("Failed to start consumer: %s", err)
	}

	defer func() {
		if err := consumer.Close(); err != nil {
			log.Fatalln(err)
		}
	}()

	partitions, err := consumer.Partitions(topic)
	if err != nil {
		logger.Fatalf("Failed to get partitions: %s", err)
	}
	logger.Printf("%d partitions for topic: %s", len(partitions), topic)

	partitionConsumer, err := consumer.ConsumePartition(topic, 0, sarama.OffsetNewest)
	if err != nil {
		logger.Errorf("Failed to consume partition: %s", err)
	}

	defer func() {
		if err := partitionConsumer.Close(); err != nil {
			log.Fatalln(err)
		}
	}()
	logger.Printf("Listening to topic=%s ...", topic)

	for m := range partitionConsumer.Messages() {
		readLoanProto(m.Value)
	}
}

func readLoanProto(encodedMsg []byte) {
	loan := new(com_lending_proto.Loan)
	err := proto.Unmarshal(encodedMsg, loan)
	if err != nil {
		logger.Errorf("unmarshaling error: %s", err)
	}
	logger.Println(proto.MarshalTextString(loan))
}
