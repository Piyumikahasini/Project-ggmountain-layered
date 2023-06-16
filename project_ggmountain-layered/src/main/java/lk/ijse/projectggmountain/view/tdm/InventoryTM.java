package lk.ijse.projectggmountain.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryTM {
    private String id;
    private String name;
    private String category;
    private Double unitPrice;
    private String qtyOnHand;
}
