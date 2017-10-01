package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.order.TransferRequestDetail;

import java.util.Date;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "INVENTORY_TRANSFER")
public class InventoryTransfer {
    private String id;
    private String transferRequestDetailId;
    private String fromWarehouseId;
    private String toWarehouseId;
    private String trackingNumber;
    private Double quantity;
    private Date date;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "transfer_request_detail_id")
    public String getTransferRequestDetailId() {
        return transferRequestDetailId;
    }

    public void setTransferRequestDetailId(String transferRequestDetailId) {
        this.transferRequestDetailId = transferRequestDetailId;
    }

    @DynamoDBAttribute(attributeName = "from_warehouse_id")
    public String getFromWarehouseId() {
        return fromWarehouseId;
    }

    public void setFromWarehouseId(String fromWarehouseId) {
        this.fromWarehouseId = fromWarehouseId;
    }

    @DynamoDBAttribute(attributeName = "to_warehouse_id")
    public String getToWarehouseId() {
        return toWarehouseId;
    }

    public void setToWarehouseId(String toWarehouseId) {
        this.toWarehouseId = toWarehouseId;
    }

    @DynamoDBAttribute(attributeName = "tracking_number")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @DynamoDBAttribute(attributeName = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
