package mn.mosc.project.domain.repository.inventory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.inventory.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by loya on 9/29/17
 */
@Component
public class CategoryRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    @Autowired
    public CategoryRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public Category getCategory(String id) {
        try {
            Category partitionKey = new Category();
            partitionKey.setId(id);

            DynamoDBQueryExpression<Category> queryExpression = new DynamoDBQueryExpression<Category>()
                    .withHashKeyValues(partitionKey);

            List<Category> propertyUtils = dynamoDBMapper.query(Category.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in CategoryAdapter.getCategory: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putCategory(Category category) {
        try {
            dynamoDBMapper.save(category);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(category);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in CategoryAdapter.putCategory: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Category.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in CategoryAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
