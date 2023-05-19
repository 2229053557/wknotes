package org.example.a04udpdemo;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendMessageDemo {
    public static void main(String[] args) throws Exception{
        /**
         * 组播发送端代码
         */
        //创建MuticastSocket对象
        //1.创建DatagramSocket对象（快递公司）
        //细节
        //绑定端口，以后我们通过这个端口往外发送
        //空参：所有可用的端口随机一个进行使用
        //有参：指定端口号进行绑定
        MulticastSocket ms=new MulticastSocket();
        //创建DatagramPacket对象
        String str="你好威啊！！！！";
        byte[] bytes=str.getBytes();
        InetAddress address=InetAddress.getByName("224.0.0.1");//224.0.0.1是组播地址
        int port=10000;
        DatagramPacket dp=new DatagramPacket(bytes, bytes.length,address,port);

        //3.发送数据//调用MulicastSocket发送数据方法发送数据
        ms.send(dp);

        //4.释放资源
        ms.close();
    }
}
