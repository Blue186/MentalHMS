package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button teacher_button;

    @FXML
    private Button student_button;

    @FXML
    private Label label;

    @FXML
    private Pane pabel;

    @FXML
    void teacher_login(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("登陆成功");
//        alert.show();
        Platform.runLater(()->{
            //获取按钮所在的窗口
            //BtSign可以为当前窗口任意一个控件
//            Stage primaryStage = (Stage)student_button.getScene().getWindow();
            Stage primaryStage = new Stage();
            //当前窗口隐藏
//            primaryStage.hide();
            //加载注册窗口
            try {
                new Teacher_log().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void student_login(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("登陆成功");
//        alert.show();
        Platform.runLater(()->{
            Stage primaryStage = new Stage();
            //获取按钮所在的窗口
            //BtSign可以为当前窗口任意一个控件
            //当前窗口隐藏
            try {
                new Student_log().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    void manger_login(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("登陆成功");
//        alert.show();
        Platform.runLater(()->{
            Stage primaryStage = new Stage();
            //获取按钮所在的窗口
            //BtSign可以为当前窗口任意一个控件
            //当前窗口隐藏
            try {
                new Manger_log().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
