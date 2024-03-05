package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(String id, Order order, String method, Map<String, String> paymentData) {
        if (paymentRepository.findById(id)==null){
            Payment payment = new Payment(id, order, method, paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Order order = payment.getOrder();
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            order.setStatus(OrderStatus.FAILED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid status payment");
        }
        return payment;

    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }
}
