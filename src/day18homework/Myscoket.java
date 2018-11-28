package day18homework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Myscoket {
    private static ThreadPoolExecutor poolExecutor = null;

    public static void main(String[] args) throws IOException {
        Boolean flag = false;
        Socket socket = new Socket("localhost", 4396);
        OutputStream out = socket.getOutputStream();

        poolExecutor = new ThreadPoolExecutor
                (10, 10, 0L, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        poolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner user = new Scanner(System.in);
                    System.out.println("请输入您的昵称：");
                    String name = user.nextLine();
                    out.write(name.getBytes("utf-8"));
//                            poolExecutor.submit(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        serverresponed(socket);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });

                    while (true) {
                        System.out.println("欢迎来到聊天室~");
                        System.out.println("请输入你要完成的操作：\r");
                        System.out.println("1.群聊;\r");
                        System.out.println("2.查看当前在线人员;\r");
                        Scanner num = new Scanner(System.in);
                        int caozuo = num.nextInt();
                        out.write((caozuo + "").getBytes("utf-8"));
                        switch (caozuo) {
                            case 1:
                                Scanner usermassage = new Scanner(System.in);
                                System.out.println("请输入内容");
                                String s = usermassage.nextLine();
                                out.write((name + ":" + s).getBytes("utf-8"));
                                readresponed();

                            case 2:
                                readresponed();

                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void readresponed() throws IOException {
                InputStream in = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                String s3 = null;
                while ((len = in.read(bytes)) != -1) {
                    s3 = new String(bytes, 0, len, "utf-8");
                    System.out.println(s3);
                }

            }
        });


    }
}

