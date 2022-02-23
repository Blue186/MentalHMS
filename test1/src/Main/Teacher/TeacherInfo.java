package Main.Teacher;
import Main.TeacherE;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherInfo {
    Statement sql;
    boolean res;
    TeacherE teacherE = new TeacherE();
    String User = teacherE.getUser();
    public TeacherInfo(Statement sql) {
        this.sql = sql;
    }

    public void update() {
        Scanner in = new Scanner(System.in);
        int ins;
        String operate;
        String con;
        do {
            // 执行SQL语句，返回结果集
            System.out.println("请输入您想更改的项目");
            System.out.println("1.工作时间");
            System.out.println("2.邮箱");
            System.out.println("3.手机号");
//            System.out.println("4.所属学院");
//            System.out.println("5.擅长领域");
//            System.out.println("6.工作时间");
//            System.out.println("7.工作时间");
            System.out.println("0.退出");
            ins = in.nextInt();
            if(ins == 1) operate = "TWork";
            else if(ins == 2) operate = "TEmail";
            else if(ins == 3) operate = "Tphone";
//            else if(ins == 4) operate = "TEmail";
//            else if(ins == 5) operate = "TfTphoneield";
//            else if(ins == 6) operate = "TCollege";
//            else if(ins == 7) operate = "TWork";
            else if(ins == 0) {break;}
            else {
                System.out.println("输入错误，请重新输入！");
                continue;
            }
            System.out.println("请输入您想更改的内容：");
            con = in.next();
            try {
                sql.execute("begin ");
                sql.execute("select *from teacherinfo where TNo= "+"\""+User+"\""+" for update ");
                res = sql.execute("update teacherinfo set "+operate+" = \""+con +"\""+" where Tno ="+ "\""+User+"\";");
                sql.execute("commit ;");
                if (!res) System.out.println("更新成功");
                else System.out.println("更新失败");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while(ins != 0);
    }
}
