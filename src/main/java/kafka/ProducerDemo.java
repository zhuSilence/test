package kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;


public class ProducerDemo {
    public static void main(String[] args) {
        int events = 5;
        // 设置配置属性
        Properties props = new Properties();
        props.put("metadata.broker.list", "172.20.135.95:9092,172.20.135.95:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        //props.put("partitioner.class", "kafka.PartitionerImpl");
        ProducerConfig config = new ProducerConfig(props);
        // 创建producer
        Producer<String, String> producer = new Producer<String, String>(config);
        // 产生并发送消息
        long start = System.currentTimeMillis();
        for (long i = 0; i < events; i++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + i;
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                    "testTopic", ip, msg);
            producer.send(data);
        }
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        producer.close();
    }
}
