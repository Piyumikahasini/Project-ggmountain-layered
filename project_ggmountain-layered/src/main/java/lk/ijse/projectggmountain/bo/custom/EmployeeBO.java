package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public EmployeeDTO findByEmployeeId(String id) throws SQLException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

}
