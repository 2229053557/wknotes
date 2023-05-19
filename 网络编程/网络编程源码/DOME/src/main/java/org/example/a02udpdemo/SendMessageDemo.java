package org.example.a02udpdemo;

import java.io.IOException;
import java.net.*;

public class SendMessageDemo {
    public static void main(String[] args) throws Exception {
        /**
         * 发送数据
         */
        //1.创建DatagramSocket对象（快递公司）
        //细节
        //绑定端口，以后我们通过这个端口往外发送
        //空参：所有可用的端口随机一个进行使用
        //有参：指定端口号进行绑定
        //2.打包数据
        //3.发送数据
        //4.释放资源
        DatagramSocket ds=new DatagramSocket();

        //2.打包数据
        String str="你好威啊！！！！";
        byte[] bytes=str.getBytes();

        InetAddress address=InetAddress.getByName("127.0.0.1");

        int port=10086;

        DatagramPacket dp=new DatagramPacket(bytes, bytes.length,address,port);

        //3.发送数据
        ds.send(dp);

        //4.释放资源
        ds.close();
    }
}
