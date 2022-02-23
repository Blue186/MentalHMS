package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javafx.application.Application.launch;

public class Student {


    @FXML
    private CheckBox annoy;

    @FXML
    private Label sept;

    @FXML
    private Label teacher;

    @FXML
    private ComboBox<String> select;

    @FXML
    private Button fix;

    @FXML
    private Button update;

    @FXML
    private Button submit;

    @FXML
    private TextArea question;

    @FXML
    private Label sno;

    @FXML
    private TextField phone;

    @FXML
    private Label grade;

    @FXML
    private Label name;

    @FXML
    private Label birth;

    @FXML
    private Tab infobutton;

    @FXML
    private Button tup;

    @FXML
    private Label Ino;

    @FXML
    private Label iphone;

    @FXML
    private Label iname;

    @FXML
    private Label imail;

    @FXML
    private Label icollege;

    @FXML
    void fixed(ActionEvent event) {
        String number = phone.getText();
        try {
            boolean res = Student_log.sql.execute("update studentinfo set SPNo = \""+number+"\" where Sno = \""+Student_log.name+"\"");
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
    void isannoy(ActionEvent event) {
    }

    @FXML
    void tosubmit(ActionEvent event) {
        int x;
        String que = question.getText();
        if(annoy.isSelected()==true) x = 1;
        else x = 0;
        try {
            Date date = new Date();//获取当前时间
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sim.format(date.getTime());
            String[] Str3Array = select.getValue().split("\\|");
            boolean res = Student_log.sql.execute("insert into sask(SNo,SAInfo,Time,TNo,ANo) " +
                    "values ("+"\""+Student_log.name+"\","+"\""+question.getText()+"\","+"\""+time+"\""+","+"\""+Str3Array[0]+"\","+"\""+x+"\""+")");
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
            ResultSet resultSet = Student_log.sql.executeQuery("select *from view_studentinfo_show where SNo=" + "\"" + Student_log.name + "\"");
            while(resultSet.next()) {
                String SNo = resultSet.getString("SNo");
                String SName = resultSet.getString("SName");
                String Birth = resultSet.getString("Birth");
                String SPNo = resultSet.getString("SPNo");
                String Major = resultSet.getNString("Major");
                String Grade = resultSet.getString("Grade");
                sno.setText(SNo);
                phone.setText(SPNo);
                name.setText(SName);
                birth.setText(Birth);
                sept.setText(Major);
                grade.setText(Grade);
            }
            resultSet = Student_log.sql.executeQuery("select * from instructorinfo where SInstructor = (select SInstructor from " +
                     "studentinfo where SNo =\"" + Student_log.name + "\")");
            while(resultSet.next()) {
                String INo = resultSet.getString("SInstructor");
                String IName = resultSet.getString("IName");
                String College = resultSet.getString("College");
                String IPNo = resultSet.getString("IPNo");
                String Email = resultSet.getNString("Email");
                Ino.setText(INo);
                iname.setText(IName);
                icollege.setText(College);
                iphone.setText(IPNo);
                imail.setText(Email);
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
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
        primaryStage.setTitle("重庆邮电大学心理健康咨询系统");
        primaryStage.setScene(new Scene(root, 1267, 796
        ));
        primaryStage.show();
    }
    @FXML
    void up(ActionEvent event) {
        try {
            ResultSet resultSet = Student_log.sql.executeQuery("select *from teacherinfo");
            while(resultSet.next()) {
                String TNo = resultSet.getString("TNo");
                String TName = resultSet.getString("TName");
                select.getItems().addAll(
                    TNo +"|"+TName
                );
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
    private Label ques;

    @FXML
    private Label anser;
    @FXML
    private Label asktime;


    @FXML
    private Label ansername;
    @FXML
    private ComboBox<String> questionid;
    @FXML
    void delete(ActionEvent event) {

    }


    @FXML
    void tofind(ActionEvent event) {
        String Re = "select *from view_student_show_ask where SANo";
        try {
            String id = questionid.getValue();
            ResultSet resultset = Student_log.sql.executeQuery(Re+"="+"\""+id+"\";");
            while(resultset.next()){
                String SAInfo = resultset.getString("SAInfo");
                String Time = resultset.getString("Time");
                String TNo = resultset.getString("TName");
                String TAnswer = resultset.getString("TAnswer");
                anser.setText(TAnswer);
                ques.setText(SAInfo);
                asktime.setText(Time);
                ansername.setText(TNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getnew(ActionEvent event) {
        String Re = "select SANo from sask where SNo";
        try {
            ResultSet resultset = Student_log.sql.executeQuery(Re+"="+"\""+Student_log.name+"\";");
            while(resultset.next()){
                String SAno = resultset.getString("SAno");
                questionid.getItems().addAll(
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
    @FXML
    private CheckBox annoy1;

    @FXML
    private TextArea question1;

    @FXML
    private ComboBox<String> select1;
    @FXML
    private Button submit1;
    @FXML
    void tosubmit1(ActionEvent event) {
        int x;
        String que = question1.getText();
        if(annoy1.isSelected()==true) x = 1;
        else x = 0;
        try {
            Date date = new Date();//获取当前时间
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sim.format(date.getTime());
            String[] Str3Array = select1.getValue().split("\\|");
            System.out.println(Str3Array[0]);
            boolean res = Student_log.sql.execute("insert into tevalute(Evalute,TETime,TAno,SNo,TNo) " +
                    "values ("+"\""+question1.getText()+"\","+"\""+time+"\","+"\""+x+"\""+","+"\""+Student_log.name+"\","+"\""+Str3Array[0]+"\""+")");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("评论成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("评论失败");
            alert.showAndWait();
        }

    }
    @FXML
    void up1(ActionEvent event) {
        try {
            ResultSet resultSet = Student_log.sql.executeQuery("select *from teacherinfo");
            while (resultSet.next()) {
                String TNo = resultSet.getString("TNo");
                String TName = resultSet.getString("TName");
                select1.getItems().addAll(
                        TNo + "|" + TName
                );
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
    private RadioButton b1;
    @FXML
    private RadioButton b2;

    @FXML
    private RadioButton b3;
    @FXML
    private RadioButton b4;

    @FXML
    private RadioButton b5;
    @FXML
    private ToggleGroup group4;

    @FXML
    private ToggleGroup group3;

    @FXML
    private ComboBox<String> index;
    @FXML
    private ToggleGroup group5;
    @FXML
    private RadioButton c1;

    @FXML
    private ToggleGroup group2;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton c2;

    @FXML
    private RadioButton c3;

    @FXML
    private RadioButton c4;

    @FXML
    private RadioButton c5;
    @FXML
    private RadioButton d1;

    @FXML
    private RadioButton d2;
    @FXML
    private RadioButton d3;

    @FXML
    private RadioButton d4;

    @FXML
    private RadioButton d5;
    @FXML
    private RadioButton a1;

    @FXML
    private RadioButton a2;

    @FXML
    private RadioButton a3;

    @FXML
    private RadioButton a4;

    @FXML
    private RadioButton a5;

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
    String QNum1;
    String QNum2;
    String QNum3;
    String QNum4;
    String QNum5;
    int qno;
    @FXML
    void tochoice(ActionEvent event) {
        String[] Str3Array = index.getValue().split("\\|");
        qno = Integer.parseInt(Str3Array[0]);
        try {//先直接从这个表中拿信息了，看看能不能正常显示。
            String AnswerD;
            String AnswerC;
            String AnswerB;
            String AnswerA;
            String Question;
            ResultSet resultSet = Student_log.sql.executeQuery("select * from qtype_study;");
            resultSet.next();
            QNum1 = resultSet.getString("QNum");
            Question = resultSet.getString("Question");
            AnswerA = resultSet.getString("AnswerA");
            AnswerB = resultSet.getString("AnswerB");
            AnswerC = resultSet.getString("AnswerC");
            AnswerD = resultSet.getString("AnswerD");
            w1.setText(Question);
            w1.setUserData(Question);
            a1.setText(AnswerA);
            a1.setUserData(AnswerA);
            b1.setText(AnswerB);
            b1.setUserData(AnswerB);
            c1.setText(AnswerC);
            c1.setUserData((AnswerC));
            d1.setText(AnswerD);
            d1.setUserData(AnswerD);
            resultSet.next();
            QNum2 = resultSet.getString("QNum");
            Question = resultSet.getString("Question");
            AnswerA = resultSet.getString("AnswerA");
            AnswerB = resultSet.getString("AnswerB");
            AnswerC = resultSet.getString("AnswerC");
            AnswerD = resultSet.getString("AnswerD");
            w2.setText(Question);
            a2.setText(AnswerA);
            b2.setText(AnswerB);
            c2.setText(AnswerC);
            d2.setText(AnswerD);
            w2.setUserData(Question);
            a2.setUserData(AnswerA);
            b2.setUserData(AnswerB);
            c2.setUserData((AnswerC));
            d2.setUserData(AnswerD);
            resultSet.next();
            QNum3 = resultSet.getString("QNum");
            Question = resultSet.getString("Question");
            AnswerA = resultSet.getString("AnswerA");
            AnswerB = resultSet.getString("AnswerB");
            AnswerC = resultSet.getString("AnswerC");
            AnswerD = resultSet.getString("AnswerD");
            w3.setText(Question);
            a3.setText(AnswerA);
            b3.setText(AnswerB);
            c3.setText(AnswerC);
            d3.setText(AnswerD);
            w3.setUserData(Question);
            a3.setUserData(AnswerA);
            b3.setUserData(AnswerB);
            c3.setUserData((AnswerC));
            d3.setUserData(AnswerD);
            resultSet.next();
            QNum4 = resultSet.getString("QNum");
            Question = resultSet.getString("Question");
            AnswerA = resultSet.getString("AnswerA");
            AnswerB = resultSet.getString("AnswerB");
            AnswerC = resultSet.getString("AnswerC");
            AnswerD = resultSet.getString("AnswerD");
            w4.setText(Question);
            a4.setText(AnswerA);
            b4.setText(AnswerB);
            c4.setText(AnswerC);
            d4.setText(AnswerD);
            w4.setUserData(Question);
            a4.setUserData(AnswerA);
            b4.setUserData(AnswerB);
            c4.setUserData((AnswerC));
            d4.setUserData(AnswerD);
            resultSet.next();
            QNum5 = resultSet.getString("QNum");
            Question = resultSet.getString("Question");
            AnswerA = resultSet.getString("AnswerA");
            AnswerB = resultSet.getString("AnswerB");
            AnswerC = resultSet.getString("AnswerC");
            AnswerD = resultSet.getString("AnswerD");
            w5.setText(Question);
            a5.setText(AnswerA);
            b5.setText(AnswerB);
            c5.setText(AnswerC);
            d5.setText(AnswerD);
            w5.setUserData(Question);
            a5.setUserData(AnswerA);
            b5.setUserData(AnswerB);
            c5.setUserData((AnswerC));
            d5.setUserData(AnswerD);
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
        try{
        Date date = new Date();//获取当前时间
        SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddHHmmss");
        String time=sim.format(date.getTime());
        Student_log.sql.execute("insert into qresult(QRNo, QNo, SNo) values ("+"\""+Student_log.name+time+"\","+"\""+qno+"\","+"\""+Student_log.name+"\""+")");
        Student_log.sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+Student_log.name+time+"\","+"\""+QNum1+"\","+"\""+group1.getSelectedToggle().getUserData()+"\""+")");
        Student_log.sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+Student_log.name+time+"\","+"\""+QNum2+"\","+"\""+group2.getSelectedToggle().getUserData()+"\""+")");
        Student_log.sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+Student_log.name+time+"\","+"\""+QNum3+"\","+"\""+group3.getSelectedToggle().getUserData()+"\""+")");
        Student_log.sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+Student_log.name+time+"\","+"\""+QNum4+"\","+"\""+group4.getSelectedToggle().getUserData()+"\""+")");
        Student_log.sql.execute("insert into questions(QRNo, QNum, QAnswer) values ("+"\""+Student_log.name+time+"\","+"\""+QNum5+"\","+"\""+group5.getSelectedToggle().getUserData()+"\""+")");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("提示");
        alert.headerTextProperty().set("提交成功");
        alert.showAndWait();}
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("提交失败");
            alert.showAndWait();
        }
    }

    @FXML
    void toupdate_w(ActionEvent event) {
        try {
            ResultSet resultSet = Student_log.sql.executeQuery("select *from qtype");
            while(resultSet.next()) {
                String QNo = resultSet.getString("QNo");
                String Type = resultSet.getString("Type");
                index.getItems().addAll(
                        QNo +"|"+Type
                );
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
}

