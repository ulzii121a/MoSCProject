package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

import java.util.Map;

/**
 * created by loya on 9/29/17
 */
@DynamoDBTable(tableName = "PRODUCT")
public class Product {
    private String id;
    private String productName;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private String picturePath;
    private Category category;
    private Map<String, String> attrs;
    private Long version;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @DynamoDBAttribute(attributeName = "weight")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @DynamoDBAttribute(attributeName = "height")
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @DynamoDBAttribute(attributeName = "width")
    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @DynamoDBAttribute(attributeName = "length")
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @DynamoDBAttribute(attributeName = "picturePath")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    @DynamoDBAttribute(attributeName = "attrs")
    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", picturePath='" + picturePath + '\'' +
                ", category=" + category +
                ", attrs=" + attrs +
                ", version=" + version +
                '}';
    }
}
