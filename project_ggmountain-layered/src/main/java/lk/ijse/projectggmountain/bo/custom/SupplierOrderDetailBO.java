package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.InventoryDTO;
import lk.ijse.projectggmountain.dto.NewLoadSupplierDTO;
import lk.ijse.projectggmountain.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierOrderDetailBO extends SuperBO {
    public List<String> loadSupplierIds() throws SQLException;

    public List<String> loadItemIds() throws SQLException;

    public String getNextSupplyLoadId() throws SQLException;

    public boolean placeLoad(String loadid, String suppid, String totalprice, List<NewLoadSupplierDTO> placeSupplyLoadList) throws SQLException, ClassNotFoundException;

    public SupplierDTO findBySupId(String supp_id) throws SQLException;

    public InventoryDTO findBy(String id) throws SQLException;

}
