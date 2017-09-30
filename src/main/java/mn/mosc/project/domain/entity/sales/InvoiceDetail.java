package mn.mosc.project.domain.entity.sales;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.inventory.ProductTrax;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "INVOICE_DETAIL")
public class InvoiceDetail {
    private String id;
    private ProductTrax productTrax;
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

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "productTrax")
    public ProductTrax getProductTrax() {
        return productTrax;
    }

    public void setProductTrax(ProductTrax productTrax) {
        this.productTrax = productTrax;
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
