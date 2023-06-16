package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public UserDTO findByUserName(String id) throws SQLException;
}
