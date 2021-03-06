package mn.mosc.project.domain.repository.transfer;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.transfer.TransferRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log LOGGER = LogFactory.getLog(TransferRequestRepository.class);

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
            String errorMessage = String.format("Exception in TransferRequestAdapter.getTransferRequest: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public List<TransferRequest> getTransferRequests() {
        try {
            return dynamoDBMapper.scan(TransferRequest.class, new DynamoDBScanExpression());
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in AppPropertyRepository.getTransferRequests: %s", e.getMessage());
            LOGGER.error(errorMessage, e);
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
            String errorMessage = String.format("Exception in TransferRequestAdapter.putTransferRequest: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(TransferRequest.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in TransferRequestAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
