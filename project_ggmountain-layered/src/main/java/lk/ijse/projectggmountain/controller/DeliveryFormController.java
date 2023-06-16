package lk.ijse.projectggmountain.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.bo.BOFactory;
import lk.ijse.projectggmountain.bo.custom.DeliveryBO;
import lk.ijse.projectggmountain.dto.DeliveryDTO;
import lk.ijse.projectggmountain.view.tdm.DeliveryTM;
import lk.ijse.projectggmountain.controller.util.AlertController;
import lk.ijse.projectggmountain.controller.util.ValidateField;

public class DeliveryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDeliveryId;

    @FXML
    private TableColumn<?, ?> colDeliveryStatus;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private ComboBox<String> comDelStatus;

    @FXML
    private ComboBox<String> comEmpId;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearchDeliveryId;

    @FXML
    private TextField txtSearchDueDate;

    @FXML
    private TextField txtSearchOrderId;

    @FXML
    private TextField txtSearchStatus;

    @FXML
    private Label lblDeliveryId;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblinvalidwrongduedate;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    DeliveryBO deliveryBO =  BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DELIVERY_BO);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String delid = lblDeliveryId.getText();
        String ordid = lblOrderId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to cancel this delivery?");
        if(result==true) {
            try {
                boolean isDeleted = deliveryBO.deleteDelivery(delid);
                if (isDeleted) {
                    boolean isUpdated = deliveryBO.cancelDelivery(ordid);
                    AlertController.confirmmessage("Delivery Cancelled Successfully");
                    lblOrderId.setText("");
                    lblDeliveryId.setText("");
                    comDelStatus.setValue(null);
                    comEmpId.setValue(null);
                    txtLocation.setText("");
                    txtDate.setValue(null);
                    getAll();
                }
            } catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String delid = lblDeliveryId.getText();
        String delsts = comDelStatus.getValue();
        String loc = txtLocation.getText();
        String deldate = String.valueOf(txtDate.getValue());
        String orderid = lblOrderId.getText();
        String empid = comEmpId.getValue();

        if(ValidateField.dateCheck(deldate) || deldate.equals("Pending")) {
            DeliveryDTO delivery = new DeliveryDTO(delid,deldate,delsts, loc, orderid, empid);
            try {
                boolean isUpdated = deliveryBO.updateDelivery(delivery);
                if (isUpdated) {
                    AlertController.confirmmessage("Delivery Details Updated");
                    lblOrderId.setText("");
                    lblDeliveryId.setText("");
                    comDelStatus.setValue(null);
                    comEmpId.setValue(null);
                    txtLocation.setText("");
                    txtDate.setValue(null);
                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
//        else{
//            lblinvalidwrongduedate.setVisible(true);
//        }
    }

    @FXML
    void txtSearchDeliveryIdOnAction(ActionEvent event) {
        String delid = txtSearchOrderId.getText();
        try {
            DeliveryDTO deliveryDTO = deliveryBO.findBydeliveryId(delid);
            if(deliveryDTO!=null) {
                comDelStatus.setDisable(false);
                txtLocation.setDisable(false);
                txtDate.setDisable(false);
                comEmpId.setDisable(false);

                lblDeliveryId.setText(deliveryDTO.getDelid());
                comDelStatus.setValue(deliveryDTO.getDelsts());
                txtLocation.setText(deliveryDTO.getLoc());
                txtDate.setValue(LocalDate.parse(deliveryDTO.getDeldate()));
                lblOrderId.setText(deliveryDTO.getOrdid());
                comEmpId.setValue(deliveryDTO.getEmpid());

                txtSearchOrderId.setText("");
            }else{
                AlertController.errormessage("Delivery ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    @FXML
    void txtSearchDueDateOnAction(ActionEvent event) throws SQLException {
        String duedate = txtSearchDueDate.getText();
        // ObservableList<DeliveryTM> obList = DeliveryModel.getByDueDate(duedate);

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();
        ArrayList<DeliveryDTO> all = deliveryBO.getByDueDate(duedate);

        for (DeliveryDTO dto : all) {
            obList.add(new DeliveryTM(dto.getOrdid(),dto.getDelid(),dto.getDeldate(),dto.getDelsts(),dto.getLoc(),dto.getEmpid()));
        }
        tblDelivery.setItems(obList);
    }

    @FXML
    void txtSearchOrderIdOnAction(ActionEvent event) {
        String id = txtSearchOrderId.getText();
        try {
            DeliveryDTO delivery = deliveryBO.findByOrderID(id);
            if(delivery!=null) {
                comDelStatus.setDisable(false);
                txtLocation.setDisable(false);
                txtDate.setDisable(false);
                comEmpId.setDisable(false);

                lblDeliveryId.setText(delivery.getDelid());
                comDelStatus.setValue(delivery.getDelsts());
                txtLocation.setText(delivery.getLoc());
                txtDate.setValue(LocalDate.parse(delivery.getDeldate()));
                lblOrderId.setText(delivery.getOrdid());
                comEmpId.setValue(delivery.getEmpid());

                txtSearchOrderId.setText("");
            }else{
                AlertController.errormessage("Order ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    @FXML
    void txtSearchStatusOnAction(ActionEvent event) throws SQLException {
        String delists = String.valueOf(comDelStatus.getValue());
        // ObservableList<DeliveryTM> obList = DeliveryModel.getByDeliveryStatus(delists);
        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();

        ArrayList<DeliveryDTO> all = deliveryBO.getByDeliveryStatus(delists);
        for (DeliveryDTO dto : all) {
            obList.add(new DeliveryTM(dto.getOrdid(),dto.getDelid(),dto.getDeldate(),dto.getDelsts(),dto.getLoc(),dto.getEmpid()));
        }
        tblDelivery.setItems(obList);
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = deliveryBO.loadEmployeeIds();

            for (String id : ids) {
                obList.add(id);
            }
            comEmpId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void tblDeliveryOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblDelivery.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<DeliveryTM, ?>> columns = tblDelivery.getColumns();

        lblOrderId.setText(columns.get(0).getCellData(row).toString());
        lblDeliveryId.setText(columns.get(1).getCellData(row).toString());
        txtDate.setValue(LocalDate.parse(columns.get(2).getCellData(row).toString()));
        comDelStatus.setValue(columns.get(3).getCellData(row).toString());
        txtLocation.setText(columns.get(4).getCellData(row).toString());
        comEmpId.setValue(columns.get(5).getCellData(row).toString());


        comDelStatus.setDisable(false);
        txtLocation.setDisable(false);
        txtDate.setDisable(false);
        comEmpId.setDisable(false);
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("delid"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("delstatus"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("deldate"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empid"));
    }

    private void getAll() throws ClassNotFoundException {
        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();
        try {
            ArrayList<DeliveryDTO> all = deliveryBO.getAllDelivery();

            for (DeliveryDTO d : all) {
                obList.add(new DeliveryTM(d.getDelid(),d.getDeldate(),d.getDelsts(),d.getLoc(),d.getOrdid(),d.getEmpid()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() throws ClassNotFoundException {
        loadEmployeeIds();
        getAll();
        setCellValueFactory();
        comDelStatus.setDisable(true);
        txtLocation.setDisable(true);
        txtDate.setDisable(true);
        comEmpId.setDisable(true);
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert adminChangingPane != null : "fx:id=\"cashierChangingPane\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDate != null : "fx:id=\"colDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDeliveryId != null : "fx:id=\"colDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDeliveryStatus != null : "fx:id=\"colDeliveryStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colEmpId != null : "fx:id=\"colEmpId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colLocation != null : "fx:id=\"colLocation\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colOrderId != null : "fx:id=\"colOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert comDelStatus != null : "fx:id=\"comDelStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert comEmpId != null : "fx:id=\"comEmpId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert lblDeliveryId != null : "fx:id=\"lblDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert lblOrderId != null : "fx:id=\"lblOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert tblDelivery != null : "fx:id=\"tblDelivery\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtLocation != null : "fx:id=\"txtLocation\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDeliveryId != null : "fx:id=\"txtSearchDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDueDate != null : "fx:id=\"txtSearchDueDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchOrderId != null : "fx:id=\"txtSearchOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchStatus != null : "fx:id=\"txtSearchStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";

    }

}
