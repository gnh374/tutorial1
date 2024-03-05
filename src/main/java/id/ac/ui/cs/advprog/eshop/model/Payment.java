package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    @Setter
    String status;
    Order order;
    Map<String,String> paymentData;

    public Payment(String id,Order order, String method,  Map<String,String> paymentData ){

    }



}
