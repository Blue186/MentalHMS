package Main;

import Main.Manager.ManageS;
import Main.Manager.ManageT;
import sql.Conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManagerE {
    static Statement sql;
    public void MEntrance() {
        Scanner in = new Scanner(System.in);
        System.out.println("这里是管理员登录窗口");
        Conn conn = new Conn();
        Connection connection = conn.getConnection();
        try {
            sql = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Root,Root1;
        String Pwd,Pwd1;
        ResultSet resultSet;
        boolean flag1 = true,flag2 = false,flag3 = false;
        do {
            System.out.println("输入管理员账号：");
            Root = in.next();
            System.out.println("输入管理员密码：");
            Pwd = in.next();
            try {
                resultSet = sql.executeQuery("select Root,Pwd from root;");
                while (resultSet.next()){
                    Root1 = resultSet.getString("Root");
                    Pwd1 = resultSet.getString("Pwd");
                    if (flag1){
                        if (Root.equals(Root1)){
                            flag2 = true;
                            if (Pwd.equals(Pwd1)){
                                flag1 = false;
                                flag3 = true;
                                System.out.println("登录成功！");
                            }
                        }
                    }
                }
                if (!flag2){
                    System.out.println("账号错误");
                }
                if (!flag3){
                    System.out.println("密码错误");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (flag1);
        ManageS manageS = new ManageS(sql);
        ManageT manageT = new ManageT(sql);
        String choice;
        do {
            System.out.println("请选择需要查看的对象：");
            System.out.println("1.学生");
            System.out.println("2.老师");
            choice = in.next();
            if (choice.equals("1")){
                manageS.ChooseManage();
            }else if (choice.equals("2")){
                manageT.ChooseM();
            }
        }while (choice.equals("0"));
    }
}
