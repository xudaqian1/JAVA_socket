/**
 * Created by xdq on 2017/8/3.
 * http://blog.csdn.net/dgly1611/article/details/12967295 代码来源
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client (){

    }
    public void receiveFile(String savePath,String ip,int port){
        Socket socket=null;
        try {
            socket = new Socket(ip,port);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DataInputStream dis=null;
        try {
            dis = new DataInputStream(new BufferedInputStream(socket
                    .getInputStream()));//接收文件
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        int bufferSize = 1024;
        // 缓冲区
        byte[] buf = new byte[bufferSize];
        int passedlen = 0;
        long len = 0;
        // 获取文件名称
        try{
            savePath += dis.readUTF();//文件的路径
            DataOutputStream fileOut = new DataOutputStream(
                    new BufferedOutputStream(new BufferedOutputStream(
                            new FileOutputStream(savePath))));
            len = dis.readLong();
            System.out.println("文件的长度为:" + len + "    KB");
            System.out.println("开始接收文件!");
            progress_bar t=new progress_bar();
            while (true) {
                int read = 0;
                if (dis!= null) {
                    read = dis.read(buf);
                }
                passedlen += read;
                if (read == -1) {
                    break;
                }
                int num=(int)(passedlen * 100 / len);
                t.run(num);
                fileOut.write(buf, 0, read);
            }
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    public static void main(String[] args) {
        chose_floder test= new chose_floder();
        new Client().receiveFile(test.find()+"\\", "localhost", 8821);
       // new Client().receiveFile("F:\\", "localhost", 8821);

    }
}