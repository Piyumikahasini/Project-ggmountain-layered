package lk.ijse.projectggmountain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeDTO {
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
