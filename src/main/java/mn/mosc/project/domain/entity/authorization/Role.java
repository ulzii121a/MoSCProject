package mn.mosc.project.domain.entity.authorization;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "ROLE")
public class Role {
    private String id;
    private String roleName;
    private String description;
    private List<Permission> permissions;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "roleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.L)
    @DynamoDBAttribute(attributeName = "permissions")
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
