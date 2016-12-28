package kafka;

import com.alibaba.fastjson.JSON;
import com.origin.eurybia.utils.DateUtils;
import entity.ClientRequestsModel;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;


public class ProducerDemo {

    public void sendObject() {
        int events = 1;
        // 设置配置属性
        Properties props = new Properties();
        //props.put("metadata.broker.list", "172.20.135.95:9092,172.20.135.95:9093,172.20.135.95:9094");
        props.put("metadata.broker.list", "172.20.132.140:9092,172.20.132.141:9092,172.20.132.142:9092");
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

           /* TvEntity tvEntity = new TvEntity();
            tvEntity.setMac("1004_00301bba02dc");
            tvEntity.setBrand("PHILIPS");
            tvEntity.setIp("127.0.0.1");
            tvEntity.setModel("55PUF6401");
            tvEntity.setChip("");
            tvEntity.setSize("55");
            tvEntity.setResolution("1920*1080");
            tvEntity.setCreate_time(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));*/

            ClientRequestsModel model = new ClientRequestsModel();
            model.setMac("bcec23a9cf2f");
            model.setBrand("COOCAA");
            model.setModel("U");
            if (i <= 1) model.setAdspace_id("CCADTV10001");
            if (i == 2) model.setAdspace_id("CCADTV10002");
            if (i == 3) model.setAdspace_id("CCADTV10003");
            if (i == 4) model.setAdspace_id("CCADTV10004");
            if (i == 5) model.setAdspace_id("CCADTV10005");
            if (i == 6) model.setAdspace_id("CCADTV10006");
            if (i >= 7) model.setAdspace_id("CCADTV10007");

            model.setAgent_id(51);
            model.setCustomer_id(38);

            model.setIp("202.105.137.34");
            model.setProvince_name("广东省");
            model.setCity_name("深圳市");
            model.setTotal_traffic(3000);

            model.setSchedule_id("S20161011001689");
            model.setOrder_id("O20160831000421");

            model.setMedia_type("image");

            model.setWeekdaynum(3);
            model.setHoursnum("9");
            model.setCreate_time(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

            //long runtime = new Date().getTime();
            String key = model.getAdspace_id();
            //String key = tvEntity.getIp();
            //String msg = runtime + ",www.example.com," + ip;
            //String jsonStr =
            String string = JSON.toJSONString(model);
            System.out.println(string);
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                    "adPv_CCADTV10007", key, string);
            producer.send(data);
        }
        System.out.println("本次生产 " + events + " 条数据");
        System.out.println("产生并发送消息耗时:" + (System.currentTimeMillis() - start));
        producer.close();
    }

    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();
/*        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                producerDemo.sendObject();

            }

        }, new Date(), 2000);*/
        producerDemo.sendObject();


    }

}
