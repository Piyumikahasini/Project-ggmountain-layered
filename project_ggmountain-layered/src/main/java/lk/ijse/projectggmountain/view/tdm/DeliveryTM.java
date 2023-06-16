package lk.ijse.projectggmountain.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryTM {
    private String orderid;
    private String delid;
    private String deldate;
    private String delstatus;
    private String location;
    private String empid;
}
