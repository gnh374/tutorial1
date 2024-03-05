package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    @Setter
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
                throw new IllegalArgumentException("Invalid");
            }
            this.status = validateVoucherCode();
        } else if (this.method.equals("BANK")) {
            if (!this.paymentData.containsKey("bankName")|| !this.paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException("Invalid");
            }
            this.status = validateBankPayment();
        }
    }

    private String validateVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int count = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                count++;
            }
        }
        if (count != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private String validateBankPayment() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName==null|| referenceCode ==null) {
            return "REJECTED";
        }
        return "SUCCESS";
    }
}








