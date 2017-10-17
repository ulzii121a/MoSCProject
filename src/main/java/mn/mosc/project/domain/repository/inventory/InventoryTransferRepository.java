package mn.mosc.project.domain.repository.inventory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.inventory.InventoryTransfer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Loya on 10/16/17
 */
@Component
public class InventoryTransferRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    private static final Log LOGGER = LogFactory.getLog(InventoryTransferRepository.class);

    @Autowired
    public InventoryTransferRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public InventoryTransfer getInventoryTransfer(String id) {
        try {
            InventoryTransfer partitionKey = new InventoryTransfer();
            partitionKey.setId(id);

            DynamoDBQueryExpression<InventoryTransfer> queryExpression = new DynamoDBQueryExpression<InventoryTransfer>()
                    .withHashKeyValues(partitionKey);

            List<InventoryTransfer> propertyUtils = dynamoDBMapper.query(InventoryTransfer.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in InventoryTransferAdapter.getInventoryTransfer: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public List<InventoryTransfer> getInventoryTransfers() {
        try {
            return dynamoDBMapper.scan(InventoryTransfer.class, new DynamoDBScanExpression());
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in PropertyRepository.getInventoryTransfers: %s", e.getMessage());
            LOGGER.error(errorMessage, e);
            return null;
        }
    }

    public void putInventoryTransfer(InventoryTransfer inventoryTransfer) {
        try {
            dynamoDBMapper.save(inventoryTransfer);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(inventoryTransfer);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in InventoryTransferAdapter.putInventoryTransfer: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(InventoryTransfer.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in InventoryTransferAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
