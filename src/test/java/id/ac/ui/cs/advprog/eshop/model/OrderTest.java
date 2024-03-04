package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct(){
        this.products.clear();

        assertThrows(IllegalArgumentException.class, ()->{
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat");
        });
    }

    @Test
    void testCreateOrderDefault(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sampo Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudarajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testOrderCreateOrderSuccessfulStatus(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());

    }

    @Test
    void testOrderCreateOrderInvalidStatus(){
        assertThrows(IllegalArgumentException.class, ()->{
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat","MEOW");
        });
    }
    @Test
    void testSetStatusToCancelled(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L,"Safira Sudarajat");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }
    @Test
    void testSetStatusToInvalidStatus(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L,"Safira Sudarajat");
        assertThrows(IllegalArgumentException.class, ()->{
            order.setStatus("MEOW");
        });
    }

}