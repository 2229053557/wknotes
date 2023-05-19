package org.example.bao;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
public class Test {
    public static void main(String[] args) throws IOException{
        System.out.println("=========客户端启动========");

        //1.创建发送通信管道
        Socket socket=new Socket("127.0.0.1",7777);//参数一：本机服务端地址 参数二：服务端端口
        //2.从scoket管道中获得一个字节输出流（输出流：写入数据。输入流：读取数据。反义相对）,负责发送数据

        OutputStream os=socket.getOutputStream();
        //3.字节流升级成打印流
        PrintStream ps=new PrintStream(os);
        //4.发送消息
        ps.println("大哥，你好");
        ps.flush();//刷新
    }
}
//发送端
//public class Test {
//    public static void main(String[] args) throws IOException {
//        System.out.println("==============客户端启动===============");
//        //1.创建发送通信管道
//        Socket socket = new Socket("127.0.0.1",7777);//参数一：服务端地址 参数二：服务端端口
//        //2.从scoket管道中获得一个字节输出流,负责发送数据
//        OutputStream os = socket.getOutputStream();
//        //3.字节流升级成打印流
//        PrintStream ps = new PrintStream(os);
//        //4.发送消息
//        ps.println("大哥，我来了");
//        ps.flush();//刷新
//    }
//}