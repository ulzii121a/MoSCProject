package mn.mosc.project.domain.entity.sales;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.entity.inventory.ProductTrax;

import java.util.Date;
import java.util.List;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "INVOICE")
public class Invoice {
    private String invoiceNumber;
    private Date date;
    private User user;
    private List<InvoiceDetail> invoiceDetails;
    private Long version;

    @DynamoDBHashKey(attributeName = "invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @DynamoDBAttribute(attributeName = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.L)
    @DynamoDBAttribute(attributeName = "invoiceDetails")
    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
