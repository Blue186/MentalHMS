package Main.Teacher;

import sql.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherLook {
    Statement sql;
    ResultSet res;
    boolean rest;
    String x;
    public TeacherLook(Statement sql){
        this.sql = sql;
    }
    public void search(){
        try {
            // 执行SQL语句，返回结果集
            res = sql.executeQuery("select * from sask where TNo="+"\""+ Conn.user+"\""+"and TAnswer is null");
            if(!res.first()){
                System.out.println("暂无问题");
            }
            else {
                int i = 0;
                res.previous();
                System.out.print("学生学号               ");
                System.out.print("问题编码            ");
                System.out.print("提问时间                                         ");
                System.out.print("问题内容                                                                             ");
                System.out.println();
                while (res.next()) { // 如果当前语句不是最后一条则进入循环
                    String question = res.getString("SAInfo");
                    String name = res.getString("SNo");
                    String time = res.getString("Time");
                    String ano = res.getString("Ano");
                    String Ano = res.getString("SANo");
                    String anser = res.getString("TAnswer");
                    if (anser != null) continue;
                    else {
                        if (ano.equals("1")) System.out.print("**********             ");
                        else System.out.print(name + "             ");
                        System.out.print(Ano + "            ");
                        System.out.print(time + "  ");
                        System.out.print(question + "  ");
                        System.out.println();
                        i++;
                    }
                }
                System.out.println("共查询到" + i + "条数据");
                String num, con;
                Scanner in = new Scanner(System.in);
                do {
                    System.out.println("请输入您想回答问题的编号");
                    num = in.next();
                    System.out.println("请输入您的回答");
                    con = in.next();
                    try {
                        rest = sql.execute("update sask set TAnswer = \"" + con + "\"" + "where SANo = \"" + num + "\";");
                        if (rest == false) System.out.println("评价成功");
                        else System.out.println("评价失败");
                        System.out.println("是否继续回答？Y/N");
                        x = in.next();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } while (x.equals("Y") || x.equals("y"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
