package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ro.unibuc.hello.data.transaction.TransactionDTO;
import ro.unibuc.hello.data.transaction.TransactionEntity;
import ro.unibuc.hello.data.transaction.TransactionEntry;

import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    TransactionDTO transaction = new TransactionDTO();
    TransactionEntry transactionEntry = new TransactionEntry();
    TransactionEntity transactionEntity = new TransactionEntity();
    @Test
    void test_transaction_entry()
    {
        transactionEntry.setProductId("productId");
        transactionEntry.setProductQuantity(100);

        Assertions.assertEquals("productId", transactionEntry.getProductId());
        Assertions.assertEquals(100, transactionEntry.getProductQuantity());
    }

    @Test
    void test_transaction_user()
    {
        transaction.setUserId("userId");
        Assertions.assertEquals("userId", transaction.getUserId());
    }

    @Test
    void test_transaction_products_list()
    {
        TransactionEntry first_entry = new TransactionEntry();
        first_entry.setProductId("1");
        first_entry.setProductQuantity(10);

        TransactionEntry second_entry = new TransactionEntry();
        second_entry.setProductId("2");
        second_entry.setProductQuantity(7);

        List<TransactionEntry> products = new ArrayList<>();
        products.add(first_entry);
        products.add(second_entry);

        transaction.setProductsList(products);
        Assertions.assertEquals(products, transaction.getProductsList());
    }

    @Test
    void test_transaction_entity_id()
    {
        transactionEntity.setId("1");
        Assertions.assertEquals("1", transactionEntity.getId());
    }

    @Test
    void test_transaction_entity_user()
    {
        transactionEntity.setUserId("userId");
        Assertions.assertEquals("userId", transactionEntity.getUserId());
    }

    @Test
    void test_transaction_entity_products_list()
    {
        TransactionEntry first_entry = new TransactionEntry();
        first_entry.setProductId("1");
        first_entry.setProductQuantity(10);

        TransactionEntry second_entry = new TransactionEntry();
        second_entry.setProductId("2");
        second_entry.setProductQuantity(7);

        List<TransactionEntry> products = new ArrayList<>();
        products.add(first_entry);
        products.add(second_entry);

        transactionEntity.setProductsList(products);
        Assertions.assertEquals(products, transactionEntity.getProductsList());
    }
}
