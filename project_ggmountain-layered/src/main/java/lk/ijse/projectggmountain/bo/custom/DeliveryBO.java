package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.DeliveryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryBO extends SuperBO {
    public boolean cancelDelivery(String ordid) throws SQLException;

    public ArrayList<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException;

    public boolean updateDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteDelivery(String id) throws SQLException, ClassNotFoundException;

    public DeliveryDTO findByOrderID(String id) throws SQLException;

    public DeliveryDTO findBydeliveryId(String delid) throws SQLException;

    public ArrayList<DeliveryDTO> getByDueDate(String duedate) throws SQLException;

    public List<String> loadEmployeeIds() throws SQLException;

    public ArrayList<DeliveryDTO> getByDeliveryStatus(String delists) throws SQLException;
}
