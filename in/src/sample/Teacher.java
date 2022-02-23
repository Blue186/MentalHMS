package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher {

    @FXML
    private Label stime3;

    @FXML
    private Label sept;

    @FXML
    private TextField mail;

    @FXML
    private Label question2;

    @FXML
    private TextArea answer2;

    @FXML
    private Label birth;

    @FXML
    private Button update;

    @FXML
    private Label sno3;

    @FXML
    private Label sno2;

    @FXML
    private Button update3;

    @FXML
    private Label sname3;

    @FXML
    private Label sname2;

    @FXML
    private Label evalute3;

    @FXML
    private ComboBox<String> question1;

    @FXML
    private ComboBox<String> evalute;

    @FXML
    private Button select3;

    @FXML
    private Label time2;

    @FXML
    private Button fix;

    @FXML
    private Label sno;

    @FXML
    private TextField phone;

    @FXML
    private Label name;

    @FXML
    private TextField worktime;

    @FXML
    private Button submit_b;

    @FXML
    private Button select_b;

    @FXML
    void up2(ActionEvent event) {
        String Re = "select SANo from sask where TNo";
        try {
            ResultSet resultset = Teacher_log.sql.executeQuery(Re+"="+"\""+Teacher_log.name+"\" and TAnswer is null");
            while(resultset.next()){
                String SAno = resultset.getString("SANo");
                question1.getItems().addAll(
                        SAno
                );
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新成功" );
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新失败");
            alert.showAndWait();
        }
    }
    static String id;
    @FXML
    void select2(ActionEvent event) {
        String Re = "select *from view_student_show_ask where SANo";
        try {
            id = question1.getValue();
            ResultSet resultset = Teacher_log.sql.executeQuery(Re+"="+"\""+id+"\";");
            while(resultset.next()){
                String SAInfo = resultset.getString("SAInfo");
                String Time = resultset.getString("Time");
                String TNo = resultset.getString("TName");
                String sno = resultset.getString("SNo");
                int ano = resultset.getInt("Ano");
                question2.setText(SAInfo);
                time2.setText(Time);
                if(ano == 1) sno2.setText("***********");
                else sno2.setText(sno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void submit(ActionEvent event) {
        try {
            boolean res = Teacher_log.sql.execute("update sask set TAnswer = \""+answer2.getText()+"\" where SANo = \""+id+"\"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("发布成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("发布失败");
            alert.showAndWait();
        }

    }

    @FXML
    void get_inf(ActionEvent event) {
        try {
            ResultSet resultSet = Teacher_log.sql.executeQuery("select *from teacherinfo where TNo =" + "\"" + Teacher_log.name + "\"");
            while(resultSet.next()) {
                String TNo = resultSet.getString("TNo");
                String TName = resultSet.getString("TName");
                String TBirth = resultSet.getString("TBirth");
                String TEmail = resultSet.getString("TEmail");
                String Tphone = resultSet.getNString("Tphone");
                String TCollege = resultSet.getString("TCollege");
                String TWork = resultSet.getString("TWork");
                sno.setText(TNo);
                phone.setText(Tphone);
                name.setText(TName);
                birth.setText(TBirth);
                sept.setText(TCollege);
                mail.setText(TEmail);
                worktime.setText(TWork);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新成功");
            alert.showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新失败");
            alert.showAndWait();
        }
    }

    @FXML
    void fixed(ActionEvent event) {
        String number = phone.getText();
        String youxiang  = mail.getText();
        String work = worktime.getText();
        try {
            boolean res = Teacher_log.sql.execute("update teacherinfo set Tphone = \""+number+"\" where Tno = \""+Teacher_log.name+"\"");
            boolean res1 = Teacher_log.sql.execute("update teacherinfo set TEmail = \""+youxiang+"\" where Tno = \""+Teacher_log.name+"\"");
            boolean res2 = Teacher_log.sql.execute("update teacherinfo set TWork = \""+work+"\" where Tno = \""+Teacher_log.name+"\"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改失败");
            alert.showAndWait();
        }
    }
    @FXML
    void toup3(ActionEvent event) {
        String Re = "select TENo from view_student_evalute_show where TNo";
        try {
            ResultSet resultset = Teacher_log.sql.executeQuery(Re+"="+"\""+Teacher_log.name+"\";");
            while(resultset.next()){
                String SAno = resultset.getString("TENo");
                evalute.getItems().addAll(
                        SAno
                );
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新成功" );
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新失败");
            alert.showAndWait();
        }
    }

    static String id2;
    @FXML
    void toselect3(ActionEvent event) {
        String Re = "select *from view_student_evalute_show where TENo";
        try {
            id2 = evalute.getValue();
            ResultSet resultset = Teacher_log.sql.executeQuery(Re+"="+"\""+id2+"\";");
            while(resultset.next()){
                String SAInfo = resultset.getString("Evalute");
                String Time = resultset.getString("TETime");
                String sno = resultset.getString("SNo");
                int ano = resultset.getInt("TAno");
                evalute3.setText(SAInfo);
                stime3.setText(Time);
                if(ano == 1) sno3.setText("***********");
                else sno3.setText(sno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("teacher.fxml"));
        primaryStage.setTitle("重庆邮电大学心理健康咨询系统");
        primaryStage.setScene(new Scene(root, 1267, 796
        ));
        primaryStage.show();
    }

    @FXML
    private Label a1;

    @FXML
    private Label a2;

    @FXML
    private Label a3;

    @FXML
    private Label a4;

    @FXML
    private Label a5;
    @FXML
    private Label w1;

    @FXML
    private Label w2;

    @FXML
    private Label w3;

    @FXML
    private Label w4;

    @FXML
    private Label w5;
    @FXML
    private Button submit_w;
    @FXML
    private TextField sno_w;
    @FXML
    private TextArea evalute_w;

    @FXML
    private TextField score_w;
    @FXML
    private Button up_w;
    @FXML
    private ComboBox<String> wno;
    @FXML
    void toup_w(ActionEvent event) {
        String Re = "select QRNo from qresult where SNo";
        try {
            ResultSet resultset = Teacher_log.sql.executeQuery(Re+"="+"\""+sno_w.getText()+"\";");
            while(resultset.next()){
                String SAno = resultset.getString("QRNo");
                System.out.println(SAno);
                wno.getItems().addAll(
                        SAno
                );
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新成功" );
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新失败");
            alert.showAndWait();
        }
    }
    String QRNo;
    @FXML
    void toselect_w(ActionEvent event) {
        try {//先直接从这个表中拿信息了，看看能不能正常显示。
            String Question;
            String QAnswer;
            QRNo = wno.getValue();
            ResultSet resultSet = Teacher_log.sql.executeQuery("select * from view_question_teacher where QRNo = \""+QRNo+"\"");
            resultSet.next();
            Question = resultSet.getString("Question");
            QAnswer =resultSet.getString("QAnswer");
            w1.setText("1"+Question);
            a1.setText(QAnswer);
            resultSet.next();
            Question = resultSet.getString("Question");
            QAnswer =resultSet.getString("QAnswer");
            w2.setText("2"+Question);
            a2.setText(QAnswer);
            resultSet.next();
            Question = resultSet.getString("Question");
            QAnswer =resultSet.getString("QAnswer");
            w3.setText("3"+Question);
            a3.setText(QAnswer);
            resultSet.next();
            Question = resultSet.getString("Question");
            QAnswer =resultSet.getString("QAnswer");
            w4.setText("4"+Question);
            a4.setText(QAnswer);
            resultSet.next();
            Question = resultSet.getString("Question");
            QAnswer =resultSet.getString("QAnswer");
            w5.setText("2"+Question);
            a5.setText(QAnswer);
            resultSet = Teacher_log.sql.executeQuery("select * from qresult where QRNo = \"" + QRNo+ "\"");
            while (resultSet.next())
            {
                String grade = resultSet.getString("QGrade");
                String eva = resultSet.getString("QREva");
                evalute_w.setText(eva);
                score_w.setText(grade);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("更新失败");
            alert.showAndWait();
        }
    }

    @FXML
    void tosubmit_w(ActionEvent event) {
        String evalue = evalute_w.getText();
        String sc = score_w.getText();
        try {
            Teacher_log.sql.execute("update qresult set QREva = \""+evalue+"\" where QRNo = \""+QRNo+"\"");
            Teacher_log.sql.execute("update qresult set QGrade = \""+sc+"\" where QRNo = \""+QRNo+"\"");
            Teacher_log.sql.execute("update qresult set TNo = \""+Teacher_log.name+"\" where QRNo = \""+QRNo+"\"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("提交成功");
            alert.showAndWait();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("提交失败");
            alert.showAndWait();
        }
    }
}

