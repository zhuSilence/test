package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by zhuxiang on 2016/9/19.
 * Desc :
 */
public class FileLogUtils {
    public static Logger log = LogManager.getLogger(FileLogUtils.class);

    /**
     * 将输入流写入文件
     *
     * @param inputStream
     * @throws IOException
     */
    public static void writeLog(InputStream inputStream) throws IOException {
        String string = getStreamString(inputStream);
        log.info(string + ",");
    }

    /**
     * 将字符串写入文件
     *
     * @param message
     * @throws IOException
     */
    public static void writeLog(String message) throws IOException {
        log.info(message + ",");
    }

    /**
     * 将输入流转换为字符串
     *
     * @param tInputStream 输入流
     * @return
     */
    public static String getStreamString(InputStream tInputStream) {
        if (tInputStream != null) {
            try {
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine = new String("");
                while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将字符串转换为输入流
     *
     * @param sInputString 需要转换的字符串
     * @return
     */
    public static InputStream getStringStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String string = "<orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" />\n";
        for (int i = 0; i < 100000; i++) {
            InputStream inputStream = new ByteArrayInputStream(string.getBytes());
            FileLogUtils.writeLog(inputStream);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
