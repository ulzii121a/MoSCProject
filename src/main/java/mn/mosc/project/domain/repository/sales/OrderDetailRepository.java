package mn.mosc.project.domain.repository.sales;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.util.CollectionUtils;
import mn.mosc.project.domain.entity.sales.OrderDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Loya on 10/16/17
 */

@Component
public class OrderDetailRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;
    private static final Long dynamoDBInitialThroughput = 25L;

    private static final Log LOGGER = LogFactory.getLog(OrderDetailRepository.class);

    @Autowired
    public OrderDetailRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }

    public OrderDetail getOrderDetail(String id) {
        try {
            OrderDetail partitionKey = new OrderDetail();
            partitionKey.setId(id);

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

    public List<OrderDetail> getOrderDetails() {
        try {
            return dynamoDBMapper.scan(OrderDetail.class, new DynamoDBScanExpression());
        } catch (ResourceNotFoundException resourceNotFoundException) {
            createTable();
            return null;
        } catch (Exception e) {
            String errorMessage = String.format("Exception in PropertyRepository.getOrderDetails: %s", e.getMessage());
            LOGGER.error(errorMessage, e);
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
