package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    List<Payment> payments = new ArrayList<>();
    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        List<Product> products = new ArrayList<>();
        List<Order> orders;

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee607ecbf1e", products,
                1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",orders.get(1),"VOUCHER", paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "ABC");
        paymentData2.put("referenceCode", "123");
        Payment payment2 = new Payment("563456ef-e9d3-490c-8309-043c926b80e4",orders.get(2),"BANK", paymentData2);
        payments.add(payment2);

    }

    @Test
    void testSveCreate(){
        Payment payment1 = payments.get(1);
        Payment result = paymentRepository.save(payment1);
        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate(){
        Payment payment1 = payments.get(1);
        paymentRepository.save(payment1);

        Payment newPayment = new Payment(payment1.getId(), payment1.getOrder(), payment1.getMethod(), payment1.getPaymentData());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getOrder(), findResult.getOrder());
        assertEquals(payment1.getStatus(), findResult.getStatus());
        assertEquals(payment1.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }



    @Test
    void testFindByIdIfIdNotFound(){

        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }


    @Test
    void testFindAllByAuthorIfAuthorCorrect(){
        for (Payment payment: payments) {
            paymentRepository.save(payment);
        }
        List<Payment> paymentList = paymentRepository.getAllPayment();
        assertEquals(2, paymentList.size());
    }


}