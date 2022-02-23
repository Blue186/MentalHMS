package Main.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherSearch {
    Statement sql;
    ResultSet res;
    String x;
    public TeacherSearch(Statement sql){
        this.sql = sql;
    }
    public void search(String sno){
        try {
            // 执行SQL语句，返回结果集
            res = sql.executeQuery("select * from view_student_show_ask where SNo="+sno);//从视图中查找
            if(!res.first()){
                System.out.println("查无此人！");
            }
            res.previous();
            while (res.next()) { // 如果当前语句不是最后一条则进入循环
                String name = res.getString("SAInfo");
                System.out.println("提问的类容：" + name); // 将列值输出
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
