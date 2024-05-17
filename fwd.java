package App;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class fwd {
    static ArrayList<Socket> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //服务端

     ServerSocket ss = new ServerSocket(1000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 16, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        while (true) {
            Socket socket = ss.accept();
            pool.submit(new Liao(socket));
        }
    }
}
