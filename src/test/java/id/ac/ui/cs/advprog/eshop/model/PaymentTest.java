package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.model.Payment;

/** id: String
 method: String to save a sub-feature name.
 status: String
 paymentData: Map<String, String> to save payment sub-feature data.
 order
 **/

/**
 test kalo berhasil bikin Payment
 status gagal kalo
 pake voucher --> voucher ga valid
 pake bank salah satu dari bank name / reff codenya kosong
 */

/**
 * id pake uuid
 * input user harusnya cuma method sama payment datanya
 */
/**
 * bikin kelas payment
 * yg ada id, method, status, paymentdata, order
 *
 * cek methodnya cuma bolej 2 VOUCHEER atau bank
 * setstatus sama set
 * **/
public class PaymentTest {
    private List<Product> products;
    private Order order;

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

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat");

    }
    @Test
    void testCreatePaymentEmptyOrder(){
        Map<String, String> paymentDataVoucher = new HashMap<>();
        assertThrows(IllegalArgumentException.class, ()->{
           Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",null,
                   "VOUCHER", paymentDataVoucher);
        });
    }
    @Test
    void testCreatePaymentSuccessfullWithOrder(){
        Map<String, String> paymentData = new  HashMap<>();
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertNotNull(payment.getOrder());

    }
    @Test
    void testCreatePaymentVoucherMethod(){
        Map<String, String> paymentData = new  HashMap<>();
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertEquals("VOUCHER", payment.getMethod());
    }
    @Test
    void testCreatePaymentBankMethod(){
        Map<String, String> paymentData = new  HashMap<>();
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"BANK", paymentData);
        assertEquals("BANK", payment.getMethod());
    }
    @Test
    void TestCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new  HashMap<>();
        assertThrows(IllegalArgumentException.class, ()->{
            Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"MEOW", paymentData);
        });
    }

    @Test
    void TestSuccessValidVoucherData(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void TestInvalidVoucherNot16Chars(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678999");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void TestInvalidVoucherNotStartedWithESHOP(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("voucherCode", "EAAAA1234ABC5678999");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void TestInvalidVoucherCodeNot8Num(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("voucherCode", "EAAAAABCDABC5678999");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void InvalidBankPaymentDataNoBankName(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("bankName", "ABC");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"BANK", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void InvalidBankPaymentDataNoCode(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("referenceCode", "123");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"BANK", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void ValidBankPaymentData(){
        Map<String, String> paymentData = new  HashMap<>();
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "123");
        Payment payment = new Payment("0176dc9d-3381-4b14-8705-8f66a8b86acf",order,"BANK", paymentData);
        assertEquals("SUCCESS", payment.getStatus());

    }







}
