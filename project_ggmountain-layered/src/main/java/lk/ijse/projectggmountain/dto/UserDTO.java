package lk.ijse.projectggmountain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String name;
    private String password;
    private String email;
    private String jobTitle;
}
