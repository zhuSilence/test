package kafka;

import java.util.List;
import java.util.Map;  
import java.util.Properties;  
  
import org.apache.kafka.clients.consumer.ConsumerConfig;  
import org.apache.kafka.clients.consumer.ConsumerRecord;  
import org.apache.kafka.clients.consumer.ConsumerRecords;  
import org.apache.kafka.clients.consumer.KafkaConsumer;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
public class KafkaConsumerTest {  
      
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerTest.class);  
      
    public static void main(String[] args) {  
        Properties properties = new Properties();  
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,   
                "172.20.132.140:9092,172.20.132.141:9092,172.20.132.142:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-20");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS, "1000");              
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");  
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY, "range");  
//      properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY, "roundrobin");  
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");    
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,   
                "org.apache.kafka.common.serialization.StringDeserializer");
          
        KafkaConsumer<Integer, String> kafkaConsumer = new KafkaConsumer<Integer, String>(properties);
        kafkaConsumer.subscribe("clientsRequests1");
//      kafkaConsumer.subscribe("*");  
        boolean isRunning = true;              
        while(isRunning) {  
            Map<String, ConsumerRecords<Integer, String>> results = kafkaConsumer.poll(100);
            if (null != results) {  
                for (Map.Entry<String, ConsumerRecords<Integer, String>> entry : results.entrySet()) {
                    ConsumerRecords<Integer, String> consumerRecords = entry.getValue();
                    List<ConsumerRecord<Integer, String>> records = consumerRecords.records();
                    for (int i = 0, len = records.size(); i < len; i++) {  
                        ConsumerRecord<Integer, String> consumerRecord = records.get(i);
                        System.out.println(consumerRecord.topic()+"------"+consumerRecord.partition());
                        try {  
                            System.out.println(consumerRecord.offset()+"****"+ new String(consumerRecord.value()));
                        } catch (Exception e) {  
                            e.printStackTrace();
                        }  
                    }  
                }  
            }  
        }  
          
        kafkaConsumer.close();    
          
    }  
  
} 