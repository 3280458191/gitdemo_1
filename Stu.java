package App;

import java.util.Objects;

public class Stu {
    private String name;
    private String pass;

    public Stu(String name, String pass) {
        this.name=name;
        this.pass=pass;
        /*setName(name);
        setPass(pass);
        if(this.name==null||this.pass==null) {
           throw new IllegalArgumentException("参数错误");
        }*/

    }

    public Stu() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name){
       /* if (name.length() < 6 || name.length() > 18) {
            throw new IllegalArgumentException("参数错误");
        }*/
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        /*if (pass.length() >= 3 && pass.length() <= 8 && pass.matches("[a-zA-Z][0-9]{5,}")) {
            this.pass = pass;
        }else{
            throw new IllegalArgumentException("参数错误");
        }*/
        this.pass=pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stu stu = (Stu) o;
        return Objects.equals(name, stu.name) && Objects.equals(pass, stu.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass);
    }

    @Override
    public String toString() {
        return name+"="+pass;
    }
}
