package mn.mosc.project.controller.inventory;

import mn.mosc.project.domain.entity.inventory.Product;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/8/17
 */

public class ProductControllerResponse {
    private Product product;
    private String message;
    private boolean result;

    public ProductControllerResponse(Product product) {
        this.product = product;
    }

    public ProductControllerResponse(String messsage) {
        this.message = messsage;
    }

    public ProductControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public Product getProduct() {
        return product;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
