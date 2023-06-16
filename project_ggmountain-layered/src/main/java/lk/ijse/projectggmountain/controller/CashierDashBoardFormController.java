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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.controller.util.LogOutController;
import lk.ijse.projectggmountain.controller.util.TimeAndDateController;

public class CashierDashBoardFormController {

    @FXML
    private AnchorPane cashierChangingPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private AnchorPane cashDashPane;

    @FXML
    void cashierCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    @FXML
    void cashierOrderOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    @FXML
    void clickOnActionInventory(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    @FXML
    void initialize() {
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'cashier_dashbord_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'cashier_dashbord_form.fxml'.";
        TimeAndDateController timeAndDate = new TimeAndDateController();
        timeAndDate.timenow(lblTime,lblDate);
    }

    public void logoutbtnOnMousePressed(MouseEvent mouseEvent) throws IOException {
        LogOutController.logout(cashDashPane);
    }

    public void cashierEventOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/event_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    public void clickOnActionDelivary(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/delivery_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    public void cashierSupplyLoadOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/new_supply_load_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    public void cashierHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    public void googleIconOnMouseClicked(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));

    }

    public void fbIconOnMouseClicked(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Facebook"));

    }
}
