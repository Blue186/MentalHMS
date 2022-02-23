package Main.Student;

import Main.StudentE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//学生提问表单
public class StudentAsk {
    StudentE studentE = new StudentE();
    String User = studentE.getUser();//获取当前学生。
    static Statement sql;
    boolean res;
    private String StudentNumber;//学生学号
    private String Information;//学生提问的内容
    private String Time;//存时间
    private String Ano;//是否匿名
    private String TeacherNumber;//老师姓名
    private int Number;//编号
    private String Answer;//老师的回答

    public StudentAsk(Statement sql) {
        StudentAsk.sql = sql;
    }
    public StudentAsk(String studentNumber,String information,String time,String ano,String teacherNumber, int number,String answer){
        this.StudentNumber = studentNumber;
        this.Information = information;
        this.Time = time;
        this.Ano = ano;
        this.TeacherNumber = teacherNumber;
        this.Number = number;
        this.Answer = answer;
    }
//进入这个界面后选择需要做的事情
    public void ChooseAsk(){
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            System.out.println("请选择需要的选项--");
            System.out.println("1.查看已提交问题");
            System.out.println("2.发布新问题");
            System.out.println("3.评价老师");
            System.out.println("0.退出");
            choice = in.nextInt();
            if (choice==1){
                ShowAsk();
            }else if (choice==2){
                WriteAsk();
            }else if (choice==3){
                Evalute();
            }
        }while (choice!=0);
    }
//    查看信息函数
    public void ShowAsk(){
        String Re = "select *from view_student_show_ask where SNo";//视图中查询信息。
        try {
            ResultSet resultset = sql.executeQuery(Re+"="+"\""+User+"\";");
            while(resultset.next()){
                String SNo = resultset.getString("SNo");
                String SAInfo = resultset.getString("SAInfo");
                String Time = resultset.getString("Time");
                String Ano = resultset.getString("Ano");
                String TName = resultset.getString("TName");
                int SANo = resultset.getInt("SANo");
                String TAnswer = resultset.getString("TAnswer");
                StudentAsk studentAsk = new StudentAsk(SNo,SAInfo,Time,Ano,TName,SANo,TAnswer);
                studentAsk.PrintAsk();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void PrintAsk(){
        System.out.println("学生学号\t\t发布信息\t\t发布时间\t\t是否匿名\t\t老师工号\t\t编号\t\t老师回答");
        System.out.println(StudentNumber+"\t\t"+Information+"\t\t"+Time+"\t\t"+Ano+"\t\t"+TeacherNumber+"\t\t"+Number+"\t\t"+Answer);
    }
//    发布心理健康问题
    public void WriteAsk(){
        Scanner in = new Scanner(System.in);
        String choice;
        System.out.println("(Y/N)?");
        do {
            choice = in.next();
            if (choice.equals("Y")){
//                System.out.println("请输入学号：");
                String sno = User;
                System.out.println("请输入提问的内容：");
                String info = in.next();
                System.out.println("请选择指定老师：");
                String teacher = in.next();
                System.out.println("是否匿名：");
                String aano = in.next();
                System.out.println("输入第几个");
                int ano = in.nextInt();
                try {
                    Date date = new Date();//获取当前时间
                    SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time=sim.format(date.getTime());
                    res = sql.execute("insert into sask(SNo,SAInfo,Time,Ano,TNo,SANo,TAnswer) values ("+"\""+sno+"\","+"\""+info+"\","+"\""+time+"\","+"\""+aano+"\","+"\""+teacher+"\","+"\""+ano+"\","+"\""+"老师的回答"+"\");");
                    if (!res){
                        System.out.println("发布成功！");
                    }else {
                        System.out.println("发布失败");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (!choice.equals("N")){
                System.out.println("输入错误，请重新输入选择：");
            }
        }while(!choice.equals("N"));
    }
//    学生对老师的评价
    public void Evalute(){
        Scanner in = new Scanner(System.in);
//        String TNo,SNo;
//        boolean res;
//        try {
//            ResultSet resultSet = sql.executeQuery("select SNo,TNo from sask;");
//            while (resultSet.next()){
//                SNo = resultSet.getString("SNo");
//                if (User.equals(SNo)){
//                    TNo = resultSet.getString("TNo");//当学号与用户相等时，其指定的心理老师的工号
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        String choice;
        do {
            System.out.println("请输入选择：");
            System.out.println("1.查看已有评论");
            System.out.println("2.进行评论");
            System.out.println("0.退出");
            choice = in.next();
            if (choice.equals("1")){
                ShowE();
            }else if (choice.equals("2")){
                Insert();
            }
        }while (!choice.equals("0"));
    }
//    选择老师进行评价
    public void Insert(){
        Scanner in = new Scanner(System.in);
        System.out.println("第几个");
        String Num= in.next();
        System.out.println("请输入你的评价：");
        String Evalute = in.next();
        Date date = new Date();//获取当前时间
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sim.format(date.getTime());
        System.out.println("是否匿名？（1/2）");
        String TAno = in.next();
        System.out.println("选择评价的老师：");
        String TNo = in.next();
        try {
            res = sql.execute("insert into tevalute values ("+"\""+Num+"\","+"\""+Evalute+"\","+"\""+time+"\","+"\""+TAno+"\","+"\""+User+"\","+"\""+TNo+"\");");
            if (!res){
                System.out.println("评价成功");
            }else {
                System.out.println("评价失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    查看已有的评价
    public void ShowE(){
        try {
            ResultSet resultSet = sql.executeQuery("select * from view_student_evalute_show where SNo="+"\""+User+"\""+";");//视图中查询，多表嘛
            while (resultSet.next()){
                int TENo = resultSet.getInt("TENo");
                String Evalute = resultSet.getNString("Evalute");
                String TETime = resultSet.getNString("TETime");
                String TAno = resultSet.getNString("TAno");
                String TName = resultSet.getString("TName");
                System.out.println("编号\t\t评价类容\t\t评价时间\t\t是否匿名\t\t老师");
                System.out.println(TENo+"\t\t"+Evalute+"\t\t"+TETime+"\t\t"+TAno+"\t\t"+TName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}