package mn.mosc.project.domain.repository.order;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.inventory.Product;
import mn.mosc.project.domain.entity.order.Order;
import mn.mosc.project.domain.entity.order.OrderDetail;

import java.util.List;

/**
 * created by loya on 9/29/17
 */

public class OrderDetailRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    public OrderDetailRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public OrderDetail getOrderDetail(Order order, Product product) {
        try {
            OrderDetail partitionKey = new OrderDetail();
            partitionKey.setProduct(product);

            DynamoDBQueryExpression<OrderDetail> queryExpression = new DynamoDBQueryExpression<OrderDetail>()
                    .withHashKeyValues(partitionKey);

            List<OrderDetail> propertyUtils = dynamoDBMapper.query(OrderDetail.class, queryExpression);

            return CollectionUtils.isNullOrEmpty(propertyUtils) ? null : propertyUtils.get(0);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.getOrderDetail: %s", e.getMessage());
            System.err.println(errorMessage);
            return null;
        }
    }

    public void putOrderDetail(OrderDetail orderDetail) {
        try {
            dynamoDBMapper.save(orderDetail);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            dynamoDBMapper.save(orderDetail);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.putOrderDetail: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }

    private void createTable() {
        try {
            CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(OrderDetail.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(dynamoDBInitialThroughput, dynamoDBInitialThroughput));

            dynamoDBClient.createTable(req);
        } catch (Exception e) {
            String errorMessage = String.format("Exception in OrderDetailAdapter.createTable: %s", e.getMessage());
            System.err.println(errorMessage);
        }
    }
}
