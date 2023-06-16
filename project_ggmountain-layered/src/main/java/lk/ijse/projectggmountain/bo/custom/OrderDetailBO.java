package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    public ArrayList<CustomDTO> getAll() throws SQLException;

    public CustomDTO fillFields(String orderid) throws SQLException;

    public int totalOrdersToday() throws SQLException;

    public int totalOrdersMonth() throws SQLException;

    public ArrayList<CustomDTO> searchbyorderdate(String date) throws SQLException;
}
