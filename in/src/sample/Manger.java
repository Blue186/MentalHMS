package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Manger {

    @FXML
    private TextField ssept_app;

    @FXML
    private TextField ttimefix;

    @FXML
    private TextField tmailfix;

    @FXML
    private TextField tbirthapp;

    @FXML
    private Button delete1;

    @FXML
    private Button delete2;

    @FXML
    private TextField sgrade_app;

    @FXML
    private TextField steacher_app;

    @FXML
    private TextField student_password;

    @FXML
    private TextField sphone_app;

    @FXML
    private TextField student_name;

    @FXML
    private Button select2;

    @FXML
    private Button select1;

    @FXML
    private Button fix2;

    @FXML
    private TextField tphoneapp;

    @FXML
    private Button fix1;

    @FXML
    private TextField tbirthfix;

    @FXML
    private TextField sname_app;

    @FXML
    private TextField tmailapp;

    @FXML
    private TextField tno_fix;

    @FXML
    private Button tapp;

    @FXML
    private TextField spassword_app;

    @FXML
    private TextField tnamefix;

    @FXML
    private TextField tnodelete;

    @FXML
    private TextField sno_fix;

    @FXML
    private TextField tpassfix;

    @FXML
    private TextField sbirth_app;

    @FXML
    private TextField tseptfix;

    @FXML
    private TextField tnameapp;

    @FXML
    private TextField student_birth;

    @FXML
    private TextField tphonefix;

    @FXML
    private TextField tpassapp;

    @FXML
    private TextField sno_delet;

    @FXML
    private Label tnofix;

    @FXML
    private Label sno;

    @FXML
    private TextField student_phone;

    @FXML
    private TextField ttimeapp;

    @FXML
    private TextField tnoapp;

    @FXML
    private TextField tseptapp;

    @FXML
    private TextField student_sept;

    @FXML
    private TextField student_grade;
    @FXML
    private TextField student_teacher;

    @FXML
    private Button sapp;

    @FXML
    private TextField sno_app;

    @FXML
    void selectstudentinfo(ActionEvent event) {
        try {
            ResultSet resultSet = Manger_log.sql.executeQuery("select *from studentinfo where SNo=" + "\"" + sno_fix.getText() + "\"");
            while(resultSet.next()) {
                String SNo = resultSet.getString("SNo");
                String SName = resultSet.getString("SName");
                String Birth = resultSet.getString("Birth");
                String SPNo = resultSet.getString("SPNo");
                String Major = resultSet.getNString("Major");
                String Grade = resultSet.getString("Grade");
                String spw = resultSet.getString("SPwd");
                String teacher = resultSet.getString("SInstructor");
                sno.setText(SNo);
                student_phone.setText(SPNo);
                student_name.setText(SName);
                student_birth.setText(Birth);
                student_sept.setText(Major);
                student_grade.setText(Grade);
                student_password.setText(spw);
                student_teacher.setText(teacher);
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
    void fixstudentinfo(ActionEvent event) {
        try {
                String SNo = sno.getText();
                String SName = student_name.getText();
                String Birth = student_birth.getText();
                String SPNo = student_phone.getText();
                String Major = student_sept.getText();
                String Grade = student_grade.getText();
                String spw = student_password.getText();
                String teacher = student_teacher.getText();
            Manger_log.sql.execute("update studentinfo set SName = \""+SName+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set Birth = \""+Birth+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set SPNo = \""+SPNo+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set Major = \""+Major+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set Grade = \""+Grade+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set SPwd = \""+spw+"\" where Sno = \""+SNo+"\"");
            Manger_log.sql.execute("update studentinfo set SInstructor = \""+teacher+"\" where Sno = \""+SNo+"\"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改成功");
            alert.showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改失败");
            alert.showAndWait();
        }
    }

    @FXML
    void delete_student(ActionEvent event) {
        try {
            Manger_log.sql.execute("delete from studentinfo where SNo="+"\""+sno_delet.getText()+"\";");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("删除成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("删除失败");
            alert.showAndWait();
        }
    }

    @FXML
    void sappend(ActionEvent event) {
        try {
            String SNo = sno_app.getText();
            String SName = sname_app.getText();
            String Birth = sbirth_app.getText();
            String SPNo = sphone_app.getText();
            String Major = ssept_app.getText();
            String Grade = sgrade_app.getText();
            String spw = spassword_app.getText();
            String teacher = steacher_app.getText();
            Manger_log.sql.execute("insert into studentinfo values ("+"\""+SNo+"\","+"\""+SName+"\","+"\""+Birth+"\","+"\""+SPNo+"\","+"\""+Major+"\","+"\""+Grade+"\","+"\""+teacher+"\","+"\""+spw+"\""+");");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("添加成功");
            alert.showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("添加失败");
            alert.showAndWait();
        }
    }

    @FXML
    void selecttno(ActionEvent event) {
        try {
            ResultSet resultSet = Manger_log.sql.executeQuery("select *from teacherinfo where TNo=" + "\"" + tno_fix.getText() + "\"");
            while(resultSet.next()) {
                String TNo = resultSet.getString("TNo");
                String TName = resultSet.getString("TName");
                String TBirth = resultSet.getString("TBirth");
                String TPNo = resultSet.getString("TPhone");
                String Major = resultSet.getNString("TCollege");
                String TWork = resultSet.getString("TWork");
                String spw = resultSet.getString("TPwd");
                String TEmail = resultSet.getString("TEmail");
                tnofix.setText(TNo);
                tphonefix.setText(TPNo);
                tnamefix.setText(TName);
                tbirthfix.setText(TBirth);
                tseptfix.setText(Major);
                ttimefix.setText(TWork);
                tpassfix.setText(spw);
                tmailfix.setText(TEmail);
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
    void fixtno(ActionEvent event) {
        try {
            String TNo = tnofix.getText();
            String TName = tnamefix.getText();
            String TBirth = tbirthfix.getText();
            String TPNo = tphonefix.getText();
            String Major = tseptfix.getText();
            String TWork = ttimefix.getText();
            String spw = tpassfix.getText();
            String TEmail = tmailfix.getText();
            Manger_log.sql.execute("update teacherinfo set TName = \""+TName+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TBirth = \""+TBirth+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TPhone = \""+TPNo+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TCollege = \""+Major+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TWork = \""+TWork+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TPwd = \""+spw+"\" where TNo = \""+TNo+"\"");
            Manger_log.sql.execute("update teacherinfo set TEmail = \""+TEmail+"\" where TNo = \""+TNo+"\"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改成功");
            alert.showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("修改失败");
            alert.showAndWait();
        }
    }

    @FXML
    void tappend(ActionEvent event) {
        try {
            String TNo = tnoapp.getText();
            String TName = tnameapp.getText();
            String TBirth = tbirthapp.getText();
            String TPNo = tphoneapp.getText();
            String Major = tseptapp.getText();
            String TWork = ttimeapp.getText();
            String spw = tpassapp.getText();
            String TEmail = tmailapp.getText();
            Manger_log.sql.execute("insert into teacherinfo values ("+"\""+TNo+"\","+"\""+TName+"\","+"\""+TBirth+"\","+"\""+TPNo+"\","+"\""+TEmail+"\","+"\""+Major+"\","+"\""+TWork+"\","+"\""+spw+"\""+")");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("添加成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("添加失败");
            alert.showAndWait();
        }
    }

    @FXML
    void deletetno(ActionEvent event) {
        try {
            Manger_log.sql.execute("delete from teacherinfo where TNo="+"\""+tnodelete.getText()+"\";");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("删除成功");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("删除失败");
            alert.showAndWait();
        }
    }
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("manger.fxml"));
        primaryStage.setTitle("重庆邮电大学心理健康咨询系统");
        primaryStage.setScene(new Scene(root, 1267, 796
        ));
        primaryStage.show();
    }

}

