package utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhuxiang on 2016/9/18.
 * Desc :
 */
public class FileTest implements Runnable {

    public void run() {

        FileUtils fileUtils = new FileUtils();
        for (int i = 0; i < 100000; i++) {
            String string = i + "<orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" />\n";
            try {
                BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(string.getBytes()));
                fileUtils = new FileUtils(inputStream);
                fileUtils.writeFileEveryDay("D:\\软件\\java\\logs\\");

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        try {
            fileUtils.closeInputAndOut();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        FileTest fileTest = new FileTest();
        long start = System.currentTimeMillis();
        fileTest.run();
        System.out.println(System.currentTimeMillis() - start);
    }
}
