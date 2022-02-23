package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.Conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Teacher_log {
    static Statement sql;
    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void get_user(ActionEvent event) {

    }

    @FXML
    void get_password(ActionEvent event) {

    }

    static String name;
    static String pass;
    @FXML
    void login_button(ActionEvent event) {
        name = user.getText();
        pass = password.getText();
        Conn conn = new Conn();//创建本对象
        Connection connection = conn.getConnection();//调用连接数据库的方法
        String SNo, SNos;
        ResultSet resultSet;
        String SPwd, SPwds;
        boolean flag1 = true, flag2 = false, flag3 = false;
        try {
            sql = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = sql.executeQuery("select TNo,TPwd from view_teacherinfo_tno_tpwd");//这里使用视图进行查询view_studentinfo_sno_spw是学生学号和密码的一个视图
            while (resultSet.next()) {
                SNos = resultSet.getString("TNo");
                SPwds = resultSet.getString("TPwd");
                if (flag1) {
                    if (name.equals(SNos)) {//学号匹配
                        flag2 = true;
                        if (pass.equals(SPwds)) {//密码匹配
                            flag1 = false;//学号密码都正确
                            flag3 = true;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.titleProperty().set("提示");
                            alert.headerTextProperty().set("登录成功");
                            alert.showAndWait();
                        }
                    }
                }
            }
            if (!flag2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("提示");
                alert.headerTextProperty().set("工号错误");
                alert.showAndWait();
            }
            if (!flag3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("提示");
                alert.headerTextProperty().set("密码错误");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!flag1) {
            Stage primaryStage = new Stage();
            //获取按钮所在的窗口
            //BtSign可以为当前窗口任意一个控件
            //当前窗口隐藏
            try {
                new Teacher().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Teacher_log.fxml"));
        primaryStage.setTitle("重庆邮电大学心理健康咨询系统");
        primaryStage.setScene(new Scene(root, 600, 400
        ));
        primaryStage.show();
    }
}

