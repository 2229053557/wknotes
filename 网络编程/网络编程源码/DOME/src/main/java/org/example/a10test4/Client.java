package org.example.a10test4;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        //客户端：接收本地文件上传到服务器，接收服务器的反馈。
        //服务器：接收客户端上传的文件，上传完毕后给出反馈。
        System.out.println("=======客户端启动======");
        //创建Socket对象，并连接服务器。
        Socket socket = new Socket("127.0.0.1", 7777);
        //读取本地文件，并写到服务器当中
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\javaExercise\\网络编程\\DOME\\src\\main\\java\\org\\example\\picture\\QQ图片20220923102331.jpg"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len= bis.read(bytes))!=-1) {
            bos.write(bytes,0,len);
        }
        //往服务器写结束标志
        socket.shutdownOutput();
        //接受服务器的回写数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String a=br.readLine();
        System.out.println(a);

        //释放资源
        socket.close();
    }
}
