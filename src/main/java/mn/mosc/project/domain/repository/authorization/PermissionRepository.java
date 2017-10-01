package mn.mosc.project.domain.repository.authorization;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.authorization.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by ubulgan on 9/30/17
 */
@Component
public class PermissionRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    @Autowired
    public PermissionRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public Permission getPermission(String id) {
        try {
            Permission partitionKey = new Permission();
            partitionKey.setId(id);

            DynamoDBQueryExpression<Permission> queryExpression = new DynamoDBQueryExpression<Permission>()
                    .withHashKeyValues(partitionKey);

            List<Permission> propertyUtils = dynamoDBMapper.query(Permission.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in PermissionRepository.getPermission: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putPermission(Permission permission) {
        try {
            dynamoDBMapper.save(permission);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(permission);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in PermissionRepository.putPermission: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Permission.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in PermissionRepository.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
