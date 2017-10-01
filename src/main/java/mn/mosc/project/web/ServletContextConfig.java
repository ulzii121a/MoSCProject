package mn.mosc.project.web;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ServletContextConfig extends WebMvcConfigurationSupport {

    //-----------Default max error retry is - 3 times:
    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.defaultClient();
    }

    //-----------Default max error retry is - 3 times:
    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.defaultClient();
    }

    //-----------Default max error retry is - 10 times:
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }

    //-----------Default max error retry is - 10 times:
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
