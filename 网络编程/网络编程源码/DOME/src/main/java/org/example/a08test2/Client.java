package org.example.a08test2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        //客户端：发送一条数据，接收服务端反馈的数据并打印。
        //服务端：接收数据并打印，再给客户端反馈消息。
        System.out.println("=====客户端启动=====");
        //创建一个Socket对象管道
        Socket socket = new Socket("127.0.0.1",8888);
        //在管道里写入一个OutputStream流，用于写入数据
        OutputStream os = socket.getOutputStream();
        //将outputStream流升级为打印流
        PrintStream ps = new PrintStream(os);
        //写出数据
        String str="你是猪吗？";
        ps.println(str);
        ps.flush();
        socket.shutdownOutput();//停止循环。
        //接收服务端回馈的数据
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String a;
        while((a= br.readLine())!=null){
            System.out.println(socket.getRemoteSocketAddress()+"说："+a);
        }
        //关闭数据
        socket.close();
    }
}
