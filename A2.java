package App;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class A2 extends Thread {
    OutputStream os;
    Scanner sc = new Scanner(System.in);

    public A2(OutputStream os) {
       this.os=os;
    }

    @Override
    public void run() {
        while(true){
            try {
                os.write((sc.nextLine()+"\r\n").getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
