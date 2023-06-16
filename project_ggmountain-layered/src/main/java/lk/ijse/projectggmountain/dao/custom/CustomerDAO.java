package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public Customer findById(String id) throws SQLException;

    public List<String> loadCustomerIds() throws SQLException;

    public String getCustName(String custId) throws SQLException;
}
