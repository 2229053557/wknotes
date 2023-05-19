package org.example.a11test5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws Exception{
        //客户端：接收本地文件上传到服务器，接收服务器的反馈。
        //服务器：接收客户端上传的文件，上传完毕后给出反馈。
        System.out.println("======服务端启动=======");
        //创建线程池对象
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                3,//核心线程数量
                16,//线程总大小
                60,//空闲时间
                TimeUnit.SECONDS,//空闲时间（单位）
                new ArrayBlockingQueue<>(2),//队列
        Executors.defaultThreadFactory(),//线程工厂，让线程池如何创建线程对象
        new ThreadPoolExecutor.AbortPolicy()//阻塞队列
        );
        //1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(7777);

        while (true) {
            //等待客户端来连接
            Socket accept = ss.accept();
            //开启线程
            //一个用户对应着服务端的一条线程
            //读取数据并保存到本地文件中
//           new Thread(new MyRunnable(accept)).start();
            tpe.submit(new MyRunnable(accept));
        }

    }
}
class MyRunnable implements Runnable{
    Socket socket;

    public MyRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String name = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\javaExercise\\网络编程\\DOME\\src\\main\\java\\org\\example\\picture\\"+name+".jpg"));
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            byte[] bytes = new byte[1024];
            int len;
            while((len= bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            //回写数据
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("接收成功");
            bw.newLine();//换行
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //释放资源
            if (socket!=null) {
                try {

                    socket.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
