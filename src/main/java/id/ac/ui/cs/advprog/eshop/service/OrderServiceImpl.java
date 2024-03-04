package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    Order createOrder(Order order){return null;}
    Order updateStatus(String orderId, String status){return null;}
    Order findById(String orderId){return null;}
    List<Order> findAllByAuthor(String id){return null;}
}