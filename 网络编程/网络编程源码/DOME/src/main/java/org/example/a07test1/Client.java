package org.example.a07test1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        System.out.println("=======客户端启动======");
        //客户端：多次发送数据
        //服务器：多次接收数据，并打印
        //1.创建Socket对象并连接服务端。
        Socket socket = new Socket("127.0.0.1",9999);
        //2.从scoket管道中获得一个字节输出流（输出流：写入数据。输入流：读取数据。反义相对）,负责发送数据
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);//升级流
        Scanner input=new Scanner(System.in);
        //3.写出数据
        while (true) {
            System.out.println("请输入消息");
            String str=input.nextLine();
            if("886".equals(str)){
                break;
            }
            ps.println(str);
            ps.flush();
        }
        //4.释放资源
        socket.close();

    }
}
