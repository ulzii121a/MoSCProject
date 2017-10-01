package mn.mosc.project.domain.repository;

import static junit.framework.TestCase.assertNotNull;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.entity.inventory.Product;
import mn.mosc.project.domain.entity.order.TransferRequest;
import mn.mosc.project.domain.entity.order.TransferRequestDetail;
import mn.mosc.project.domain.repository.authorization.UserRepository;
import mn.mosc.project.domain.repository.inventory.ProductRepository;
import mn.mosc.project.domain.repository.order.OrderDetailRepository;
import mn.mosc.project.domain.repository.order.OrderRepository;
import org.junit.Before;
import org.junit.Ignore;

import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * created by ubulgan on 9/29/17
 */
public class AwsIntegrationTest {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;

    private static final String userId = "user1";

    @Before
    public void setup() {
        try (InputStream inputStream = AwsIntegrationTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties appProperties = new Properties();
            appProperties.load(inputStream);

            System.setProperty("aws.accessKeyId", appProperties.getProperty("accessKeyId"));
            System.setProperty("aws.secretKey", appProperties.getProperty("secretKey"));
            System.setProperty("aws.region", appProperties.getProperty("region"));

            AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
            DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            userRepository = new UserRepository(dynamoDBMapper, amazonDynamoDB);
            orderDetailRepository = new OrderDetailRepository(dynamoDBMapper, amazonDynamoDB);
            orderRepository = new OrderRepository(dynamoDBMapper, amazonDynamoDB);
            productRepository = new ProductRepository(dynamoDBMapper, amazonDynamoDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void getUser() {
        User user = userRepository.getUser(userId);
        System.out.println(user);
        assertNotNull(user);
    }

    @Ignore
    public void putUser() {
        User user = new User();
        user.setUserName(userId);

        userRepository.putUser(user);
    }

    @Ignore
    public void getOrderDetail() {
        TransferRequestDetail transferRequestDetail = orderDetailRepository.getOrderDetail("OrderDetail1");
        System.out.println(transferRequestDetail);
        assertNotNull(transferRequestDetail);
    }

    @Ignore
    public void putOrderDetail() {
        TransferRequestDetail transferRequestDetail = new TransferRequestDetail();
        transferRequestDetail.setId("OrderDetail1");
        transferRequestDetail.setProductId("p1");
        transferRequestDetail.setOrderId("testOrder1");
        transferRequestDetail.setQuantity(1);
        transferRequestDetail.setUnitPrice(500d);

        orderDetailRepository.putOrderDetail(transferRequestDetail);
    }

    @Ignore
    public void putProduct() {
        Product product = new Product();
        product.setId("p1");
        product.setProductName("Prod1");

        productRepository.putProduct(product);
    }

    @Ignore
    public void getProduct() {
        Product product = productRepository.getProduct("p1");
        System.out.println(product);
        assertNotNull(product);
    }

    @Ignore
    public void getOrder() {
        TransferRequest transferRequest = orderRepository.getOrder("testOrder1");
        System.out.println(transferRequest);
        assertNotNull(transferRequest);
    }

    @Ignore
    public void putOrder() {
        Set<String> orderDetails = new HashSet<>();
        orderDetails.add("OrderDetail1");

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setId("testOrder1");
        transferRequest.setOrderDate(new Date());
        transferRequest.setUserId(userId);
        transferRequest.setOrderDetailIds(orderDetails);

        orderRepository.putOrder(transferRequest);
    }
}
