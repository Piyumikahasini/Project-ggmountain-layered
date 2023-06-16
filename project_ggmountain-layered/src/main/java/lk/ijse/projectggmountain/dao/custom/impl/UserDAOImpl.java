package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.UserDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(User user) throws SQLException {
        String sql = "INSERT INTO user(userName,password,jobTitle,email) " +
                "VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, user.getUserName(), user.getPassword(), user.getJobTitle(), user.getEmail());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    public User findByUserName(String id) throws SQLException {
        String sql = "SELECT * FROM user WHERE userName=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    public boolean updatePassword(String username, String newpassword) throws SQLException {
        String sql = "UPDATE user SET password =? WHERE userName = ?";

        return CrudUtil.execute(sql,newpassword,username);
    }
}
