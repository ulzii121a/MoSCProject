package mn.mosc.project.domain.entity.sales;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "ORDER_DETAIL")
public class OrderDetail {
    private String id;
    private String orderId;
    private String inventoryTransferId;
    private int quantity;
    private Double price;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "inventory_transfer_id")
    public String getInventoryTransferId() {
        return inventoryTransferId;
    }

    public void setInventoryTransferId(String inventoryTransferId) {
        this.inventoryTransferId = inventoryTransferId;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @DynamoDBAttribute(attributeName = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
