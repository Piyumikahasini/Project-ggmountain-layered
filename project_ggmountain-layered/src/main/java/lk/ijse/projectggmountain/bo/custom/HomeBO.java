package lk.ijse.projectggmountain.bo.custom;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.projectggmountain.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HomeBO extends SuperBO {
    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException;

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException;

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException;

}
