package mn.mosc.project.domain.entity.order;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.authorization.User;

import java.util.Date;
import java.util.List;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "ORDER")
public class Order {
    private String id;
    private OrderDetail orderDetailId;
    private String orderName;
    private Date orderDate;
    private User user;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "")
    public OrderDetail getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(OrderDetail orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @DynamoDBAttribute(attributeName = "orderName")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @DynamoDBAttribute(attributeName = "orderDate")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
