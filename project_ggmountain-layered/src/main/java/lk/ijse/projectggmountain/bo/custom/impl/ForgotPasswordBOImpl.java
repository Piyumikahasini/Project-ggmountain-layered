package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.ForgotPasswordBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.UserDAO;
import lk.ijse.projectggmountain.dto.UserDTO;
import lk.ijse.projectggmountain.entity.User;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgotPasswordBO {
    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public UserDTO findByUserName(String id) throws SQLException {
        User user = userDAO.findByUserName(id);
        return new UserDTO(user.getUserName(),user.getPassword(),user.getJobTitle(),user.getEmail());
    }

    public boolean updatePassword(String username, String newpassword) throws SQLException {
        return userDAO.updatePassword(username,newpassword);
    }
}