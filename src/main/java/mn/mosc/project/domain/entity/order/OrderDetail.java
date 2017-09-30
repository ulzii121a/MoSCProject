package mn.mosc.project.domain.entity.order;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.inventory.Product;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "ORDER_DETAIL")
public class OrderDetail {
    private String id;
    private Order order;
    private Product product;
    private int quantity;
    private Double unitPrice;
    private Long version;

    @DynamoDBAttribute(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBHashKey(attributeName = "order")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @DynamoDBRangeKey(attributeName = "product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @DynamoDBAttribute(attributeName = "unitPrice")
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
