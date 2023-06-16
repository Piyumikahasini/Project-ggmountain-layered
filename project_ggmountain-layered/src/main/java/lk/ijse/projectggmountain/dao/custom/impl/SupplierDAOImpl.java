package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.SupplierDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.db.DBConnection;
import lk.ijse.projectggmountain.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier(supId,supName,address,email,contact) " +
                "VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, supplier.getSupId(), supplier.getSupName(), supplier.getAddress(), supplier.getEmail(), supplier.getContact());
    }

    public Supplier findById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supId=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return null;
    }

    public ArrayList<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        ArrayList<Supplier> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return arrayList;
    }

    public boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supName=?,address=?,email=?,contact=? WHERE supId=?";
        return CrudUtil.execute(sql, supplier.getSupName(), supplier.getAddress(), supplier.getEmail(), supplier.getContact(), supplier.getSupId());
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public List<String> loadIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT supId FROM supplier");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
