package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.dao.SuperDAO;
import lk.ijse.projectggmountain.dto.UserDTO;

import java.sql.SQLException;

public interface ForgotPasswordBO extends SuperDAO {
    public UserDTO findByUserName(String id) throws SQLException;

    public boolean updatePassword(String username, String newpassword) throws SQLException;
}
