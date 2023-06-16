package lk.ijse.projectggmountain.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.projectggmountain.dao.custom.ItemDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.db.DBConnection;
import lk.ijse.projectggmountain.entity.Item;
import lk.ijse.projectggmountain.entity.OrderDetail;
import lk.ijse.projectggmountain.entity.SupplierOrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO item(itemId,itemName,category,unitPrice,qtyOnHand)" +
                "VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, item.getItemId(), item.getItemName(), item.getCategory(), item.getUnitPrice(), item.getQtyOnHand());
    }

    public boolean update(Item item) throws SQLException {
        String sql = "UPDATE item SET itemName=?,category=?,unitPrice=?,qtyOnHand=? WHERE itemId=?";
        return CrudUtil.execute(sql, item.getItemName(), item.getCategory(), item.getUnitPrice(), item.getQtyOnHand(), item.getItemId());
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE itemId=?";
        return CrudUtil.execute(sql,id);
    }

    public Item findById(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemId=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return null;
    }

    public ArrayList<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

        ArrayList<Item> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
                )
            );
        }
        return arrayList;
    }

    public List<String> loadItemId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT itemId FROM Item");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public boolean updateQty(OrderDetail placeorder) throws SQLException {
        System.out.println("updateQty");
        String sql = "UPDATE item SET QtyOnHand = (QtyOnHand - ?) WHERE itemId = ?";

        return CrudUtil.execute(
                sql,
                placeorder.getOrderQty(),
                placeorder.getItemId()
        );
    }

    public boolean addQty(SupplierOrderDetail supplierOrderDetail) throws SQLException {
        String sql = "UPDATE item SET QtyOnHand = (QtyOnHand + ?) WHERE itemId = ?";

        return CrudUtil.execute(
                sql,
                supplierOrderDetail.getQty(),
                supplierOrderDetail.getItemId()
        );
    }

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
        String sql="SELECT itemName,QtyOnHand FROM item WHERE QtyOnHand<=100 ";

        ArrayList<XYChart.Series<String, Integer>> all = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);

        // Creating a new series object
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while(resultSet.next()){
            String itemName = resultSet.getString("itemName");
            int itemQty = resultSet.getInt("QtyOnHand");
            series.getData().add(new XYChart.Data<>(itemName, itemQty));
        }

        all.add(series);
        return all;
    }
}
