package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.SupplierOrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {

    @Override
    public ArrayList<SupplierOrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SupplierOrderDetail supplierOrderDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplierorderdetail(supplierOrderId,itemId,supId,qty,loadDate,loadTime,price)" +
                "VALUES(?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                supplierOrderDetail.getSupplierOrderId(),
                supplierOrderDetail.getItemId(),
                supplierOrderDetail.getSupId(),
                supplierOrderDetail.getQty(),
                supplierOrderDetail.getLoadDate(),
                supplierOrderDetail.getLoadTime(),
                supplierOrderDetail.getPrice()
        );
    }

    @Override
    public boolean update(SupplierOrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextSupplyLoadId() throws SQLException {
        String sql = "SELECT supplierOrderId FROM supplierorderdetail ORDER BY supplierOrderId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

}

