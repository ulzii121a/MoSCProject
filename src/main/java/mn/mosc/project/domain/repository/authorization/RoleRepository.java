package mn.mosc.project.domain.repository.authorization;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.authorization.Role;

import java.util.List;

/**
 * created by loya on 9/29/17
 */

public class RoleRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    public RoleRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public Role getRole(String id) {
        try {
            Role partitionKey = new Role();
            partitionKey.setId(id);

            DynamoDBQueryExpression<Role> queryExpression = new DynamoDBQueryExpression<Role>()
                    .withHashKeyValues(partitionKey);

            List<Role> propertyUtils = dynamoDBMapper.query(Role.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in RoleAdapter.getRole: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putRole(Role role) {
        try {
            dynamoDBMapper.save(role);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(role);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in RoleAdapter.putRole: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Role.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in RoleAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
