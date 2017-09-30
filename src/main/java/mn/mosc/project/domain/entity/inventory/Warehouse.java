package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.address.Address;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "WAREHOUSE")
public class Warehouse {
    private String warehouseCode;
    private Address address;

    @DynamoDBHashKey(attributeName = "warehouseCode")
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
