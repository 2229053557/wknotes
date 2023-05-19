package org.example.a04udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ReceiveMessageDemo01{
    public static void main(String[] args)throws IOException {


    /**
     * 组播接收代码
     */
    //1创建MulticastSocket对象（快递公司）
    //细节：
    //在接收的时候，一定要绑定端口
    //而且在绑定端口一定要跟发送的端口保持一致
    MulticastSocket ms=new MulticastSocket(10000);

    //1.2将当前本机，添加到224.0.0.1的这一组中
    InetAddress address=InetAddress.getByName("224.0.0.1");
    ms.joinGroup(address);
    //2.接收数据包
    byte[] bytes=new byte[1024];
    DatagramPacket dp=new DatagramPacket(bytes,bytes.length);
    //2.2接收数据
        ms.receive(dp);

    //3.解析数据包
    byte[] data=dp.getData();
    int len=dp.getLength();
    String ip=dp.getAddress().getHostAddress();
    String name=dp.getAddress().getHostName();
   System.out.println("ip为"+ip+".主机名为："+name+"的人，发送了数据："+new String(data,0,len));

    //4.释放资源
        ms.close();
}
  }