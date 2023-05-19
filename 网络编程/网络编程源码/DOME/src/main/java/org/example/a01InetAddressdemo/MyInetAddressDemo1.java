package org.example.a01InetAddressdemo;

import java.net.InetAddress;
public class MyInetAddressDemo1 {
    public static void main(String[] args) throws Exception{


     //1获取本机地址ip对象，获取主机名的ip地址，主机名称可以是机械名称，也可以是IP地址。
     //2获取主机名字
     //3获取ip地址字符串
    InetAddress ip1=InetAddress.getLocalHost();
        System.out.println(ip1.getHostName());//获取主机名字
        System.out.println(ip1.getHostAddress());//获取ip地址字符串

        //2.获取域名ip对象
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostName());//获取域名
        System.out.println(ip2.getHostAddress());//获取域名的ip地址

        //3.获取公网对象
        InetAddress ip3 = InetAddress.getByName("112.80.248.76");
        System.out.println(ip3.getHostName());//获取公网名字
        System.out.println(ip3.getHostAddress());//获取公网ip地址
        //判断网络是否能连接通信 ping 5s之前测试是否能通过
        System.out.println(ip3.isReachable(5000));//通过会返回true

   }
}