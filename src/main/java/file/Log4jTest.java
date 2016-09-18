package file;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jTest {

    private static Logger log = LogManager.getLogger(Log4jTest.class.getName());

    public static void add() {
        log.info("订单编号:" + "");
        log.info("发送者:" + "");
        log.info("日期:" + "" + "\n");
    }
}  