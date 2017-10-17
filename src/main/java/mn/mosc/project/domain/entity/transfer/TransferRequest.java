package mn.mosc.project.domain.entity.transfer;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Date;
import java.util.Set;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "TRANSFER_REQUEST")
public class TransferRequest {
    private String id;
    private String userId;
    private String transferRequestTitle;
    private Date transferRequestDate;
    private Set<String> transferRequestDetails;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "transfer_request_title")
    public String getTransferRequestTitle() {
        return transferRequestTitle;
    }

    public void setTransferRequestTitle(String transferRequestTitle) {
        this.transferRequestTitle = transferRequestTitle;
    }

    @DynamoDBAttribute(attributeName = "order_date")
    public Date getTransferRequestDate() {
        return transferRequestDate;
    }

    public void setTransferRequestDate(Date transferRequestDate) {
        this.transferRequestDate = transferRequestDate;
    }

    @DynamoDBAttribute(attributeName = "transfer_request_details")
    public Set<String> getTransferRequestDetails() {
        return transferRequestDetails;
    }

    public void setTransferRequestDetails(Set<String> transferRequestDetails) {
        this.transferRequestDetails = transferRequestDetails;
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
        return "TransferRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderDetailIds=" + transferRequestDetails +
                ", transferRequestTitle='" + transferRequestTitle + '\'' +
                ", transferRequestDate=" + transferRequestDate +
                ", version=" + version +
                '}';
    }
}
