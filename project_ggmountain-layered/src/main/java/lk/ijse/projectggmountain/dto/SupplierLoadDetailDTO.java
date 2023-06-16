package lk.ijse.projectggmountain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplierLoadDetailDTO {
    private String supplierOrderId;
    private String SupId;
    private Double price;
}
