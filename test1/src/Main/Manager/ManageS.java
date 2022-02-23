package Main.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//管理学生信息表，进行增删改查
public class ManageS {
    static Statement sql;
    boolean res = true;
    Scanner in = new Scanner(System.in);

    public ManageS(Statement sql) {
        ManageS.sql =sql;
    }
//    选择列表
    public void ChooseManage(){
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("请输入需要的选项：");
            System.out.println("1.修改学生信息");
            System.out.println("2.删除学生");
            System.out.println("3.新增新学生");
            System.out.println("0.退出");
            choice = in.next();
            switch (choice) {
                case "1":
                    Update();
                    break;
                case "2":
                    Delete();
                    break;
                case "3":
                    Add();
                    break;
                default:
                    System.out.println("输入选项错误");
                    break;
            }
        }while (!choice.equals("0"));
    }
//判断学生是否存在于表中
    public String select(){
        String SNo,SNos;
        boolean flag=true;
        do {
            System.out.println("输入你想操作的学生学号：");
            SNo = in.next();
            try {
                ResultSet resultSet = sql.executeQuery("select SNo from view_studentinfo_sno_spwd;");//这个视图是学生学号和密码的视图，但是可以使用哦。
                while (resultSet.next()){
                    SNos = resultSet.getString("SNo");
                    if (flag){
                        if (SNo.equals(SNos)){
                            flag = false;
                            System.out.println("学号选择正确");
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
        return SNo;
    }
//    更新
    public void Update(){//更新学生信息
        String choice;
        String Modify = null;
        String SNo = select();
        do {
            System.out.println("请输入你想修改的类容");
//            System.out.println("1.学号");
            System.out.println("1.姓名");
            System.out.println("2.生日");
            System.out.println("3.手机号");
            System.out.println("4.学院专业");
            System.out.println("5.年级");
            System.out.println("6.辅导员");
            System.out.println("7.登录密码");
            System.out.println("0.退出/取消");
            choice = in.next();
            switch (choice) {
                case "1":
                    Modify = "SName";
                    break;
                case "2":
                    Modify = "Birth";
                    break;
                case "3":
                    Modify = "SPNo";
                    break;
                case "4":
                    Modify = "Major";
                    break;
                case "5":
                    Modify = "Grade";
                    break;
                case "6":
                    Modify = "SInstructor";
                    break;
                case "7":
                    Modify = "SPwd";
                    break;
                default:
                    System.out.println("输入错误！");
                    break;
            }
            System.out.println("请输入修改内容：");
            String content = in.next();
            try {
                sql.execute("begin ");
                sql.execute("select *from studentinfo where SNo= "+"\""+SNo+"\""+"for update ");
                res = sql.execute("update studentinfo set "+Modify+"="+"\""+content+"\""+"where SNo="+"\""+SNo+"\";");
//                sql.execute("commit ;");
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
//    删除
    public void Delete(){
        String SNo;
        String conf;
        SNo = select();
        do {
            System.out.println("确定吗？（Y/N）");
            conf = in.next();
            if (conf.equals("Y")){
                try {
                    sql.execute("begin ");
                    sql.execute("select *from studentinfo where SNo= "+"\""+SNo+"\""+"for update ");
                    res = sql.execute("delete from studentinfo where SNo="+"\""+SNo+"\";");
                    sql.execute("commit ;");
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
//    添加学生
    public void Add(){
        String SNo,SName,Birth,SPNo,Major,Grade,SInstructor,SPwd;
        System.out.println("请输入学号：");
        SNo = in.next();
        System.out.println("请输入姓名：");
        SName = in.next();
        System.out.println("请输入生日：");
        Birth = in.next();
        System.out.println("请输入手机号：");
        SPNo = in.next();
        System.out.println("请输入学院专业：");
        Major = in.next();
        System.out.println("请输入年级：");
        Grade = in.next();
        System.out.println("请输入辅导员：");
        SInstructor = in.next();
        System.out.println("请设置密码：");
        SPwd = in.next();
        try {
            boolean ret = sql.execute("insert into studentinfo values ("+"\""+SNo+"\","+"\""+SName+"\","+"\""+Birth+"\","+"\""+SPNo+"\","+"\""+Major+"\","+"\""+Grade+"\","+"\""+SInstructor+"\","+"\""+SPwd+"\""+");");
            if (!ret){
                System.out.println("插入数据成功");
            }else {
                System.out.println("失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
