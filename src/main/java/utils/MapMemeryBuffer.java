package utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class MapMemeryBuffer {
    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
        byte[] bbb = new byte[14 * 1024 * 1024];
        //FileInputStream fis = new FileInputStream("e://data/other/UltraEdit_17.00.0.1035_SC.exe");
        String string = "<orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" /><orderEntry type=\"library\" name=\"Maven: joda-time:joda-time:2.9.3\" level=\"project\" />\n";
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(string.getBytes()));
        FileOutputStream fos = new FileOutputStream("D:\\软件\\java\\logs\\2016-09-17.log");
        FileInputStream fis = (FileInputStream)inputStream;
        FileChannel fc = fis.getChannel();
        long timeStar = System.currentTimeMillis();// 得到当前的时间
        //fc.read(byteBuf);// 1 读取
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size() / 1024);
        long timeEnd = System.currentTimeMillis();// 得到当前的时间
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
        timeStar = System.currentTimeMillis();
        //fos.write(bbb);//2.写入
        mbb.flip();
        timeEnd = System.currentTimeMillis();
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
        fos.flush();
        fc.close();
        fis.close();
    }
}