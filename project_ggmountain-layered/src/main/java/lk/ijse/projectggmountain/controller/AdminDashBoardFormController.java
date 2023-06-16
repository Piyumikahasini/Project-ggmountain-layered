package lk.ijse.projectggmountain.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.controller.util.LogOutController;
import lk.ijse.projectggmountain.controller.util.TimeAndDateController;

public class AdminDashBoardFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane adminDashPane;

    @FXML
    void adminEmployeeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    @FXML
    void adminInventoryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    @FXML
    void adminOrderOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    @FXML
    void clickOnActionSupplierForm(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    @FXML
    void initialize() {
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'adminDashBoard_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'adminDashBoard_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'adminDashBoard_form.fxml'.";
        TimeAndDateController timeAndDate = new TimeAndDateController();
        timeAndDate.timenow(lblTime, lblDate);
    }

    public void logoutbtnOnMousePressed(MouseEvent mouseEvent) throws IOException {
        LogOutController.logout(adminDashPane);
    }

    public void adminHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    public void OnActionReportBtn(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/report_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    public void googleIconOnMouseClicked(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));

    }

    public void fbIconOnMouseClicked(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Facebook"));

    }
}
