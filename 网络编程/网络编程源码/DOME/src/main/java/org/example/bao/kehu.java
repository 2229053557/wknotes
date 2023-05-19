package org.example.bao;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class kehu {
    public static void main(String[] args) throws Exception {
        System.out.println("========客户端启动=======");
        //1、创建与服务端链接的管道
        Socket socket = new Socket(InetAddress.getLocalHost(), 9955);//参数一：本机服务端地址 参数二：服务端端口
        //使用InetAddress类的静态方法getLocalHost()获得一个InetAddress对象，该对象含有本地机的域名和IP地址
        //2.从scoket管道中获得一个字节输出流（输出流：写入数据。输入流：读取数据。反义相对）,负责发送数据
        //2.1创建一个线程用于客户端消息的读取。
        new ClientReaderThread(socket).start();
        OutputStream os = socket.getOutputStream();
        //3.字节流升级成打印流
        PrintStream ps = new PrintStream(os);
        //4.发送消息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入");
        String s1 = sc.nextLine();
        ps.println("");//发送数据过去
        ps.flush();//刷新流

    }
}


    class ClientReaderThread extends Thread {
        private Socket socket;

        public ClientReaderThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
            //4.字节流升级生缓冲输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //5.按照行读取消息会更好
            String a;
            while (true) {

                    if ((a = br.readLine()) != null) {
                        System.out.println("收到消息" + a);
                    }
                }
            }catch (IOException e) {
                    System.out.println("服务器把你提出了群聊");
                }
            }
        }
