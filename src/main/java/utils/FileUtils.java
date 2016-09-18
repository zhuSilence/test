package utils;

import file.DateUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by zhuxiang on 2016/9/18.
 * Desc :
 */
public class FileUtils {
    /**
     * 标准输入流
     */
    private InputStream inputStream;
    /**
     * 标准输出流
     */
    private OutputStream outputStream;

    public FileUtils() {
    }

    public FileUtils(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    /**
     * 根据文件夹的目录创建文件夹，出错抛异常
     *
     * @param folderPath
     * @throws IOException
     */
    public void mkdirsFolder(String folderPath) throws IOException {
        String message;
        File directory = new File(folderPath);
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                message = "File " + directory + " exists and is " + "not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        } else if (!directory.mkdirs() && !directory.isDirectory()) {
            message = "Unable to create directory " + directory;
            throw new IOException(message);
        }
    }


    /**
     * 将输入流的内容写入指定文件
     *
     * @param saveFile
     * @throws IOException
     */
    public void writeToFile(File saveFile) throws IOException {
        if (!saveFile.getParentFile().exists()) {
            mkdirsFolder(saveFile.getParent());
        }
        outputStream = new FileOutputStream(saveFile, true);
        byte[] buffer = new byte[4096];
        int length;
        while (-1 != (length = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, length);
            outputStream.write(",".getBytes());
        }
    }


    /**
     * 根据时间每天创建一个新的日志文件
     *
     * @param folderPath 日志文件的目录
     * @throws IOException
     */
    public void writeFileEveryDay(String folderPath) throws IOException {
        mkdirsFolder(folderPath);
        Date date = new Date();
        String dateStr = DateUtils.formatDate(date, "yyyy-MM-dd");
        String fileName = folderPath + dateStr + ".log";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        writeToFile(file);
    }


    /**
     * 关闭输入输出流
     *
     * @throws IOException
     */
    public void closeInputAndOut() throws IOException {
        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
    }

/*    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("D:\\软件\\java\\logs\\2016-09-17.log");
        FileUtils fileUtils = new FileUtils(inputStream);
        fileUtils.writeFileEveryDay("D:\\软件\\java\\logs\\");

    }*/
}
