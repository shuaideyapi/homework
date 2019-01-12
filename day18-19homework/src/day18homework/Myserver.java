package day18homework;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Myserver {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4396);
        System.out.println("服务器以开启...");
        ArrayList<Socket> socketlist = new ArrayList<>();
        ArrayList<String> userlist = new ArrayList<>();

        while(true){
            Socket accept = serverSocket.accept();
            socketlist.add(accept);

            System.out.println("客户端已连接...");
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         InputStream in = accept.getInputStream();
                         OutputStream out  = accept.getOutputStream();
                         while (true) {
                             String s = readrequest(in);
                             if (s == null) break;
                             System.out.println(s+"进入了聊天室");
                             userlist.add(s);
                             //out.write(("欢迎"+s+"来到聊天室\n").getBytes("utf-8"));
//                             out.write("请选择你要完成的操作：\r\n".getBytes("utf-8"));
//                             out.write("1.群聊\n".getBytes("utf-8"));
//                             out.write("2.查询当前在线人员".getBytes("utf-8"));
                             String s1 = readrequest(in);
                             System.out.println(s1);
                             switch(Integer.parseInt(s1)) {
                                 case 1:
                                     //群聊，一人发消息，所有人接受到
                                     String s2 = readrequest(in);
                                     System.out.println(s2);
                                     for (Socket user : socketlist) {
                                         user.getOutputStream().write(s2.getBytes("utf-8"));
                                     }

                                 case 2:
                                     //当前在线人员信息
//                                     System.out.print("当前在线的有");
//                                     System.out.println(userlist);
                                     out.write(userlist.toString().getBytes("utf-8"));
                             }

                         }
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }

                 private String readrequest(InputStream in) throws IOException {
                     byte[] bytes = new byte[1024];
                     int len = in.read(bytes);
                     if (len == -1) {
                         return null;
                     }
                     String s = new String(bytes, 0, len, "utf-8");
                     return s;
                 }
             }).start();
        }
    }
}