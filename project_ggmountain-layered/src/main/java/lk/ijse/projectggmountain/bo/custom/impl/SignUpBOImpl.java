package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.SignUpBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.UserDAO;
import lk.ijse.projectggmountain.dto.UserDTO;
import lk.ijse.projectggmountain.entity.User;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getName(),dto.getPassword(),dto.getJobTitle(),dto.getEmail()));
    }
}
