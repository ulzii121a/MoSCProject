package mn.mosc.project.controller.inventory;

import mn.mosc.project.domain.entity.inventory.Product;
import mn.mosc.project.domain.service.inventory.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loya on 10/8/17
 */
@RestController
@RequestMapping("/rest/inventory")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{productId}/getProduct", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        try {
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, product == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProduct() {
        try {
            List<Product> products = productService.getProducts();
            return new ResponseEntity<>(products, products == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<ProductControllerResponse> addProduct(@RequestBody Product product) {
        if (product == null || StringUtils.isBlank(product.getId()) || StringUtils.isBlank(product.getProductName()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new ProductControllerResponse(productService.addProduct(product)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
