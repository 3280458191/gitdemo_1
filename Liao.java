package App;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Liao implements Runnable {
    static ArrayList<Stu> list = new ArrayList<>();//当前处于登录状态的用户
    static HashMap<String, String> hm = new HashMap<>();//文件中所有的用户

    static {
        File file = new File("src/App/test.txt");
        BufferedReader brs = null;
        try {
            brs = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String ss;
        while (true) {
            try {
                if (!((ss = brs.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] split = ss.split("=");
            hm.put(split[0], split[1]);
        }
        try {
            brs.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Socket socket;

    public Liao(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//字节流转换成字符流转换成字符缓冲流
                OutputStream os = socket.getOutputStream();
                String s = br.readLine();
                String ss = br.readLine();
                String name = (ss.split("&")[0].split("="))[1];
                String pass = (ss.split("&")[1].split("="))[1];
                if (s.equals("login")) {
                    login(br,os,name,pass);
                } else if (s.equals("register")) {
                    Sregister(br,os,name,pass);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void login(BufferedReader br,OutputStream os,String name,String pass) throws IOException {
        while (true) {
            if (hm.containsKey(name)) {
                if (hm.get(name).equals(pass)) {
                    Stu stu = new Stu(name, pass);
                    if (!list.contains(stu)) {
                        list.add(stu);
                        os.write("登录成功\r\n".getBytes());
                        fwd.list.add(socket);
                        System.out.print(stu.getName()+"加入了聊天群\t");
                        System.out.println("当前聊天人数为："+fwd.list.size());
                        ss(br,stu);
                    } else {
                        os.write("请勿重新登录\r\n".getBytes());
                    }
                } else {
                    os.write("用户名或密码错误\r\n".getBytes());
                }
            } else {
                os.write("用户不存在\r\n".getBytes());
                break;
            }
        }
    }

    private void ss( BufferedReader br,Stu stu) throws IOException {//开启聊天
        while (true) {
            String s=br.readLine();
            fwd.list.forEach(new Consumer<Socket>() {
                @Override
                public void accept(Socket socket) {
                    try {
                        OutputStream os = socket.getOutputStream();
                        os.write((stu.getName()+">"+s+"\r\n").getBytes());
                        System.out.print(stu.getName()+">"+s+"\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
    private void Sregister(BufferedReader br,OutputStream os,String name,String pass) throws IOException {
        while(true){
            if (hm.containsKey(name)){
                os.write("用户已存在\r\n".getBytes());
            }
            else{
                Stu stu=new Stu();
                stu.setName(name);
                stu.setPass(pass);
                FileWriter fw = new FileWriter("src/App/test.txt");
                fw.write(stu.toString());
                hm.put(name,pass);
            }
            break;
        }
    }

}
