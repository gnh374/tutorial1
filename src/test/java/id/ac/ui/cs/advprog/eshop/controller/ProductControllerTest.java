package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productServiceMock;
    @Test
    public void testCreateProductPage() throws Exception {
        // Perform GET request to /create endpoint
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk()) // Verify HTTP status is OK
                .andExpect(view().name("CreateProduct")) // Verify view name is "createProduct"
                .andExpect(model().attributeExists("product")); // Verify "product" attribute exists in the model
    }
    @Test
    public void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("name", "Test Product")
                        .param("price", "100"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("list"));


    }
    @Test
    public void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productServiceMock.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attribute("products", products));
    }
    @Test
    public void EditPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        when(productServiceMock.getProductById("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    public void EditPagePost() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("name", "Test Product")
                        .param("price", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

    }

    @Test
    public void DeletePage() throws Exception {
        Product product= new Product();
        product.setProductId("1");
        mockMvc.perform(get("/product/delete/{id}",1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));

    }
}


