package Main.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//管理老师信息，进行增删改查
public class ManageT{
    static Statement sql;
    boolean res;
    Scanner in = new Scanner(System.in);

    public ManageT(Statement sql) {
        ManageT.sql = sql;
    }


    public void ChooseM(){
        String choice;
        do {
            System.out.println("请输入你的选择：");
            System.out.println("1.修改老师信息");
            System.out.println("2.删除老师信息");
            System.out.println("3.添加新老师");
            System.out.println("0.退出");
            choice = in.next();
            switch (choice) {
                case "1":
                    Update();
                    break;
                case "2":
                    DeleteT();
                    break;
                case "3":
                    AddT();
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }while (!choice.equals("0"));
    }
    public String select(){
        String TNo,TNos;
        boolean flag=true;
        do {
            System.out.println("输入你想操作的老师工号：");
            TNo = in.next();
            try {
                ResultSet resultSet = sql.executeQuery("select TNo from view_teacherinfo_tno_tpwd;");//从老师的工号和密码视图中查找。
                while (resultSet.next()){
                    TNos = resultSet.getString("TNo");
                    if (flag){
                        if (TNo.equals(TNos)){
                            flag = false;
                            System.out.println("工号选择正确");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (flag){
                System.out.println("请重新输入学号：");
            }
        }while (flag);
        return TNo;
    }

    public void Update(){
        String choice;
        String Modify=null;
        String TNo = select();
        do {
            System.out.println("请输入你想修改的类容");
            System.out.println("1.姓名");
            System.out.println("2.生日");
            System.out.println("3.电话");
            System.out.println("4.邮箱");
            System.out.println("5.学院");
            System.out.println("6.工作时间");
            System.out.println("7.密码");
            System.out.println("0.退出/取消");
            choice = in.next();
            switch (choice) {
                case "1":
                    Modify = "TName";
                    break;
                case "2":
                    Modify = "TBirth";
                    break;
                case "3":
                    Modify = "TPhone";
                    break;
                case "4":
                    Modify = "TEmail";
                    break;
                case "5":
                    Modify = "TCollege";
                    break;
                case "6":
                    Modify = "TWork";
                    break;
                case "7":
                    Modify = "TPwd";
                    break;
                default:
                    System.out.println("输入错误！");
                    break;
            }
            System.out.println("请输入修改内容：");
            String content = in.next();
            try {
                sql.execute("begin ");
                sql.execute("select *from teacherinfo where TNo="+"\""+TNo+"\""+" for update ");
                res = sql.execute("update teacherinfo set "+Modify+"="+"\""+content+"\""+"where TNo="+"\""+TNo+"\";");
                sql.execute("commit;");
                if (!res){
                    System.out.println("更新成功");
                }else {
                    System.out.println("更新失败");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (choice.equals("0"));
    }
    public void DeleteT(){
        String TNo = select();
        String conf;
        do {
            System.out.println("确定吗？（Y/N）");
            conf = in.next();
            if (conf.equals("Y")){
                try {
                    res = sql.execute("delete from teacherinfo where TNo="+"\""+TNo+"\";");
                    if (!res){
                        System.out.println("删除成功！");
                    }else {
                        System.out.println("删除失败！");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }while (conf.equals("N"));
    }
    public void AddT(){
        String TNo,TName,TBirth,TPhone,TEmail,TCollege,TWork,TPwd;
        System.out.println("1.输入工号：");
        TNo = in.next();
        System.out.println("2.输入姓名：");
        TName = in.next();
        System.out.println("3.输入生日：");
        TBirth = in.next();
        System.out.println("4.输入手机号：");
        TPhone = in.next();
        System.out.println("5.输入邮箱：");
        TEmail = in.next();
        System.out.println("6.输入所属学院：");
        TCollege = in.next();
        System.out.println("7.输入工作时间：");
        TWork = in.next();
        System.out.println("8.设置密码：");
        TPwd = in.next();
        try {
            boolean ret = sql.execute("insert into teacherinfo values ("+"\""+TNo+"\","+"\""+TName+"\","+"\""+TBirth+"\","+"\""+TPhone+"\","+"\""+TEmail+"\","+"\""+TCollege+"\","+"\""+TWork+"\","+"\""+TPwd+"\""+")");
            if (!ret){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
