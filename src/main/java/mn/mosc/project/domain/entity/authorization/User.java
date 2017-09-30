package mn.mosc.project.domain.entity.authorization;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

/**
 * created by ubulgan on 9/29/17
 */

@DynamoDBTable(tableName = "USER")
public class User {
    private String userName;
    private String email;
    private Boolean isLocked;
    private Role role;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "isLocked")
    public Boolean isLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //--------Optimistic Locking:
    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isLocked=" + isLocked +
                ", role=" + role +
                ", version=" + version +
                '}';
    }
}