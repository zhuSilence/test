package kafka2;

public interface KafkaProperties {
    final static String zkConnect = "172.20.135.95:2181,172.20.135.95:2182";
    final static String groupId = "G_1";
    final static String topic = "Tp_1";
    final static String kafkaServerURL = "172.20.135.95:9092,172.20.135.95:9093";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "SimpleConsumerDemoClient";
}