package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.EmployeeBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.EmployeeDAO;
import lk.ijse.projectggmountain.dto.EmployeeDTO;
import lk.ijse.projectggmountain.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save( new Employee(dto.getId(), dto.getName(),dto.getNic(),dto.getDob(),dto.getAddress(),dto.getEmail(),dto.getJob(),dto.getContact(),dto.getSalary()));
    }

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList = new ArrayList<>();

        for (Employee e : all) {
            arrayList.add(new EmployeeDTO(e.getEmpId(), e.getEmpName(),e.getNic(),e.getDob(),e.getAddress(),e.getEmail(),e.getJobTitle(),e.getContact(),e.getSalary()));
        }
        return arrayList;
    }


    public EmployeeDTO findByEmployeeId(String id) throws SQLException {
        Employee employee = employeeDAO.findById(id);
        return new EmployeeDTO(employee.getEmpId(), employee.getEmpName(),employee.getNic(),employee.getDob(),employee.getAddress(),employee.getEmail(),employee.getJobTitle(),employee.getContact(),employee.getSalary());
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(), dto.getName(),dto.getNic(),dto.getDob(),dto.getAddress(),dto.getEmail(),dto.getJob(),dto.getContact(),dto.getSalary()));
    }

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
}
