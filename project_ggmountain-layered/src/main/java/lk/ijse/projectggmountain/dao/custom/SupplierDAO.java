package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    public Supplier findById(String id) throws SQLException;

    public List<String> loadIds() throws SQLException;
}
