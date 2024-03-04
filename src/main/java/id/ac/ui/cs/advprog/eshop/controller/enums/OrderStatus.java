package id.ac.ui.cs.advprog.eshop.controller.enums;

public enum OrderStatus {
    WAITINGPAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");
    private final String value;

    private OrderStatus(String value){
        this.value = value;
    }
    public static  boolean contains(String param){
        for (OrderStatus orderStatus : OrderStatus.values()){
            if (orderStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}