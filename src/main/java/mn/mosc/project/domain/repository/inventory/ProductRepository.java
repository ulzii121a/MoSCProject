package mn.mosc.project.domain.repository.inventory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.inventory.Product;

import java.util.List;

/**
 * created by loya on 9/29/17
 */

public class ProductRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    public ProductRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public Product getProduct(String id) {
        try {
            Product partitionKey = new Product();
            partitionKey.setId(id);

            DynamoDBQueryExpression<Product> queryExpression = new DynamoDBQueryExpression<Product>()
                    .withHashKeyValues(partitionKey);

            List<Product> propertyUtils = dynamoDBMapper.query(Product.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in ProductAdapter.getProduct: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putProduct(Product product) {
        try {
            dynamoDBMapper.save(product);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(product);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in ProductAdapter.putProduct: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Product.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in ProductAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
