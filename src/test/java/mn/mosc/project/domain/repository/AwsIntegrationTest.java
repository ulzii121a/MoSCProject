package mn.mosc.project.domain.repository;

import static junit.framework.TestCase.assertNotNull;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.entity.inventory.Product;
import mn.mosc.project.domain.entity.transfer.TransferRequest;
import mn.mosc.project.domain.entity.transfer.TransferRequestDetail;
import mn.mosc.project.domain.repository.authorization.UserRepository;
import mn.mosc.project.domain.repository.inventory.ProductRepository;
import mn.mosc.project.domain.repository.transfer.TransferRequestDetailRepository;
import mn.mosc.project.domain.repository.transfer.TransferRequestRepository;
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
    private TransferRequestRepository transferRequestRepository;
    private TransferRequestDetailRepository transferRequestDetailRepository;
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
            transferRequestDetailRepository = new TransferRequestDetailRepository(dynamoDBMapper, amazonDynamoDB);
            transferRequestRepository = new TransferRequestRepository(dynamoDBMapper, amazonDynamoDB);
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
        user.setId(userId);

        userRepository.putUser(user);
    }

    @Ignore
    public void getTransferRequestDetail() {
        TransferRequestDetail transferRequestDetail = transferRequestDetailRepository.getTransferRequestDetail("OrderDetail1");
        System.out.println(transferRequestDetail);
        assertNotNull(transferRequestDetail);
    }

    @Ignore
    public void putTransferRequestDetail() {
        TransferRequestDetail transferRequestDetail = new TransferRequestDetail();
        transferRequestDetail.setId("TranferRequestDetail1");
        transferRequestDetail.setProductId("p1");
        transferRequestDetail.setTransferRequestId("testTransferRequest1");
        transferRequestDetail.setQuantity(1);
        transferRequestDetail.setUnitPrice(500d);

        transferRequestDetailRepository.putTransferRequestDetail(transferRequestDetail);
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
    public void getTransferRequest() {
        TransferRequest transferRequest = transferRequestRepository.getTransferRequest("testOrder1");
        System.out.println(transferRequest);
        assertNotNull(transferRequest);
    }

    @Ignore
    public void putTransferRequest() {
        Set<String> orderDetails = new HashSet<>();
        orderDetails.add("TransferRequestDetail1");

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setId("testTransferRequest1");
        transferRequest.setTransferRequestDate(new Date());
        transferRequest.setUserId(userId);
        transferRequest.setTransferRequestDetails(orderDetails);

        transferRequestRepository.putTransferRequest(transferRequest);
    }
}
