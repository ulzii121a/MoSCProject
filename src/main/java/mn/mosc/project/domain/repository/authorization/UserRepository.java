package mn.mosc.project.domain.repository.authorization;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.authorization.User;

import java.util.List;

/**
 * created by ubulgan on 9/29/17
 */
public class UserRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    public UserRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public User getUser(String id) {
        try {
            User partitionKey = new User();
            partitionKey.setId(id);

            DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                    .withHashKeyValues(partitionKey);

            List<User> propertyUtils = dynamoDBMapper.query(User.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in UserAdapter.getUser: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    void putUser(User User) {
        try {
            dynamoDBMapper.save(User);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in UserAdapter.putUser: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(User.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in UserAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}