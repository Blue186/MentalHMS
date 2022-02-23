package Main;

import Main.Student.Questionnaire;
import Main.Student.QuestionnaireType;
import Main.Student.StudentAsk;
import Main.Student.StudentInfo;
import Main.Teacher.SInstructor;
import sql.*;
import java.sql.*;
import java.util.*;
//主函数
public class StudentE {
    static Statement sql;
    public static String user;//定义用户，登录唯一。
    public void SEntrance(){
        Scanner in = new Scanner(System.in);
        Conn conn = new Conn();//创建本对象
        Connection connection = conn.getConnection();//调用连接数据库的方法
        try {
            sql = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        创建数据库连接
        String SNo,SNos;
        ResultSet resultSet;
        String SPwd,SPwds;
        boolean flag1 = true,flag2 = false,flag3 = false;
        do {
            System.out.println("请输入学号：");
            SNo = in.next();
            System.out.println("请输入登录密码：");
            SPwd = in.next();
            try {
                resultSet = sql.executeQuery("select *from view_studentinfo_sno_spwd;");//这里使用视图进行查询view_studentinfo_sno_spw是学生学号和密码的一个视图
                while (resultSet.next()){
                    SNos = resultSet.getString("SNo");
                    SPwds = resultSet.getString("SPwd");
                    if (flag1){
                        if (SNo.equals(SNos)){//学号匹配
                            flag2=true;
                            if (SPwd.equals(SPwds)){//密码匹配
                                flag1=false;//学号密码都正确
                                flag3=true;
                                System.out.println("登录成功！");
                                setUser(SNo);//确定登录的是哪一个用户，学号唯一嘛。
                                System.out.println(user);
                            }
                        }
                    }
                }
                if (!flag2){
                    System.out.println("学号错误");
                }
                if (!flag3){
                    System.out.println("密码错误");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (flag1);
        StudentInfo studentInfo = new StudentInfo(sql);
        StudentAsk studentAsk = new StudentAsk(sql);
        Questionnaire questionnaire = new Questionnaire(sql);
        QuestionnaireType questionnaireType = new QuestionnaireType(sql);
        SInstructor sInstructor = new SInstructor(sql);
//        界面选择面板
        boolean flag = true;
        while(flag){
            System.out.println("这里是学生端------");
            System.out.println("1.查看个人基本信息--");//个人信息里面可以做出修改操作
            System.out.println("2.心理问题提问--");//里面分为提问，查看和对老师的评价三部分
            System.out.println("3.填写心理问卷调查--");//里面因无其余选项
            System.out.println("4.问卷类型--");//查看老师的信息
            System.out.println("5.辅导员信息--");
            System.out.println("0.退出");
//            录入选项
            String choice = in.next();
            switch(choice){
                case "1":
                    studentInfo.Show();
                    System.out.println("Do you want to update the information?(Y/N)");
                    String ans = in.next();
                    if(ans.equals("Y")){
                        studentInfo.Update();
                    }else {
                        break;
                    }
                    break;
                case "2":
                    studentAsk.ChooseAsk();
                    break;
                case "3":
                    questionnaire.ChooseQ();
                    break;
                case "4":
                    questionnaireType.ShowQT();
                    break;
                case "5":
                    sInstructor.ShowSI();
                    break;
                default:
                    flag=false;
                    break;
            }
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        StudentE.user = user;
    }
}
