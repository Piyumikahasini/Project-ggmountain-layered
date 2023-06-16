package lk.ijse.projectggmountain.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.Item;
import lk.ijse.projectggmountain.entity.OrderDetail;
import lk.ijse.projectggmountain.entity.SupplierOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    public lk.ijse.projectggmountain.entity.Item findById(String id) throws SQLException;

    public List<String> loadItemId() throws SQLException;

    public boolean updateQty(OrderDetail placeorder) throws SQLException;

    public boolean addQty(SupplierOrderDetail supplierOrderDetail) throws SQLException;

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException;

}
