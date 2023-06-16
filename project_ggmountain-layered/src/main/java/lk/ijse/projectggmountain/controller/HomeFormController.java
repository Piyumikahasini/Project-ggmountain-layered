package lk.ijse.projectggmountain.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.projectggmountain.bo.BOFactory;
import lk.ijse.projectggmountain.bo.custom.HomeBO;
import lk.ijse.projectggmountain.controller.util.ValidateField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {
    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private BarChart<String, Integer> lowstockitems;

    @FXML
    private PieChart piechartincomelast5months;

    @FXML
    private AreaChart<String, Double> areachart;

    @FXML
    private TextField txtyear;

    @FXML
    private Label lblwrongyearformat;

    HomeBO homeBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.HOME_BO);

    public void lowstockitemsOnMouseEntered(MouseEvent mouseEvent) {
    }

    public void lowstockitemsOnMouseExited(MouseEvent mouseEvent) {
    }

    public void setDataToPieChart() throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        ArrayList<PieChart.Data> all = homeBO.getDataToPieChart();

        pieChartData.addAll(all);
        piechartincomelast5months.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataToPieChart();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            setDataToBarChart();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            setDataToAreaChart();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setDataToBarChart() throws SQLException {
        ObservableList<XYChart.Series<String, Integer>> barChartData = FXCollections.observableArrayList();

        ArrayList<XYChart.Series<String, Integer>> all = homeBO.getDataToBarChart();

        barChartData.addAll(all);
        lowstockitems.setData(barChartData);
    }

    List<XYChart.Data<String, Double>> data;
    public void setDataToAreaChart() throws SQLException {
        String year=txtyear.getText();
        data = homeBO.getDataToAreaChart(year);

        XYChart.Series<String, Double> series = new XYChart.Series<>(year, FXCollections.observableArrayList(data));

        areachart.getData().add(series);
    }

    public void txtyearOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            lblwrongyearformat.setVisible(false);
            if (!txtyear.getText().isEmpty()) {
                if (ValidateField.yearCheck(txtyear.getText())) {
                    setDataToAreaChart();
                    areachart.setData(FXCollections.observableArrayList());
                    setDataToAreaChart();
                } else {
                    txtyear.setStyle("-fx-text-fill: red");
                    lblwrongyearformat.setText("Wrong Year Format");
                    lblwrongyearformat.setVisible(true);
                }
            } else {
                lblwrongyearformat.setText("Please enter an year first");
                lblwrongyearformat.setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void areachartOnMouseClicked(MouseEvent mouseEvent) {
        txtyear.setText("");
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }

    public void txtyearOnMouseClicked(MouseEvent mouseEvent) {
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }
}
