package lk.ijse.projectggmountain.entity;

public class Item {
    private String itemId;
    private String itemName;
    private String category;
    private Double unitPrice;
    private String QtyOnHand;

    public Item(String itemId, String itemName, String category, Double unitPrice, String qtyOnHand) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.unitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
    }

    public Item(){}

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", unitPrice=" + unitPrice +
                ", QtyOnHand='" + QtyOnHand + '\'' +
                '}';
    }
}
