package lk.ijse.projectggmountain.entity;

public class OrderDetail {
    private String orderId;
    private String itemId;
    private Integer orderQty;

    public OrderDetail(String orderId, String itemId, Integer orderQty) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderQty = orderQty;
    }

    public OrderDetail(String itemId, Integer orderQty) {
        this.itemId = itemId;
        this.orderQty = orderQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
