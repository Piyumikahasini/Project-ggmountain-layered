package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.SupplierOrderDetail;

import java.sql.SQLException;

public interface SupplierOrderDetailDAO extends CrudDAO<SupplierOrderDetail,String> {

    public String getNextSupplyLoadId() throws SQLException;

}
