package lk.ijse.projectggmountain.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Custom {
    private String orderId;
    private String itemId;
    private int orderQty;
    private String itemName;
    private Double unitPrice;
    private String category;
    private int QtyOnHand;
    private String custId;
    private String deliveryStatus;
    private LocalDate date;
    private LocalTime time;
    private Double payment;
    private String custName;
    private String email;
    private String contact;
}
