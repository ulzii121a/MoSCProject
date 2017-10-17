package mn.mosc.project.domain.service.inventory;

import mn.mosc.project.domain.entity.inventory.Product;
import mn.mosc.project.domain.repository.inventory.ProductRepository;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by ubulgan on 9/30/17
 */
@Component
public class ProductService {
    private final ProductRepository productRepository;

    private static final Log LOGGER = LogFactory.getLog(ProductService.class);

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String productId) {
        Validate.notBlank(productId, "Product ID should not be blank!");

        try {
            return productRepository.getProduct(productId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in ProductService.getProduct: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProducts() {
        try {
            return productRepository.getProducts();
        } catch (Exception e) {

            LOGGER.error(String.format("Exception in ProductService.getProduct: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public Product addProduct(Product product) {
        Validate.notNull(product, "Product should not be Null!");
        Validate.notBlank(product.getId(), "Product ID should not be Null!");
        Validate.notBlank(product.getProductName(), "Product Name should not be Null!");

        try {
            productRepository.putProduct(product);
            return product;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in ProductService.getProduct: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

}
