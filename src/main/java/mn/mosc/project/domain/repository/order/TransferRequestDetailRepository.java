package mn.mosc.project.domain.repository.order;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.order.TransferRequestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by loya on 9/29/17
 */
@Component
public class TransferRequestDetailRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    @Autowired
    public TransferRequestDetailRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public TransferRequestDetail getTransferRequestDetail(String id) {
        try {
            TransferRequestDetail partitionKey = new TransferRequestDetail();
            partitionKey.setId(id);

            DynamoDBQueryExpression<TransferRequestDetail> queryExpression = new DynamoDBQueryExpression<TransferRequestDetail>()
                    .withHashKeyValues(partitionKey);

            List<TransferRequestDetail> propertyUtils = dynamoDBMapper.query(TransferRequestDetail.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.getTransferRequestDetail: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putTransferRequestDetail(TransferRequestDetail transferRequestDetail) {
        try {
            dynamoDBMapper.save(transferRequestDetail);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(transferRequestDetail);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.putOrderDetail: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(TransferRequestDetail.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
