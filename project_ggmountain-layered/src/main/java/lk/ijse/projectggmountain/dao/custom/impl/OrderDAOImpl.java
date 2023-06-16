package lk.ijse.projectggmountain.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.projectggmountain.dao.custom.OrderDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public boolean cancelDelivery(String ordid) throws SQLException {
        String sql = "UPDATE orders SET delivery=? WHERE orderId=?";

        return CrudUtil.execute(
                sql,
                false,
                ordid
        );
    }

    public String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    public int totalOrdersToday() throws SQLException {
        String sql = "SELECT COUNT(orderId) FROM orders WHERE date=?";

        String currDate = String.valueOf(LocalDate.now());
        ResultSet resultSet=CrudUtil.execute(sql,currDate);

        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    public int totalOrdersMonth() throws SQLException {
        String sql = "SELECT COUNT(orderId) FROM orders "+
                "WHERE YEAR(date) = YEAR(CURDATE()) "+
                "AND MONTH(date) = MONTH(CURDATE()) ";

        ResultSet resultSet=CrudUtil.execute(sql);

        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    private String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("ORD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "ORD-" + digit;
        }
        return "ORD-001";
    }

    public boolean save(Order entity) throws SQLException {
        String sql = "INSERT INTO orders(orderId,date,time,deliveryStatus,payment,custId) VALUES(?,?,?,?,?,?)";

        return  CrudUtil.execute(
                sql,
                entity.getOrderId(),
                entity.getDate(),
                entity.getTime(),
                entity.getDeliveryStatus(),
                entity.getPayment(),
                entity.getCustId()
        );
    }

    public ObservableList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.itemName,COUNT(orderitemdetail.itemId) "+
                "FROM orderitemdetail "+
                "INNER JOIN item "+
                "ON item.itemId = orderitemdetail.itemId " +
                "INNER JOIN orders " +
                "ON orderitemdetail.orderId=orders.orderId " +
                "WHERE MONTH(orders.date) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.itemName " +
                "LIMIT 5 ";
        ObservableList<PieChart.Data> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;
    }

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
        String sql= "SELECT MONTHNAME(date) AS month,SUM(payment) AS total_income FROM orders WHERE YEAR(date)=? GROUP BY month ORDER BY month desc";

        List<XYChart.Data<String, Double>> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql,year);

        while(resultSet.next()){
            String month = resultSet.getString("month");
            double income = resultSet.getDouble("total_income");
            data.add(new XYChart.Data<>(month, income));
        }
        return data;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
