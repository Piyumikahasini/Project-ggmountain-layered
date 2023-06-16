package lk.ijse.projectggmountain.view.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddSupplyLoadTM {
    String itemId;
    String itemName;
    String category;
    Integer quantity;
    Button action;
}
