package lk.ijse.projectggmountain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryDTO {
    private String delid;
    private String deldate;
    private String delsts;
    private String loc;
    private String ordid;
    private String empid;
}
