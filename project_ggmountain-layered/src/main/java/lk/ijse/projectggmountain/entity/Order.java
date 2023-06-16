package lk.ijse.projectggmountain.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderId;
    private LocalDate date;
    private LocalTime time;
    private Boolean deliveryStatus;
    private Double payment;
    private String custId;

}
