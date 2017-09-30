package mn.mosc.project.domain.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.repository.authorization.UserRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertNotNull;

/**
 * created by ubulgan on 9/29/17
 */
public class AwsIntegrationTest {

    private UserRepository userRepository;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUser() {
        User user = userRepository.getUser(userId);
        System.out.println(user);
        assertNotNull(user);
    }

    @Ignore
    public void putUser() {
        User user = new User();
        user.setId(userId);
        user.setUserName("Test User");

        userRepository.putUser(user);
    }
}
