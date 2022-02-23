package Main.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//问卷调查表的类型
public class QuestionnaireType {
    static Statement sql;
    private String Type;//；类型
    private int Num;

    public QuestionnaireType(Statement sql) {
        QuestionnaireType.sql = sql;
    }

    public QuestionnaireType(String type,int num){
        this.Type = type;
        this.Num = num;
    }
    public void PrintQT(){
        System.out.println("问卷类型\t\t类型编号");
        System.out.println(Type+"\t\t"+Num);
    }
    public void ShowQT(){
        String Re = "select *from qtype";
        try {
            ResultSet resultSet = sql.executeQuery(Re);
            while(resultSet.next()){
                String Type = resultSet.getString("Type");
                int QNo = resultSet.getInt("QNo");
                QuestionnaireType questionnaireType = new QuestionnaireType(Type,QNo);
                questionnaireType.PrintQT();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
