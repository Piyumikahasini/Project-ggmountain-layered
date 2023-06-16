package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.CustomerDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.db.DBConnection;
import lk.ijse.projectggmountain.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(custId,custName,address,email,contact) " +
                "VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, customer.getCustId(), customer.getCustName(), customer.getAddress(), customer.getEmail(), customer.getContact());
    }

    public boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET custName=?,address=?,email=?,contact=? WHERE custId=?";
        return CrudUtil.execute(sql, customer.getCustName(), customer.getAddress(), customer.getEmail(), customer.getContact(), customer.getCustId());

    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE custId=?";
        return CrudUtil.execute(sql, id);
    }

    public ArrayList<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return arrayList;
    }

    public Customer findById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE custId=?";

        ResultSet resultSet = CrudUtil.execute(sql, id);
        if (resultSet.next()) {
            return (new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return null;
    }

    public List<String> loadCustomerIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT custId FROM Customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public String getCustName(String custId) throws SQLException {
        String sql = "SELECT custName FROM customer WHERE custId=?";
        ResultSet resultSet = CrudUtil.execute(sql, custId);

        if (resultSet.next()) {
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }
}
