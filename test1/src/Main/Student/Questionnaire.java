package Main.Student;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//问卷调查结果表
public class Questionnaire {
    static Statement sql;
    private int Number;//问卷调查表的编号，个数
    private String Type;//问卷调查的类型
    private String TInformation;//老师的评语
    private int Score;//评分
    private String SName;
    private String TName;

    public Questionnaire(Statement sql) {
        Questionnaire.sql = sql;
    }

    public Questionnaire(String type,int number,String TInformation,int score,String sname,String tname){
        this.Type = type;
        this.Number = number;
        this.TInformation = TInformation;
        this.Score = score;
        this.SName = sname;
        this.TName = tname;
    }
    public void ChooseQ(){
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("请输入选择：");
            System.out.println("1.查看已有问卷");
            System.out.println("2.进行问卷");
            System.out.println("0.退出");
            choice = in.next();
            if (choice.equals("1")){
                ShowQ();
            }else if (choice.equals("2")){
                Write();
            }
        }while (!choice.equals("0"));
    }
    public void PrintQ(){
        System.out.println("问卷类型\t\t老师评估类容\t\t分数\t\t编号\t\t学生\t\t老师");
        System.out.println(Type+"\t\t"+TInformation+"\t\t"+Score+"\t\t"+Number+"\t\t"+SName+"\t\t"+TName);
    }
    public void ShowQ(){
        String Re = "select *from view_question_result_show";//问卷结果的表，多表联合。
        try {
            ResultSet resultSet = sql.executeQuery(Re);
            while(resultSet.next()){
                String Type = resultSet.getString("Type");//返回问卷的类型
                int QRNo = resultSet.getInt("QRNo");//返回问卷的个数，第几个
                String QREva = resultSet.getString("QREva");//返回问卷结果老师的评价
                int QGrade = resultSet.getInt("QGrade");//返回问卷的分数
                String SName = resultSet.getString("SName");//返回学生的名字
                String TName = resultSet.getString("TName");//返回老师的名字
                Questionnaire questionnaire = new Questionnaire(Type,QRNo,QREva,QGrade,SName,TName);
                questionnaire.PrintQ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//    填写问卷
    public void Write(){
        System.out.println("这里是问卷填写的代码");
        Scanner in = new Scanner(System.in);
        System.out.println("选择你想要的问卷类型：");
        System.out.println("1.学习类");
        String choice = in.next();//这里是选择的类型
        try {//先直接从这个表中拿信息了，看看能不能正常显示。
            ResultSet resultSet = sql.executeQuery("select *from qtype_study;");

            while (resultSet.next()){//获取问题和选项
                String QNum = resultSet.getString("QNum");
//                String QNo = resultSet.getString("QNo");
                String Question = resultSet.getString("Question");
                String AnswerA = resultSet.getString("AnswerA");
                String AnswerB = resultSet.getString("AnswerB");
                String AnswerC = resultSet.getString("AnswerC");
                String AnswerD = resultSet.getString("AnswerD");
                Print(QNum,Question,AnswerA,AnswerB,AnswerC,AnswerD);

            }

//            String Answer = in.next();

//            boolean res = sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+QRNo+"\","+"\""+QNum+"\","+"\""+Answer+"\""+")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Print(String n,String q,String a,String b,String c,String d){
        System.out.println("下面就是问题和选项---");
        System.out.println(n+":"+q);
        System.out.println("A:"+a+","+"B:"+b+","+"C:"+c+","+"D:"+d);
    }
}
