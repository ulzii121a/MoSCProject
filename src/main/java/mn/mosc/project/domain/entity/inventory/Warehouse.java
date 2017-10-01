package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.address.Address;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "WAREHOUSE")
public class Warehouse {
    private String id;
    private String addressId;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "addressId")
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String address) {
        this.addressId = addressId;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
