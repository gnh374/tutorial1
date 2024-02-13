package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import  static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest{
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;


    @BeforeEach
    void setUp(){

    }
    @Test
    public void testCreateProduct() {
        // Create a sample product
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.create(any(Product.class))).thenReturn(product);

        productService.create(product);

        verify(productRepository,times(1)).create(product);
    }
    @Test
    public void testFindAll() {
        // Create sample products
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");

        // Create sample iterator with products
        Iterator<Product> productIterator = Arrays.asList(product1, product2).iterator();

        // Setup behavior: When productRepository.findAll() is called,
        // then return the sample iterator with products
        when(productRepository.findAll()).thenReturn(productIterator);

        // Call the method under test
        List<Product> allProducts = productService.findAll();

        // Verify that the returned list contains the expected products
        assertEquals(2, allProducts.size());
        assertEquals(product1, allProducts.get(0));
        assertEquals(product2, allProducts.get(1));
    }
    @Test
    public void getProductByIdTest(){
        Product product = new Product();
        product.setProductId("1");

        when(productRepository.getProductById("1")).thenReturn(product);

        Product getProduct = productService.getProductById("1");

        assertEquals(getProduct,product);
    }

    @Test
    public void editTest(){
        Product product = new Product();
        productService.edit(product);
        verify(productRepository,times(1)).edit(product);

    }

    @Test
    public void deleteProductTest(){
        String id = "1";
        productService.deleteProduct(id);

        verify(productRepository,times(1)).delete(id);
    }

}

