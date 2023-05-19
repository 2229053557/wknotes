package org.example.sockethomework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server {
    static ArrayList<Socket> list =new ArrayList<>();
    public static void main(String[] args) throws Exception{
        /**
         * 服务端：
         * 1.先读取本地文件中所有的正确用户信息
         * 2.当客户端来连接的时候，就开启一条线程
         * 3.在线程里面判断当前用户是登录操作还是注册操作
         * 4.登录，校验用户名和密码是否正确
         * 5.注册，检验用户名是否唯一，检验用户名密码格式是否正确
         * 6.如果登陆成功，开始聊天
         * 7.如果注册成功，将用户信息写到本地，开始聊天。
         */
        System.out.println("=======服务端启动========");
        //创建ServerSocket对象注册端口
        ServerSocket ss=new ServerSocket(8888);
        //把本地文件用户名密码获取到
        Properties prop = new Properties();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\javaExercise\\网络编程\\DOME\\src\\main\\java\\org\\example\\sockethomework\\a.txt"));
        prop.load(bis);
        bis.close();
        //等待管道连接
        while (true) {
            Socket socket = ss.accept();
            System.out.println("管道连接成功");
            new Thread(new MyRunnable(socket,prop)).start();
        }

    }
}
     class MyRunnable implements Runnable{
    Socket socket;
    Properties prop;

    public MyRunnable(Socket socket, Properties prop) {
        this.socket = socket;
        this.prop = prop;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

           //读第一次操作
            while (true) {
                String choose= br.readLine();
                switch (choose){
                    case "login": login(br);break;
                    case "register":System.out.println("用户选择了注册操作");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
          //读取用户登录时，传过来的用户信息
          // 并对信息进行校验
         public void  login(BufferedReader br)throws IOException{
            System.out.println("用户选择登录操作");
            String userinfo=br.readLine();
            //uername=zhangsan&password=123
             String[] userInfoArr=userinfo.split("&");
             String uernameInput=userInfoArr[0].split("=")[1];
             String passwordInput=userInfoArr[1].split("=")[1];
             System.out.println("用户输入的用户名为"+uernameInput);
             System.out.println("用户输入的密码为"+passwordInput);

             if (prop.containsKey(uernameInput)) {
                 //如果用户名存在，继续判断密码
                 String rightPassword=prop.get(uernameInput)+"";
                 if (rightPassword.equals(passwordInput)) {
                     //提示用户登录成功，可以开始聊天


                     writeMessage2Client("1");
                   Server.list.add(socket);

                     //接收客户端发送过来的数据，并打印在控制台
                     talk2All(br,uernameInput);
                 } else {
                     //密码输入错误
                     writeMessage2Client("2");



                 }
             } else {
                 //如果用户名不存在，直接回写
                 writeMessage2Client("");
             }
         }
         public void writeMessage2Client(String message)throws IOException{
            //获取输出流
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             bw.write(message);
             bw.newLine();
             bw.flush();
         }
         public void talk2All(BufferedReader br, String username)throws  IOException{
            while (true){
                String message=br.readLine();
                System.out.println(username+"发送过来消息"+message);
                //群发

                for (Socket s : Server.list) {
                    //s依次表示每一个客户端的连接对象
                    writeMessage2Client(s,username+"发送过来消息"+message);

                }
            }

         }
         public void writeMessage2Client(Socket s,String message)throws IOException{
             //获取输出流
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
             bw.write(message);
             bw.newLine();
             bw.flush();
         }

}
