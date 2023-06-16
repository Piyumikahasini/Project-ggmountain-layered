package lk.ijse.projectggmountain.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeTM {
    private String id;
    private String name;
    private String nic;
    private String dob;
    private String address;
    private String email;
    private String job;
    private String contact;
    private Double salary;
}
