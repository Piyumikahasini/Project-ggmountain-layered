package lk.ijse.projectggmountain.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.projectggmountain.bo.BOFactory;
import lk.ijse.projectggmountain.bo.custom.LoginBO;
import lk.ijse.projectggmountain.controller.util.AlertController;
import lk.ijse.projectggmountain.dto.UserDTO;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private ComboBox loginComboBox;

    @FXML
    private Rectangle loginRec;

    @FXML
    private PasswordField loginTxt1;

    @FXML
    private PasswordField loginTxti2;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    LoginBO loginBO=  BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN_BO);

    @FXML
    void clickOnActionForgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/forget_password_form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("assets/Logo (1).png"));
        stage.show();
    }

    @FXML
    void clickOnActionLogin(ActionEvent actionEvent) throws IOException, SQLException {
        String username = loginTxt1.getText();
        String password = loginTxti2.getText();
        String combo = String.valueOf(loginComboBox.getValue());

        try {
            UserDTO userDTO = loginBO.findByUserName(username);
            if (userDTO == null) {
                AlertController.errormessage("User Name Not Found");
            } else {
                if (userDTO.getPassword().equals(password) && userDTO.getJobTitle().equalsIgnoreCase(combo) && combo.equals("Admin")) {
                    LoginMessageController.loginsuccessfulmsg();

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        loginBtn.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoard_form.fxml"));
                        Parent root1 = null;
                        try {
                            root1 = fxmlLoader.load();
                        } catch (IOException e) {
                            System.out.println(e);
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        stage.setTitle("DashBoard");
                        stage.setScene(new Scene(root1));
                        stage.setResizable(false);
                        stage.getIcons().add(new Image("assets/Logo (1).png"));
                        stage.show();
                    }));
                    timeline.play();
                } else if (userDTO.getPassword().equals(password) && userDTO.getJobTitle().equalsIgnoreCase(combo) && combo.equals("Cashier")) {
                    LoginMessageController.loginsuccessfulmsg();

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        loginBtn.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cashier_dashbord_form.fxml"));
                        Parent root1 = null;
                        try {
                            root1 = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        stage.setTitle("DashBoard");
                        stage.setScene(new Scene(root1));
                        stage.setResizable(false);
                        stage.getIcons().add(new Image("assets/Logo (1).png"));
                        stage.show();
                    }));
                    timeline.play();
                } else {
                    LoginMessageController.loginunsuccessfulmsg();
                }
            }
            } catch(SQLException e){
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }

    @FXML
    void initialize() {
        assert forgotPassword != null : "fx:id=\"forgotPassword\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginComboBox != null : "fx:id=\"loginComboBox\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginRec != null : "fx:id=\"loginRec\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginTxt1 != null : "fx:id=\"loginTxt1\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginTxti2 != null : "fx:id=\"loginTxti2\" was not injected: check your FXML file 'login_form.fxml'.";

        loginComboBox.getItems().addAll("Admin" , "Cashier");
    }

    public void clickOnActionSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("//view/signUp_form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("assets/Logo (1).png"));
        stage.show();
    }
}
