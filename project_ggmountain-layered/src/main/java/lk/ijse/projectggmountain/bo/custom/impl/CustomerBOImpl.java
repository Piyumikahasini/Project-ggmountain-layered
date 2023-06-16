package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.CustomerBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.CustomerDAO;
import lk.ijse.projectggmountain.dto.CustomerDTO;
import lk.ijse.projectggmountain.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save( new Customer(dto.getId(), dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();

        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getCustId(),c.getCustName(),c.getAddress(),c.getEmail(),c.getContact()));
        }
        return arrayList;
    }

    public CustomerDTO findByCustomerId(String id) throws SQLException {
        Customer customer = customerDAO.findById(id);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getAddress(),customer.getEmail(),customer.getContact());
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
}
