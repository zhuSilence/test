package kafka2;

public class KafkaConsumerProducerDemo {

    public static void main(String[] args) {
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();
        System.out.println("**********************");
        KafkaConsumer1 consumerThread = new KafkaConsumer1(KafkaProperties.topic);
        consumerThread.start();
    }
}