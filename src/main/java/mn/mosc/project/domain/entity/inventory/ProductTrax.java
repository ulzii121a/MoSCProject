package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.order.OrderDetail;

import java.util.Date;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "PRODUCT_TRAX")
public class ProductTrax {
    private String id;
    private Warehouse fromWH;
    private Warehouse toWH;
    private OrderDetail orderDetail;
    private Double quantity;
    private Date date;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "fromWH")
    public Warehouse getFromWH() {
        return fromWH;
    }

    public void setFromWH(Warehouse fromWH) {
        this.fromWH = fromWH;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "toWH")
    public Warehouse getToWH() {
        return toWH;
    }

    public void setToWH(Warehouse toWH) {
        this.toWH = toWH;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "orderDetail")
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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
}
