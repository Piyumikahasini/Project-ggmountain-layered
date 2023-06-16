package lk.ijse.projectggmountain.bo.custom.impl;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.projectggmountain.bo.custom.HomeBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.ItemDAO;
import lk.ijse.projectggmountain.dao.custom.OrderDAO;
import lk.ijse.projectggmountain.dao.custom.QueryDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBoImpl implements HomeBO {
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException {
        return queryDAO.getDataToPieChart();
    }

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
        return itemDAO.getDataToBarChart();
    }

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
        return ordersDAO.getDataToAreaChart(year);
    }
}
