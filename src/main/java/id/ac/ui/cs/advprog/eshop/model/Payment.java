package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.setStatus();
    }

    public void setStatus() {
        if (this.method.equals("VOUCHER")) {
            if (!this.paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException("Invalid Payment Data");
            }
            this.status = validateVoucherCode();
        } else if (this.method.equals("BANK")) {
            if (!this.paymentData.containsKey("bankName")|| !this.paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException("Invalid Payment Data");
            }
            this.status = validateBankPayment();
        }
        else{
            throw new IllegalArgumentException("Invalid Method");
        }
    }

    private String validateVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return PaymentStatus.REJECTED.getValue();
        }

        if (voucherCode.length() != 16) {
            return PaymentStatus.REJECTED.getValue();
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED.getValue();
        }

        int count = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                count++;
            }
        }
        if (count != 8) {
            return PaymentStatus.REJECTED.getValue();
        }


        return PaymentStatus.SUCCESS.getValue();
    }

    private String validateBankPayment() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName==null|| referenceCode ==null) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }
}








