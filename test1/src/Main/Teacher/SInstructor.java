package Main.Teacher;

import Main.StudentE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//辅导员信息表格
public class SInstructor {
    StudentE studentE = new StudentE();
    String User = studentE.getUser();//每个学生对应的老师。
    static Statement sql;
    private String INumber;//工号
    private String IName;//姓名
    private String College;//所属学院
    private String Phone;//手机号
    private String Email;//邮箱

    public SInstructor(Statement sql) {
        SInstructor.sql = sql;
    }

    public SInstructor(String INumber,String IName,String college,String phone,String email){
        this.INumber = INumber;
        this.IName = IName;
        this.College = college;
        this.Phone = phone;
        this.Email = email;
    }
    public void PrintSI(){
        System.out.println("工号\t\t姓名\t\t所属学院\t\t手机号\t\t邮箱\t\t");
        System.out.println(INumber+"\t\t"+IName+"\t\t"+College+"\t\t"+Phone+"\t\t"+Email);
    }
    public void ShowSI(){
        String Instr = null;
        try {
            ResultSet resultSet = sql.executeQuery("select SInstructor from studentinfo where SNo="+"\""+User+"\";");//查询当前学生的辅导员。不需要视图，因为我么你直接获取其所有信息，也没有多表联合查询
            while (resultSet.next()){
                Instr = resultSet.getString("SInstructor");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Re = "select *from instructorinfo where SInstructor="+"\""+Instr+"\";";
        try {
            ResultSet resultSet = sql.executeQuery(Re);
            while (resultSet.next()){
                String SInstructor = resultSet.getString("SInstructor");
                String IName = resultSet.getString("IName");
                String College = resultSet.getString("College");
                String IPNo = resultSet.getString("IPNo");
                String Email = resultSet.getString("Email");
                SInstructor sInstructor = new SInstructor(SInstructor,IName,College,IPNo,Email);
                sInstructor.PrintSI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
