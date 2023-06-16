package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.UserDTO;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;
}
