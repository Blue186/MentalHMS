package Main;

import Main.Teacher.TeacherInfo;
import Main.Teacher.TeacherLook;
import Main.Teacher.TeacherSearch;
import sql.Conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherE {
    static Statement sql;
    public static String user;
    public void TEntrance() {
        Scanner in = new Scanner(System.in);
        Conn c = new Conn(); // 创建本类对象
        Connection con = c.getConnection(); // 调用连接数据库的方法
        try {
            sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // 实例化Statement对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String TNo,TNos;//用于判断账号密码是否匹配
        String TPwd,TPwds;
        ResultSet resultSet;
        boolean flag1 = true,flag2 = false,flag3 = false;
        do {
            System.out.println("请输入工号：");
            TNo = in.next();
            System.out.println("请输入登录密码：");
            TPwd = in.next();
            try {
                resultSet = sql.executeQuery("select TNo,TPwd from view_teacherinfo_tno_tpwd");
                while (resultSet.next()){
                    TNos = resultSet.getString("TNo");
                    TPwds = resultSet.getString("TPwd");
                    if (flag1){
                        if (TNo.equals(TNos)){//学号匹配
                            flag2=true;
                            if (TPwd.equals(TPwds)){//密码匹配
                                flag1=false;//学号密码都正确
                                flag3=true;
                                System.out.println("登录成功！");
                                setUser(TNo);
                            }
                        }
                    }

                }
                if (!flag2){
                    System.out.println("工号错误");
                }
                if (!flag3){
                    System.out.println("密码错误");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (flag1);
        TeacherInfo x = new TeacherInfo(sql);
        TeacherSearch y = new TeacherSearch(sql);
        TeacherLook z = new TeacherLook(sql);
        int op;
        String sno;
        String choice;
        do {
            System.out.println("1.个人信息完善");
            System.out.println("2.学生问卷查询");
            System.out.println("3.学生问题查看");
            System.out.println("0.退出");
            op = in.nextInt();
            if (op == 1) {
                x.update();
            } else if (op == 2) {
                do {
                    System.out.println("请输入你要查询的学生学号：");
                    sno = in.next();
                    y.search(sno);
                    System.out.println("是否继续查询？Y/N");
                    choice = in.next();
                }while(choice.equals("Y")||choice.equals("y"));
            } else if (op == 3) {
                z.search();
            }
            else if(op == 0){}
            else {
                System.out.println("输入错误，请重新输入！");
            }
        }while (op!=0);
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        TeacherE.user = user;
    }
}
