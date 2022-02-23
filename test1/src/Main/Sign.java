package Main;

import java.util.Scanner;

public class Sign {
    public static void main(String[] args) {
        StudentE studentE = new StudentE();
        TeacherE teacherE = new TeacherE();
        ManagerE managerE = new ManagerE();
        Scanner in = new Scanner(System.in);
        String choice ;
        do {
            System.out.println("请选择学生端/老师端");
            System.out.println("1.学生登录");
            System.out.println("2.老师登录");
            System.out.println("3.管理员登录");
            System.out.println("0.退出");
            choice = in.next();
            if (choice.equals("1")){
                studentE.SEntrance();
            }else if (choice.equals("2")){
                teacherE.TEntrance();
            }else if (choice.equals("3")){
                managerE.MEntrance();
            }
        }while(choice.equals("0"));
    }
}
