package mn.mosc.project;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mn.mosc.project.domain.repository.authorization.UserRepository;
import mn.mosc.project.domain.repository.inventory.ProductRepository;
import mn.mosc.project.domain.repository.order.TransferRequestDetailRepository;
import mn.mosc.project.domain.repository.order.TransferRequestRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Properties;

/**
 * created by ubulgan on 9/30/17
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        try (InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties appProperties = new Properties();
            appProperties.load(inputStream);

            System.setProperty("aws.accessKeyId", appProperties.getProperty("accessKeyId"));
            System.setProperty("aws.secretKey", appProperties.getProperty("secretKey"));
            System.setProperty("aws.region", appProperties.getProperty("region"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
    }
}
