package org.example.a08test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        //客户端：发送一条数据，接收服务端反馈的数据并打印。
        //服务端：接收数据并打印，再给客户端反馈消息。
        System.out.println("======服务端启动======");
        //创建ServerSocket对象,并绑定端口
        ServerSocket ss = new ServerSocket(8888);
        //2.等待客户端来连接
        Socket accept = ss.accept();
        //3.读取数据
        InputStream is = accept.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String a;
        //细节：read方法会从连接中读取数据，但是需要有一个标记，此处循环才会停止，否则就会一直停在read方法这里，等待读取下面的数据。
        while((a= br.readLine())!=null){
            System.out.println(accept.getRemoteSocketAddress()+"说："+a);
        }
        //回写数据
        OutputStream os = accept.getOutputStream();
        PrintStream ps = new PrintStream(os);
        String str="不是";
        ps.println(str);
        ps.flush();
        //4.释放数据
        ss.close();
        accept.close();
    }
}
