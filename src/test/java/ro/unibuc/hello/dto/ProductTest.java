package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ro.unibuc.hello.data.product.ProductDTO;
import ro.unibuc.hello.data.product.ProductEntity;

public class ProductTest {
    ProductDTO product = new ProductDTO("lapte", 10.0f, 5);
    ProductEntity productEntity = new ProductEntity("1",
            "paine",5.0f, true, 10);

    @Test
    void test_product_name()
    {
        product.setName(product.name + " de soia");
        Assertions.assertEquals("lapte de soia", product.getName());
    }

    @Test
    void test_product_price()
    {
        product.setPrice(product.price + 1);
        Assertions.assertEquals(11.0f, product.getPrice());
    }

    @Test
    void test_product_stock_size()
    {
        product.setStockSize(product.stockSize - 1);
        Assertions.assertEquals(4, product.getStockSize());
    }

    @Test
    void test_product_entity_id()
    {
        productEntity.setId("2");
        Assertions.assertEquals("2", productEntity.getId());
    }

    @Test
    void test_product_entity_name()
    {
        productEntity.setName(productEntity.name + " cu seminte");
        Assertions.assertEquals("paine cu seminte", productEntity.getName());
    }

    @Test
    void test_product_entity_price()
    {
        productEntity.setPrice(productEntity.price + 1);
        Assertions.assertEquals(6.0f, productEntity.getPrice());
    }

    @Test
    void test_product_entity_in_stock()
    {
        productEntity.setInStock(false);
        Assertions.assertEquals(false, productEntity.isInStock());
    }

    @Test
    void test_product_entity_stock_size()
    {
        productEntity.setStockSize(productEntity.stockSize - 1);
        Assertions.assertEquals(9, productEntity.getStockSize());
    }
}
