package Main.Student;
import Main.StudentE;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//学生基本信息表格
public class StudentInfo {
    Statement sql;
    StudentE studentE = new StudentE();
    String User = studentE.getUser();//相当于获取了登录的学生的学号，即便指定了唯一用户！
    boolean res;//用于判断是否连接上数据库
    private String StudentName;//学生姓名
    private String StudentNumber;//学生学号
    private String StudentPhoneNumber;//学生手机号
    private String StudentMajor;//学生学院专业
    private String StudentGrade;//学生年级
    private String StudentInstructor;//学生辅导员
    private String Birth;
    private String Password;//登录密码
//    学生生日
//    将信息赋值给学生对象的函数。
    public StudentInfo(String studentName,String studentNumber,String studentPhoneNumber,String studentMajor,String studentGrade,String studentInstructor,String birth,String password){
        this.StudentName = studentName;
        this.StudentNumber = studentNumber;
        this.StudentPhoneNumber = studentPhoneNumber;
        this.StudentMajor = studentMajor;
        this.StudentGrade = studentGrade;
        this.StudentInstructor = studentInstructor;
        this.Birth = birth;
        this.Password = password;
    }
    public void Print(){
        System.out.println(StudentName+"\t"+StudentNumber+"\t"+StudentPhoneNumber+"\t"+StudentMajor+"\t"+StudentGrade+"\t"+StudentInstructor+"\t"+Birth+"\t"+"********");
    }
    public  void Show(){
        String Re = "select *from view_studentinfo_show where SNo=";//查询操作的话就从视图中查找,将辅导员的编号转换为姓名。
        try {
            ResultSet resultSet = sql.executeQuery(Re+"\""+User+"\";");
            while (resultSet.next()){
                String SNo = resultSet.getString("SNo");
                String SName = resultSet.getString("SName");
                String Birth = resultSet.getString("Birth");
                String SPNo = resultSet.getString("SPNo");
                String Major = resultSet.getNString("Major");
                String Grade = resultSet.getString("Grade");
                String IName = resultSet.getString("IName");
                String SPwd = resultSet.getString("SPwd");//密码
                String NewSPwd = SPwd;
                try {//加密用,使用MD5加密算法
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    BASE64Encoder base64Encoder = new BASE64Encoder();
                    NewSPwd = base64Encoder.encode(md5.digest(SPwd.getBytes()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                StudentInfo studentInfo = new StudentInfo(SNo,SName,Birth,SPNo,Major,Grade,IName,NewSPwd);
                System.out.println("学号\t\t姓名\t\t生日\t\t手机号\t\t学院专业\t\t年级\t\t辅导员\t\t密码");
                studentInfo.Print();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Update(){
        Scanner in = new Scanner(System.in);
        int choice;
        String Modify;
        do {
            System.out.println("请输入你想修改的类容--");
            System.out.println("1.手机号");
            System.out.println("0.退出/取消");
            choice = in.nextInt();
            if(choice==1){
                Modify = "SPNo";
                System.out.println("请输入修改内容：");
                String content = in.next();
                try {
                    sql.execute("begin;");
                    sql.execute("select *from studentinfo where SNo= "+"\""+User+"\"");
                    res = sql.execute("update studentinfo set "+Modify+"=\""+content+"\""+"where SNo= "+"\""+User+"\"");
                    sql.execute("commit ;");
                    if (!res){
                        System.out.println("更新成功");
                    }else {
                        System.out.println("更新失败");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("输入错误，请从新输入：");
            }
        }while (choice!=0);
    }
    public StudentInfo(Statement sql) {
        this.sql = sql;
    }
}
