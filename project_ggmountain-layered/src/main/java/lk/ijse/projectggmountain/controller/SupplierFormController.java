package lk.ijse.projectggmountain.controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.bo.BOFactory;
import lk.ijse.projectggmountain.bo.custom.SupplierBO;
import lk.ijse.projectggmountain.dto.SupplierDTO;
import lk.ijse.projectggmountain.view.tdm.SupplierTM;
import lk.ijse.projectggmountain.controller.util.AlertController;
import lk.ijse.projectggmountain.controller.util.ValidateField;

public class SupplierFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField supAddress;

    @FXML
    private TableColumn<?, ?> supColAddress;

    @FXML
    private TableColumn<?, ?> supColContact;

    @FXML
    private TableColumn<?, ?> supColEmail;

    @FXML
    private TableColumn<?, ?> supColId;

    @FXML
    private TableColumn<?, ?> supColName;

    @FXML
    private TextField supContact;

    @FXML
    private TextField supEmail;

    @FXML
    private TextField supId;

    @FXML
    private TextField supName;

    @FXML
    private TextField supSearch;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private Button updateBtn;

    @FXML
    private Label lblinvalidcontact;

    @FXML
    private Label lblinvalidemail;

    @FXML
    private Label lblinvalidsupplierid;

    SupplierBO supplierBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER_BO);

    @FXML
    void clickOnActionDelete(ActionEvent event) throws ClassNotFoundException {
        String id = supId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Supplier?");
        if(result==true) {
            try {
                boolean isDeleted = supplierBO.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Supplier Deleted Successfully");
                    supId.setText("");
                    supName.setText("");
                    supAddress.setText("");
                    supEmail.setText("");
                    supContact.setText("");

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void clickOnActionSave(ActionEvent event) throws ClassNotFoundException {
        String id = supId.getText();
        String name = supName.getText();
        String address = supAddress.getText();
        String email = supEmail.getText();
        String contact = supContact.getText();

        if(id.isEmpty() || name.isEmpty() || contact.isEmpty() || address.isEmpty() || email.isEmpty()){
            AlertController.errormessage("Supplier not saved successfully.\nPlease make sure to fill out all the required fields.");
        }else{
            if(ValidateField.supplierIdCheck(id)) {
                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                    if (ValidateField.emailCheck(email)) {
                        if (ValidateField.contactCheck(contact)) {
                            if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                                if (ValidateField.contactCheck(contact)) {
                                    if (ValidateField.emailCheck(email)) {


                                        SupplierDTO supplier = new SupplierDTO(id,name,address,email,contact);

                                        try {
                                            boolean isSaved = supplierBO.saveSupplier(supplier);
                                            if (isSaved) {
                                                AlertController.confirmmessage("New Supplier added successfully");
                                                supId.setText(null);
                                                supName.setText(null);
                                                supAddress.setText(null);
                                                supEmail.setText(null);
                                                supContact.setText(null);
                                                getAll();
                                            }
                                        }catch(SQLIntegrityConstraintViolationException e){
                                            AlertController.errormessage("This Supplier ID already exists.");
                                        } catch (SQLException e) {
                                            System.out.println(e);
                                            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                        }

                                    } else {
                                        lblinvalidemail.setVisible(true);
                                    }
                                } else {
                                    lblinvalidcontact.setVisible(true);
                                }
                            } else {
                                lblinvalidemail.setVisible(true);
                                lblinvalidcontact.setVisible(true);
                            }
                        } else {
                            lblinvalidcontact.setVisible(true);
                        }
                    } else {
                        lblinvalidemail.setVisible(true);
                    }
                } else {
                    lblinvalidemail.setVisible(true);
                    lblinvalidcontact.setVisible(true);
                }
            }else{
                lblinvalidsupplierid.setVisible(true);
                lblinvalidsupplierid.setStyle("-fx-text-fill: red");
            }
        }
    }

    @FXML
    void clickOnActionUpdate(ActionEvent event) throws ClassNotFoundException {
        String id = supId.getText();
        String name = supName.getText();
        String address = supAddress.getText();
        String email = supEmail.getText();
        String contact = supContact.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this Suppliers' details?");
        if(result==true) {

            if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || address.isEmpty() || email.isEmpty()) {
                AlertController.errormessage("Supplier not saved successfully.\nPlease make sure to fill out all the required fields.");
            } else {
                if (ValidateField.supplierIdCheck(id)) {
                    if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                        if (ValidateField.emailCheck(email)) {
                            if (ValidateField.contactCheck(contact)) {
                                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                                    if (ValidateField.contactCheck(contact)) {
                                        if (ValidateField.emailCheck(email)) {

                                            SupplierDTO supplier = new SupplierDTO(id, name, address, email, contact);

                                            try {
                                                boolean isUpdated = supplierBO.updateSupplier(supplier);
                                                if (isUpdated) {
                                                    AlertController.confirmmessage("New Supplier added successfully");
                                                    supId.setText(null);
                                                    supName.setText(null);
                                                    supAddress.setText(null);
                                                    supEmail.setText(null);
                                                    supContact.setText(null);
                                                    getAll();
                                                }
                                            } catch (SQLException e) {
                                                System.out.println(e);
                                                new Alert(Alert.AlertType.ERROR, "something went wrong!");
                                            }

                                        } else {
                                            lblinvalidemail.setVisible(true);
                                        }
                                    } else {
                                        lblinvalidcontact.setVisible(true);
                                    }
                                } else {
                                    lblinvalidemail.setVisible(true);
                                    lblinvalidcontact.setVisible(true);
                                }
                            } else {
                                lblinvalidcontact.setVisible(true);
                            }
                        } else {
                            lblinvalidemail.setVisible(true);
                        }
                    } else {
                        lblinvalidemail.setVisible(true);
                        lblinvalidcontact.setVisible(true);
                    }
                } else {
                    lblinvalidsupplierid.setVisible(true);
                    lblinvalidsupplierid.setStyle("-fx-text-fill: red");
                }
            }
        }
    }

    private void getAll() throws ClassNotFoundException {
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
        try {
            ArrayList<SupplierDTO> all = supplierBO.getAll();

            for (SupplierDTO i : all) {
                obList.add(new SupplierTM(i.getId(),i.getName(),i.getAddress(),i.getEmail(),i.getContact()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSupplier.setItems(obList);
    }

    @FXML
    void searchIconOnMousePressed(MouseEvent event) {
        supSearch.requestFocus();
        supSearch.selectAll();
        supSearch.fireEvent(new ActionEvent());
    }

    @FXML
    void supSearchOnAction(ActionEvent event) throws SQLException {
        String id = supSearch.getText();

        supId.setText("");
        supName.setText("");
        supAddress.setText("");
        supEmail.setText("");
        supContact.setText("");


        SupplierDTO supplier = supplierBO.findBy(id);
        if(supplier!=null) {
            supId.setText(supplier.getId());
            supName.setText(supplier.getName());
            supAddress.setText(supplier.getAddress());
            supEmail.setText(supplier.getEmail());
            supContact.setText(supplier.getContact());

            supSearch.setText("");

        }else{
            AlertController.errormessage("Supplier ID Not Found");
        }
    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        String searchValue = supSearch.getText().trim();
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
        ArrayList<SupplierDTO> all = supplierBO.getAll();

        for (SupplierDTO i : all) {
            obList.add(new SupplierTM(i.getId(),i.getName(),i.getAddress(),i.getEmail(),i.getContact()));
        }
        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>(){
                @Override
                public boolean test(SupplierTM suppliertm) {
                    return String.valueOf(suppliertm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSupplier.setItems(filteredData);
        } else {
            tblSupplier.setItems(obList);
        }
    }

    private void setCellValueFactory() {
        supColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        supColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        supColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        supColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        supColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    @FXML
    void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        getAll();
        lblinvalidemail.setVisible(false);
        lblinvalidcontact.setVisible(false);
        lblinvalidsupplierid.setVisible(false);
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supAddress != null : "fx:id=\"supAddress\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColAddress != null : "fx:id=\"supColAddress\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColContact != null : "fx:id=\"supColContact\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColEmail != null : "fx:id=\"supColEmail\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColId != null : "fx:id=\"supColId\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColName != null : "fx:id=\"supColName\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supContact != null : "fx:id=\"supContact\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supEmail != null : "fx:id=\"supEmail\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supId != null : "fx:id=\"supId\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supName != null : "fx:id=\"supName\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supSearch != null : "fx:id=\"supSearch\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert tblSupplier != null : "fx:id=\"tblSupplier\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";

    }
    public void supIdOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidsupplierid.setVisible(false);
    }

    public void supContactOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidcontact.setVisible(false);
    }

    public void supEmailOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidemail.setVisible(false);
    }

}
