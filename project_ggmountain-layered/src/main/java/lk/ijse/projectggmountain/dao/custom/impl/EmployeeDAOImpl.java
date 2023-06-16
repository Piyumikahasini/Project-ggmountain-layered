package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.EmployeeDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.db.DBConnection;
import lk.ijse.projectggmountain.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(empId,empName,nic,dob,address,email,jobTitle,contact,salary) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql, employee.getEmpId(), employee.getEmpName(), employee.getNic(), employee.getDob(), employee.getAddress(), employee.getEmail(), employee.getJobTitle(), employee.getContact(), employee.getSalary());
    }

    public ArrayList<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        ArrayList<Employee> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9)
            ));
        }
        return arrayList;
    }

    public Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE empId=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9)

            ));
        }
        return null;
    }

    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET empName=?,nic=?,dob=?,address=?,email=?,jobTitle=?,contact=?,salary=? WHERE empId=?";
        return CrudUtil.execute(sql, employee.getEmpName(), employee.getNic(), employee.getDob(), employee.getAddress(), employee.getEmail(), employee.getJobTitle(), employee.getContact(), employee.getSalary(), employee.getEmpId());
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE empId=?";
        return CrudUtil.execute(sql,id);
    }

    public List<String> loadIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT empId FROM employee");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
