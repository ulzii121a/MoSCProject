package mn.mosc.project.domain.repository;

import static junit.framework.TestCase.assertNotNull;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mn.mosc.project.domain.entity.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * created by ubulgan on 9/29/17
 */
public class AwsIntegrationTest {

    private UserRepository userRepository;
    private static final String userId = "user1";

    @Before
    public void setup() {
        System.setProperty("aws.accessKeyId", "AKIAIEZ65ZTLJ5LDW77Q");
        System.setProperty("aws.secretKey", "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");
        System.setProperty("aws.region", "us-east-1");

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        userRepository = new UserRepository(dynamoDBMapper, amazonDynamoDB);
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
