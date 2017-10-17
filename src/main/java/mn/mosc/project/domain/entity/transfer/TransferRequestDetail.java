package mn.mosc.project.domain.entity.transfer;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "TRANSFER_REQUEST_DETAIL")
public class TransferRequestDetail {
    private String id;
    private String transferRequestId;
    private String productId;
    private int quantity;
    private Double unitPrice;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "transfer_request_id")
    public String getTransferRequestId() {
        return transferRequestId;
    }

    public void setTransferRequestId(String transferRequestId) {
        this.transferRequestId = transferRequestId;
    }

    @DynamoDBAttribute(attributeName = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @DynamoDBAttribute(attributeName = "unit_price")
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

    @Override
    public String toString() {
        return "TransferRequestDetail{" +
                "id='" + id + '\'' +
                ", transferRequestId='" + transferRequestId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", version=" + version +
                '}';
    }
}
