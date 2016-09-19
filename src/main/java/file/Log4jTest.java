package file;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jTest {

    public static Logger log = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        //log.info("订单编号:" + "");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            log.info(i + "<orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" />");
        }
        System.out.println(System.currentTimeMillis() - start);
        //log.error("发送者:" + "");

        //log.debug("我是logger1，debug");
    }
}  