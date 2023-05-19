package org.example.a07test1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        //客户端：多次发送数据
        //服务器：接收多次数据，并打印。
        //1.创建ServerSocket对象，并绑定9999端口。
        ServerSocket ss = new ServerSocket(9999);
        //2.等待客户端来连接
        Socket accept = ss.accept();
        //3.读取数据
        InputStream is = accept.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String a;
        while ((a= br.readLine())!= null) {
            System.out.println(accept.getRemoteSocketAddress() + "说：" + a);
        }
        //4.释放数据
        accept.close();
        ss.close();
    }
}
