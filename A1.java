package App;

import java.io.BufferedReader;
import java.io.IOException;
public class A1 extends Thread{
    BufferedReader br;
    Stu stu;
    public A1(BufferedReader br,Stu stu) {
      this.br=br;
      this.stu=stu;
    }

    @Override
    public void run() {
        while(true){
            String s;
            try {
                s = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!(s.split(">")[0].equals(stu.getName()))){
                System.out.println(s);
            }
        }
    }

}
