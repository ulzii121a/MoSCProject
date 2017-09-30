package mn.mosc.project.domain.entity.authorization;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

/**
 * created by loya on 9/29/17
 */
public class Permission {
    private String id;
    private String permissionName;
    private String description;
    private String version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "permissionName")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBVersionAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
