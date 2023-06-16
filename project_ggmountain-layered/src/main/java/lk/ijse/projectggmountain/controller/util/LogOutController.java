package lk.ijse.projectggmountain.controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutController {
    public static void logout(AnchorPane anc) throws IOException {
        anc.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(LogOutController.class.getResource("/view/login_form.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}

