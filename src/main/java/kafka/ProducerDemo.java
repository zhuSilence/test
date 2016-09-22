package kafka;

import com.alibaba.fastjson.JSON;
import entity.ClientRequestsModel;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


public class ProducerDemo {
    public void sendObject() {
        int events = 20;
        // 设置配置属性
        Properties props = new Properties();
        props.put("metadata.broker.list", "172.20.135.95:9092,172.20.135.95:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        //props.put("partitioner.class", "kafka.Partitioner.TestPartitionerImpl");

        ProducerConfig config = new ProducerConfig(props);
        // 创建producer
        Producer<String, String> producer = new Producer<String, String>(config);
        // 产生并发送消息
        long start = System.currentTimeMillis();
        for (long i = 0; i < events; i++) {
            ClientRequestsModel model = new ClientRequestsModel();
            model.setMac("bcec23403141");
            model.setProvince(31);
            model.setCity(310100);
            model.setSchedule_id("S20160831001649");
            model.setOrder_id("O20160831000420");
            model.setAdspace_id("CCADTV10001");
            model.setMedia_type("image");
            model.setAgent_id(51);
            model.setCustomer_id(38);
            model.setWeekdaynum(3);
            model.setHoursnum(9);
            model.setCreate_time(new Date());

            //long runtime = new Date().getTime();
            String key = model.getAdspace_id();
            //String msg = runtime + ",www.example.com," + ip;
            //String jsonStr =
            String string = JSON.toJSONString(model);

            KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                    "clientsRequests", key, string);
            producer.send(data);
        }
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        producer.close();
    }

    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                producerDemo.sendObject();
                //System.out.println(1);
            }
        }, new Date(), 1000);
        //producerDemo.sendObject();

    }

}
