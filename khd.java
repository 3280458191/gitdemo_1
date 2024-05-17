package App;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class khd {
    static Scanner sc = new Scanner(System.in);
    static Stu stu;
    static Socket socket;

    static {
        try {
            socket = new Socket("127.0.0.1", 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        dayin();

    }


    private static void dayin() throws IOException {
        System.out.println("==========欢迎来到我的主场==========");
        OutputStream os = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//字节流转换成字符流转换成字符缓冲流
        Look:
        while (true) {
            System.out.print("1：登录\n2：注册\n请输入你的选项>");
            String s = sc.nextLine();

            if (s.equals("1")) {
                os.write("login\r\n".getBytes());
            } else {
                os.write("register\r\n".getBytes());
            }
            os.flush();
            if (s.matches("[12]")) {
                while (true) {
                    System.out.println("请输入你的用户名");
                    String name = sc.nextLine();
                    System.out.println("请输入你的密码");
                    String pass = sc.nextLine();
                    os.write(("usernamr=" + name + "&" + "password=" + pass + "\r\n").getBytes());
                    os.flush();
                    s = br.readLine();
                    if (s.equals("登录成功")) {
                        stu = new Stu(name, pass);
                        ss(br, os);
                        break Look;
                    } else if (s.equals("用户不存在")||s.equals("用户已存在")) {
                        System.out.println(s);
                        break;
                    }else {
                        System.out.println(s);
                    }
                }
            } else {
                System.out.println("输入有误，请重新选择");
            }
        }
    }


    private static void ss(BufferedReader br, OutputStream os) {//开启聊天
        A1 a1 = new A1(br, stu);
        A2 a2 = new A2(os);
        a1.start();
        a2.start();

    }
}
