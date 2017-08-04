/**
 * http://blog.csdn.net/dgly1611/article/details/12967295 代码来源
 * Created by xdq on 2017/8/3.
 */
import java.io.BufferedInputStream;//实现文件读写
import java.io.DataInputStream;//实现数据读取
import java.io.DataOutputStream;//实现数据输出
import java.io.File;//实现文件操作
import java.io.FileInputStream;//实现文件独缺
import java.io.FileNotFoundException;//文件异常
import java.io.IOException;//io异常
import java.net.ServerSocket;//进行socket通信
import java.net.Socket;
import java.lang.Thread;
public class Server {
    private ServerSocket ss=null;//创建初始变量
    public Server(){

    }//构造函数进行初始化

    /**
     * 该函数使用socket通信进行文件的传输
     * @param filePath 读取文件的路径
     * @param port  进行socket所使用的端口号
     *
     */
    public void sendFile(String filePath,int port){
        DataOutputStream dos=null;
        DataInputStream dis=null;

        Socket socket=null;//创建套接字变量
        try {
            File file=new File(filePath);//读取文件
            ss=new ServerSocket(port);//加载所使用的端口号
            socket=ss.accept();//开始建立连接
            dos=new DataOutputStream(socket.getOutputStream());//创建文件传输的缓存区，多态？
            dis=new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));//以字节流的形式读取文件

            int buffferSize=1024;
            byte[]bufArray=new byte[buffferSize];//创建1024byte大小的缓存区
            dos.writeUTF(file.getName());//getName获得文件的名字，放入缓存区中
            dos.flush();//从缓存区中送入到客户端
            dos.writeLong((long) file.length());//获得文件的长度（以byte为单位）
            dos.flush();//将文件名称和文件长度传输给客户端
            while (true) {
                int read = 0;
                if (dis!= null) {
                    read = dis.read(bufArray);
                }

                if (read == -1) {
                    break;
                }
                dos.write(bufArray, 0, read);//发送文件

            }
            dos.flush();//度我去玩数据后进行确定
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            try {
                if (dos != null)
                    dos.close();
            } catch (IOException e) {
            }
            try {
                if (dis != null)
                    dis.close();
            } catch (IOException e) {
            }
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
            }
            try {
                if (ss != null)
                    ss.close();
            } catch (IOException e) {
            }
        }


    }
    public static void main(String []args){

        chose_floder test= new chose_floder();

        new Server().sendFile(test.find(), 8821);

       // new Server().sendFile("C:\\Users\\xdq\\Desktop\\英文翻译.docx", 8821);
    }
}