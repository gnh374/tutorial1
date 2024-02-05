package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import  static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testFindAllEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void  testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }


    @Test
    void testPositifEdit(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductId("id");
        product.setProductName("namaBaru");
        product.setProductQuantity(9);
        productRepository.edit(product);
        Product product1 = productRepository.getProductById("id");
        assertNotNull(product1);
        assertEquals(product1.getProductName(),"namaBaru");
        assertEquals(product1.getProductQuantity(),9);

//
    }

    @Test
    void testNegativeEdit(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductId("id");
        product.setProductName("namaBaru");
        product.setProductQuantity(9);
        productRepository.edit(product);
        Product product1 = productRepository.getProductById("random");
        assertNull(product1);

    }
    @Test
    void testpositifDelete(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product product1 = productRepository.delete(product.getProductId());
        assertNotNull(product1);
        assertEquals(product1.getProductName(), "Sampo Cap Bambang");
        assertEquals(product1.getProductQuantity(), 100);

    }

    @Test
    void testnegatifDelete(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Product product1 = productRepository.delete("random id");
        assertNull(product1);


    }


}
