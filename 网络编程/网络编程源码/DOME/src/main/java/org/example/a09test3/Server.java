package org.example.a09test3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        //客户端：接收本地文件上传到服务器，接收服务器的反馈。
        //服务器：接收客户端上传的文件，上传完毕后给出反馈。
        System.out.println("======服务端启动=======");
        //1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(7777);
        //等待客户端来连接
        Socket accept = ss.accept();
        //读取数据并保存到本地文件中
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\javaExercise\\网络编程\\DOME\\src\\main\\java\\org\\example\\picture\\img2.png"));
        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        byte[] bytes = new byte[1024];
        int len;
        while((len= bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        //回写数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bw.write("接收成功");
        bw.newLine();//换行
        bw.flush();
        //释放资源
        ss.close();
        accept.close();
    }
}
