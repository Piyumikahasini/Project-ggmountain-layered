package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.SupplierBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.SupplierDAO;
import lk.ijse.projectggmountain.dto.SupplierDTO;
import lk.ijse.projectggmountain.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }

    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO> arrayList = new ArrayList<>();

        for (Supplier s : all) {
            arrayList.add(new SupplierDTO(s.getSupId(),s.getSupName(),s.getAddress(),s.getEmail(),s.getContact()));
        }
        return arrayList;
    }

    @Override
    public SupplierDTO findBy(String id) throws SQLException {
        Supplier supplier= supplierDAO.findById(id);
        return new SupplierDTO(supplier.getSupId(),supplier.getSupName(),supplier.getAddress(),supplier.getEmail(),supplier.getContact());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
}
