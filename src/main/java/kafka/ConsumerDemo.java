package kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 详细可以参考：https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+Example
 */
public class ConsumerDemo {
    private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;

    public ConsumerDemo(String a_zookeeper, String a_groupId, String a_topic) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper, a_groupId));
        this.topic = a_topic;
    }

    public void shutdown() {
        if (consumer != null)
            consumer.shutdown();
        if (executor != null)
            executor.shutdown();
    }

    public void run(int numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        executor = Executors.newFixedThreadPool(numThreads);

        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerMsgTask(stream, threadNumber));

            threadNumber++;
        }
    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper,
                                                       String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");

        return new ConsumerConfig(props);
    }

    public static void main(String[] arg) {
        String[] args = {"172.20.132.140:2181,172.20.132.141:2181,172.20.132.142:2181", "group-20", "clientsRequests1", "4"};
        //String[] args = {"172.20.135.95:2181,172.20.135.95:2182", "group-1", "clientsRequests", "4"};
        String zooKeeper = args[0];
        String groupId = args[1];
        String topic = args[2];
        int threads = Integer.parseInt(args[3]);

        System.out.println(groupId);
        ConsumerDemo demo = new ConsumerDemo(zooKeeper, groupId, topic);
        demo.run(threads);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ie) {

        }
        demo.shutdown();


       /* KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties());
        String topic = "clientsRequests3";
        int partition = 3;
        TopicPartition topicPartition = new TopicPartition(topic, partition);

        List<String> topicList = new ArrayList<>();
        topicList.add(topic);
        //不指定分支方式
        consumer.subscribe(topicList);

        //指定消费分支方式
      *//*  List<TopicPartition> topicPartitionList = new ArrayList<>();
        topicPartitionList.add(topicPartition);
        consumer.assign(topicPartitionList);
*//*
        try {
            while (true) {
                System.out.println("定时执行consumer、、、");
                long start = System.currentTimeMillis();
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                int length = 0;
                for (ConsumerRecord<String, String> record : records) {
                    ClientRequestsModel model = (ClientRequestsModel) JsonUtils.json2Object(record.value(), ClientRequestsModel.class);
                    System.out.println("**********" + record.value());
                    //saveResult(model);
                    length++;
                }
                System.out.println(length);
                System.out.println("本次耗时：" + (System.currentTimeMillis() - start));
                //手动提交offset
                consumer.commitSync();
            }
        } finally {
            consumer.close();
        }*/


    }

    /*public static Properties consumerProperties() {
        //OffsetManager offsetManager = new OffsetManager();
        Properties props = new Properties();
        //props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.135.95:9092,172.20.135.95:9093,172.20.135.95:9094");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.132.140:9092,172.20.132.141:9092,172.20.132.142:9092");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "group-3");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }*/
}
    /*public void processMessage(Map<String, Map<Integer, String>> msgs) {
        long start = System.currentTimeMillis();
        for (Map.Entry<String, Map<Integer, String>> entry : msgs.entrySet()) {
            LinkedHashMap<Integer, String> messages = (LinkedHashMap<Integer, String>) entry.getValue();
            Collection<String> values = messages.values();
            for (Iterator<String> iterator = values.iterator(); iterator.hasNext(); ) {
                String message = "[" + iterator.next() + "]";
                List<ClientRequestsModel> modelList = JSONArray.parseArray(message, ClientRequestsModel.class);
                if (modelList != null && modelList.size() > 0) {
                    for (int i = 0, size = modelList.size(); i < size; i++) {
                        System.out.println("************saveResult****" + i);
                        ClientRequestsModel model = modelList.get(i);
                        saveResult(model);
                    }
                }
            }
        }
        time += (System.currentTimeMillis() - start);
        System.out.println("==========总共花费时长：" + time);
    }*/