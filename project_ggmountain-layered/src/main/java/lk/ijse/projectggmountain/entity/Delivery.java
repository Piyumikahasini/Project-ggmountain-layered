package lk.ijse.projectggmountain.entity;

public class Delivery {
    private String deliveryId;
    private String Date;
    private String deliveryStatus;
    private String location;
    private String orderId;
    private String empId;

    public Delivery(String deliveryId, String date, String deliveryStatus, String location, String orderId, String empId) {
        this.deliveryId = deliveryId;
        Date = date;
        this.deliveryStatus = deliveryStatus;
        this.location = location;
        this.orderId = orderId;
        this.empId = empId;
    }

    public Delivery() {

    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId='" + deliveryId + '\'' +
                ", Date='" + Date + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", location='" + location + '\'' +
                ", orderId='" + orderId + '\'' +
                ", empId='" + empId + '\'' +
                '}';
    }
}
