package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {
    public boolean updatePassword(String username, String newpassword) throws SQLException;

    public User findByUserName(String id) throws SQLException;

}
