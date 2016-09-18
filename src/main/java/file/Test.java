package file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhuxiang on 2016/9/18.
 * Desc :
 */
public class Test implements Runnable {

    public void run() {

        FileUtils fileUtils = new FileUtils();
        for (int i = 0; i < 60000; i++) {
            String string = "<orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" />\n";
            try {
                InputStream inputStream = new ByteArrayInputStream(string.getBytes());
                String saveFile = "D:\\软件\\java\\logs\\2016-09-17.log";
                File file = new File(saveFile);
                fileUtils.writeFile(file, inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Test test = new Test();
        long start = System.currentTimeMillis();
        test.run();
        System.out.println(System.currentTimeMillis() - start);
    }
}
