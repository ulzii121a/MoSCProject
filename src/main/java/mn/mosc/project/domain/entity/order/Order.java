package mn.mosc.project.domain.entity.order;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

import java.util.Date;
import java.util.Set;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "ORDER")
public class Order {
    private String id;
    private String userId;
    private Set<String> orderDetailIds;
    private String orderName;
    private Date orderDate;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @DynamoDBAttribute(attributeName = "orderDetailIds")
    public Set<String> getOrderDetailIds() {
        return orderDetailIds;
    }

    public void setOrderDetailIds(Set<String> orderDetailIds) {
        this.orderDetailIds = orderDetailIds;
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
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderDetailIds=" + orderDetailIds +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                ", version=" + version +
                '}';
    }
}
