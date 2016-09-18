package file;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * <p>类名：FileUtils</p>
 * <p>说明：TODO<br>文件管理类</p>
 * <p>作者： siber_xu</p>
 * <p>时间： 2012/2/17 下午2:40:06</p>
 */
public class FileUtils {

    /**
     * <p>标题: newFolder<p>
     * <p>说明: TODO<br>新建文件夹</P>
     *
     * @param folderPath
     */
    public void newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    /**
     * <p>标题: newFile<p>
     * <p>说明: TODO<br>新建文件</P>
     *
     * @param filePathAndName 文件名称
     * @param fileContent     文件内容
     */
    public void newFile(String filePathAndName, String fileContent) {

        try {
            //String filePath = filePathAndName;
            //filePath = filePath.toString();
            File myFilePath = new File(filePathAndName);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePathAndName);
            fos.write(fileContent.getBytes("UTF-8"));
            fos.flush();
            fos.close();
            //FileWriter resultFile = new FileWriter(myFilePath);
            //PrintWriter myFile = new PrintWriter(resultFile);
            //String strContent = fileContent;
            //myFile.println(strContent);
            //resultFile.close();

        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }

    }

    /**
     * <p>标题: delFile<p>
     * <p>说明: TODO<br>删除文件</P>
     *
     * @param filePathAndName
     */
    public void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * <p>标题: delFolder<p>
     * <p>说明: TODO<br>删除文件夹</P>
     *
     * @param folderPath
     */
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹

        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();

        }

    }

    /**
     * <p>标题: delAllFile<p>
     * <p>说明: TODO<br>删除文件夹中所有文件</P>
     *
     * @param path
     */
    public void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * <b>writeFile</b><br/>
     * <p>将流写入到文件</p>
     * <b>date：</b>2014-6-19 上午10:46:26<br/>
     *
     * @param saveFile
     * @param inputStream
     * @throws java.io.IOException
     * @throws
     * @since 1.0
     */
    public void writeFile(File saveFile, InputStream inputStream) throws IOException {
        if (!saveFile.getParentFile().exists()) {
            org.apache.commons.io.FileUtils.forceMkdir(saveFile.getParentFile());
        }
        OutputStream output = new FileOutputStream(saveFile,true);

        IOUtils.copy(inputStream, output);
        IOUtils.closeQuietly(output);
    }

    private ArrayList<String> filelist = new ArrayList<String>();

    public ArrayList<String> getFiles(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
                //filelist.add(file.getAbsolutePath());
                //System.out.println("显示" + filePath + "下所有子目录及其文件" + file.getAbsolutePath());
            } else {
                filelist.add(file.getAbsolutePath());
                //System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
            }
        }
        return filelist;
    }

    public Boolean renName(String orgName, String newName) {
        File f = new File(orgName);
        // String c=f.getParent();
        File mm = new File(newName);
        if (f.renameTo(mm)) {
            return true;
        } else {
            return false;
        }
    }

}
