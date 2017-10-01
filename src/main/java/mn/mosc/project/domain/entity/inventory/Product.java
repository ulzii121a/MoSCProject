package mn.mosc.project.domain.entity.inventory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

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
    private String categoryId;
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

    @DynamoDBAttribute(attributeName = "categoryId")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
                ", category=" + categoryId +
                ", attrs=" + attrs +
                ", version=" + version +
                '}';
    }
}
