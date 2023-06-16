package lk.ijse.projectggmountain.view.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartTM {
    private String itemId;
    private String itemName;
    private String category;
    private Integer quantity;
    private Double unitPrice;
    private Double total;
    private Button btn;
}
