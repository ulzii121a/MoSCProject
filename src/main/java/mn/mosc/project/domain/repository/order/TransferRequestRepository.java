package mn.mosc.project.domain.repository.order;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.order.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by loya on 9/29/17
 */
@Component
public class TransferRequestRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    @Autowired
    public TransferRequestRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public TransferRequest getTransferRequest(String id) {
        try {
            TransferRequest partitionKey = new TransferRequest();
            partitionKey.setId(id);

            DynamoDBQueryExpression<TransferRequest> queryExpression = new DynamoDBQueryExpression<TransferRequest>()
                    .withHashKeyValues(partitionKey);

            List<TransferRequest> propertyUtils = dynamoDBMapper.query(TransferRequest.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderAdapter.getOrder: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putTransferRequest(TransferRequest transferRequest) {
        try {
            dynamoDBMapper.save(transferRequest);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(transferRequest);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderAdapter.putOrder: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(TransferRequest.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
