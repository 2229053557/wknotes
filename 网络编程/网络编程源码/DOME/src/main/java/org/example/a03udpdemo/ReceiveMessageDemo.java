package org.example.a03udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws Exception{
        /**
         * 接收数据
         */
        //1创建DatagramSocket对象（快递公司）
        //细节：
        //在接收的时候，一定要绑定端口
        //而且在绑定端口一定要跟发送的端口保持一致
        DatagramSocket ds=new DatagramSocket(10086);


        //2.接收数据包
        byte[] bytes=new byte[1024];
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length);

        while (true) {
            ds.receive(dp);

            //3.解析数据包
            byte[] data=dp.getData();
            int len=dp.getLength();
            String ip=dp.getAddress().getHostAddress();
            String name=dp.getAddress().getHostName();

//4.打印数据
            System.out.println("ip为"+ip+".主机名为："+name+"的人，发送了数据："+new String(data,0,len));
        }
    }
}
