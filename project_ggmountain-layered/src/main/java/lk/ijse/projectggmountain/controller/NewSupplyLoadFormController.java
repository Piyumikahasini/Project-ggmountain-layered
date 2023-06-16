package lk.ijse.projectggmountain.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.bo.BOFactory;
import lk.ijse.projectggmountain.bo.custom.SupplierOrderDetailBO;
import lk.ijse.projectggmountain.dto.InventoryDTO;
import lk.ijse.projectggmountain.dto.NewLoadSupplierDTO;
import lk.ijse.projectggmountain.dto.SupplierDTO;
import lk.ijse.projectggmountain.view.tdm.AddSupplyLoadTM;
import lk.ijse.projectggmountain.controller.util.TimeAndDateController;

public class NewSupplyLoadFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> ItemIdCol;

    @FXML
    private TableColumn<?, ?> ItemNameCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private Button addSupplyLoadBtn;

    @FXML
    private Button addToLoadBtn;

    @FXML
    private AnchorPane cashierChangingPane;

    @FXML
    private TableColumn<?, ?> categoryCol;

    @FXML
    private ComboBox<String> comItemId;

    @FXML
    private ComboBox<String> comSupId;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblSupName;

    @FXML
    private Label lblTime;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private TableView<AddSupplyLoadTM> tblNewSupplyLoad;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSupLoad;

    SupplierOrderDetailBO supplierOrderDetailBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER_ORDER_DETAIL_BO);


    @FXML
    void btnAddSupplyLoadOnAction(ActionEvent event) {
        String loadId = lblSupId.getText();
        String supId = comSupId.getValue();
        String totPrice = txtSupLoad.getText();

        List<NewLoadSupplierDTO> newLoadList = new ArrayList<>();

        for (int i = 0; i < tblNewSupplyLoad.getItems().size(); i++) {
            AddSupplyLoadTM addSupplyLoadTM = obList.get(i);

            NewLoadSupplierDTO dto = new NewLoadSupplierDTO(
                    addSupplyLoadTM.getItemId(),
                    addSupplyLoadTM.getQuantity()
            );
            newLoadList.add(dto);
        }

        boolean isPlaced = false;
        try {
            isPlaced = supplierOrderDetailBO.placeLoad(loadId, supId, totPrice,newLoadList);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
    }

    private ObservableList<AddSupplyLoadTM> obList = FXCollections.observableArrayList();

    @FXML
    void btnAddToLoadOnAction(ActionEvent event) {
        String code = comItemId.getValue();
        String name = lblItemName.getText();
        String category = lblCategory.getText();
        int qty = Integer.parseInt(txtQuantity.getText());
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblNewSupplyLoad.getItems().size(); i++) {
                if (ItemIdCol.getCellData(i).equals(code)) {
                    qty += (int) quantityCol.getCellData(i);

                    obList.get(i).setQuantity(qty);

                    tblNewSupplyLoad.refresh();
                    return;
                }
            }
        }
        AddSupplyLoadTM tm = new AddSupplyLoadTM(code, name, category, qty, btnRemove);

        obList.add(tm);
        tblNewSupplyLoad.setItems(obList);

        txtQuantity.setText("");
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblNewSupplyLoad.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblNewSupplyLoad.refresh();
            }

        });
    }

    private void loadItemIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = supplierOrderDetailBO.loadItemIds();

            for (String code : codes) {
                obList.add(code);
            }
            comItemId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadSupplierIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = supplierOrderDetailBO.loadSupplierIds();

            for (String code : codes) {
                obList.add(code);
            }
            comSupId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void comItemIdOnAction(ActionEvent event) {
        String itemcode= comItemId.getValue();

        try {
            InventoryDTO item = supplierOrderDetailBO.findBy(itemcode);
            lblItemName.setText(item.getName());
            lblCategory.setText(item.getCategory());
            lblQty.setText(String.valueOf(item.getQtyOnHand()));
        } catch (Exception e) {

        }
    }

    @FXML
    void comSupIdOnAction(ActionEvent event) {
        String supId= comSupId.getValue();

        try {
            SupplierDTO supplier = supplierOrderDetailBO.findBySupId(supId);
            lblSupName.setText(supplier.getName());
        } catch (Exception e) {

        }
    }

    private void generateNextSupId() {
        try {
            String id = supplierOrderDetailBO.getNextSupplyLoadId();
            String nextId = splitOrderId(id);
            lblSupId.setText(nextId);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    public String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("SLD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "SLD-" + digit;
        }
        return "SLD-001";
    }

    void setCellValueFactory() {
        ItemIdCol.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        ItemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        loadItemIds();
        loadSupplierIds();
        generateNextSupId();
        TimeAndDateController.timenow(lblTime,lblDate);
        assert ItemIdCol != null : "fx:id=\"ItemIdCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert ItemNameCol != null : "fx:id=\"ItemNameCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert addSupplyLoadBtn != null : "fx:id=\"addSupplyLoadBtn\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert addToLoadBtn != null : "fx:id=\"addToLoadBtn\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert cashierChangingPane != null : "fx:id=\"cashierChangingPane\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert categoryCol != null : "fx:id=\"categoryCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert comItemId != null : "fx:id=\"comItemId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert comSupId != null : "fx:id=\"comSupId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblCategory != null : "fx:id=\"lblCategory\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblItemName != null : "fx:id=\"lblItemName\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblQty != null : "fx:id=\"lblQty\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblSupId != null : "fx:id=\"lblSupId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblSupName != null : "fx:id=\"lblSupName\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert quantityCol != null : "fx:id=\"quantityCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert tblNewSupplyLoad != null : "fx:id=\"tblNewSupplyLoad\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert txtQuantity != null : "fx:id=\"txtQuantity\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert txtSupLoad != null : "fx:id=\"txtSupLoad\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";

    }
}
