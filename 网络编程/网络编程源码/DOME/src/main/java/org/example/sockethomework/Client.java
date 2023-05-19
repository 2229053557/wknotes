package org.example.sockethomework;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        /**
         * 客户端：
         * 拥有聊天，注册，聊天功能
         * 1.当顾客启动之后，要求客户选择登录操作还是注册操作，需要循环
         * 如果是登录操作，就输入用户名和密码，以以下格式发送给服务端
         * username=zhangsan&&password=123
         * 如果是注册操作，就输入用户名和密码，以以下格式发送给服务端
         * username=zhangsan&&password=123
         * 2.登录成功之后，直接开始聊天
         */
        System.out.println("=======客户端启动========");
        //创建Socket对象管道
        Socket socket = new Socket("127.0.0.1",8888);
        System.out.println("连接成功");
        //主页面
        while (true) {
            System.out.println("==================欢迎来到聊天室===============");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("请选择你要的操作");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            switch (choice){
                case "1": login(socket);break;
                case "2": System.out.println();break;
                default:System.out.println("没有这个选项");break;
            }
        }
    }
    public static void login(Socket socket) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //用键盘输入
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username= input.nextLine();
        System.out.println("请输入密码");
        String password=input.nextLine();
        //拼接
        StringBuilder sb = new StringBuilder();
        //uername=zhangsan&password=123
        sb.append("username=").append(username).append("&password=").append(password);

        //第一次执行的是登录操作
        bw.write("login");
        bw.newLine();
        bw.flush();
        //第二次写的是用户名和密码的信息
        //往服务器写出用户和密码
        bw.write(sb.toString());
        bw.newLine();//换行
        bw.flush();
        //接收数据
        //获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = br.readLine();
        //状态码 1：登陆成功。2：密码有误。3：用户名不存在
        if("1".equals(message)){
            System.out.println("登录成功，可以开始聊天");
            //开一条单独的线程，专门用来接收服务端发送过来的聊天记录
            new Thread(new ClientMyRunnable(socket)).start();
            //开始聊天
            talk2All(bw);

        }
        else if("2".equals(message)){
            System.out.println("密码输入错误");

        }
        else if("3".equals(message)){
            System.out.println("用户名不存在,请先注册");

        }

    }

    private static void talk2All(BufferedWriter bw) throws IOException {
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要说的话");
            String a=input.nextLine();
            bw.write(a);
            bw.newLine();
            bw.flush();
        }

    }
}

       class ClientMyRunnable implements Runnable{
    Socket socket;

           public ClientMyRunnable(Socket socket) {
               this.socket = socket;
           }

           @Override
           public void run() {
               //循环，重复接收
               while (true) {
                   try {
                       //接收服务器发送过来的聊天记录
                       BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                       String str=br.readLine();
                       System.out.println(str);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       }