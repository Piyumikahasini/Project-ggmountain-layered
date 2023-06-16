package lk.ijse.projectggmountain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class SupplierOrderDetail {
    private String supplierOrderId;
    private String itemId;
    private String supId;
    private Integer qty;
    private LocalDate loadDate;
    private LocalTime loadTime;
    private Double price;

    public SupplierOrderDetail(String supplierOrderId, String itemId, String supId, Integer qty, LocalDate loadDate, LocalTime loadTime, Double price) {
        this.supplierOrderId = supplierOrderId;
        this.itemId = itemId;
        this.supId = supId;
        this.qty = qty;
        this.loadDate = loadDate;
        this.loadTime = loadTime;
        this.price = price;
    }

    public SupplierOrderDetail(String itemId, Integer supQty) {
        this.itemId= itemId;
        this.qty = supQty;
    }

    public String getSupplierOrderId() {
        return supplierOrderId;
    }

    public void setSupplierOrderId(String supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public LocalDate getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(LocalDate loadDate) {
        this.loadDate = loadDate;
    }

    public LocalTime getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(LocalTime loadTime) {
        this.loadTime = loadTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SupplierOrderDetail{" +
                "supplierOrderId='" + supplierOrderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", supId='" + supId + '\'' +
                ", qty=" + qty +
                ", loadDate=" + loadDate +
                ", loadTime=" + loadTime +
                ", price=" + price +
                '}';
    }
}
