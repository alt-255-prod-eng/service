package ro.unibuc.hello.service;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import ro.unibuc.hello.data.product.ProductDTO;
import ro.unibuc.hello.data.product.ProductEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Tag("IT")
public class ProductServiceTestIT {
    @Autowired
    ProductService productService;
    public String productId;

    @Test
    public void test_insertValidProduct_savesProductInDb() {
        ProductDTO product = new ProductDTO("product", 10.0f, 30);
        try {
            productId = productService.insertProduct(product);
        } catch (Exception e) {
            Assertions.fail("Product was not saved in the database");
        }

        ProductEntity productFromDb = new ProductEntity();
        try {
            productFromDb = productService.getProductById(productId);
        } catch (Exception e) {
            Assertions.fail("Product was not found in the database");
        }

        Assertions.assertEquals(productId, productFromDb.id);
        Assertions.assertEquals("product", productFromDb.name);
        Assertions.assertEquals(true, productFromDb.inStock);
    }

    @Test
    public void test_insertInvalidProduct_throwsException() {
        ProductDTO product = new ProductDTO("invalid product", 10.0f, -20);

        Exception exception = assertThrows(Exception.class, () -> {
            productService.insertProduct(product);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @Test
    public void test_updateValidProduct_updatesProductInDb() {
        ProductDTO product = new ProductDTO("product", 10.0f, 30);
        try {
            productId = productService.insertProduct(product);
        } catch (Exception e) {
            Assertions.fail("Product was not saved in the database");
        }

        ProductDTO productToUpdate = new ProductDTO("product", 10.0f, 0);
        try {
            productService.updateProductById(productId, productToUpdate);
        } catch (Exception e) {
            Assertions.fail("Product was not updated");
        }

        ProductEntity productFromDb = new ProductEntity();
        try {
            productFromDb = productService.getProductById(productId);
        } catch (Exception e) {
            Assertions.fail("Product was not found in the database");
        }

        Assertions.assertEquals(productId, productFromDb.id);
        Assertions.assertEquals("product", productFromDb.name);
        Assertions.assertEquals(false, productFromDb.inStock);
    }

    @Test
    public void test_updateInvalidProduct_throwsException() {
        ProductDTO product = new ProductDTO("product", 10.0f, 30);
        try {
            productId = productService.insertProduct(product);
        } catch (Exception e) {
            Assertions.fail("Product was not saved in the database");
        }

        ProductDTO productToUpdate = new ProductDTO("invalid product", 10.0f, -20);

        Exception exception = assertThrows(Exception.class, () -> {
            productService.updateProductById(productId, productToUpdate);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @Test
    public void test_deleteValidProduct_deletesProductFromDb() {
        ProductDTO product = new ProductDTO("product", 10.0f, 30);
        try {
            productId = productService.insertProduct(product);
        } catch (Exception e) {
            Assertions.fail("Product was not saved in the database");
        }

        productService.deleteProductById(productId);

        Exception exception = assertThrows(Exception.class, () -> {
            productService.getProductById(productId);
        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @Test
    public void test_deleteByNullId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProductById(null);
        });
    }
}
