package lk.ijse.projectggmountain.dao.custom.impl;

import javafx.scene.chart.PieChart;
import lk.ijse.projectggmountain.dao.custom.QueryDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.Custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    public ArrayList<Custom> getAll() throws SQLException {
        String sql = "SELECT orderItemDetail.orderId,orderItemDetail.itemId,item.itemName,orderItemDetail.orderQty "+
                "FROM orderItemDetail "+
                "INNER JOIN item "+
                "ON orderItemDetail.itemId=item.itemId";

        ArrayList<Custom> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            Custom custom = new Custom();
            custom.setOrderId(resultSet.getString(1));
            custom.setItemId(resultSet.getString(2));
            custom.setItemName(resultSet.getString(3));
            custom.setOrderQty(resultSet.getInt(4));

            all.add(custom);
        }
        return all;
    }

    public Custom fillFields(String orderid) throws SQLException {
        String sql = "SELECT orders.custId,customer.custName,orders.deliveryStatus,orders.date,orders.time,orders.payment "+
                "FROM orders "+
                "INNER JOIN customer "+
                "ON orders.custId=customer.custId "+
                "WHERE orders.orderId=? ";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ResultSet resultSet=CrudUtil.execute(sql,orderid);
        if(resultSet.next()){
            LocalDate localDate = LocalDate.parse(resultSet.getString(4), formatter);
            Custom custom =new Custom();
            custom.setCustId(resultSet.getString(1));
            custom.setCustName(resultSet.getString(2));
            custom.setDeliveryStatus(resultSet.getString(3));
            custom.setDate(localDate);
            custom.setTime(resultSet.getTime(5).toLocalTime());
            custom.setPayment(resultSet.getDouble(6));
            return custom;
        }
        return null;
    }

    public ArrayList<Custom> searchbyorderdate(String date) throws SQLException {
        String sql = "SELECT orderItemDetail.orderId,orderItemDetail.itemId,item.itemName,orderItemDetail.orderQty "+
                "FROM orderItemDetail "+
                "INNER JOIN item "+
                "ON orderItemDetail.itemId=item.itemId "+
                "INNER JOIN orders "+
                "ON orderItemDetail.orderId=orders.orderId "+
                "WHERE orders.date=? ";

        ArrayList<Custom> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            Custom custom = new Custom();
            custom.setOrderId(resultSet.getString(1));
            custom.setItemId(resultSet.getString(2));
            custom.setItemName(resultSet.getString(3));
            custom.setOrderQty(resultSet.getInt(4));
            all.add(custom);
        }
        return all;
    }

    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.itemName,COUNT(orderItemDetail.itemId) "+
                "FROM orderItemDetail "+
                "INNER JOIN item "+
                "ON item.itemId = orderItemDetail.itemId " +
                "INNER JOIN orders " +
                "ON orderItemDetail.orderId=orders.orderId " +
                "WHERE MONTH(orders.date) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.itemName " +
                "LIMIT 5 ";

        ArrayList<PieChart.Data> all = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            all.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return all;
    }

}
