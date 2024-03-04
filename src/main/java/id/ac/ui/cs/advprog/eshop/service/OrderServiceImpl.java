package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import java.util.List;

public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    Order createOrder(Order order){
        if (orderRepository.findById(order.getId())==null){
            orderRepository.save(order);
            return order;
        }
        return null;
    }
    Order updateStatus(String orderId, String status){
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(), status);
            orderRepository.save(newOrder);
            return  newOrder;
        }else{
            throw new NoSuchElementException();
        }
    }
    Order findById(String orderId){
        return orderRepository.findById(orderId);
    }
    List<Order> findAllByAuthor(String author){
        return orderRepository.findAllByAuthor(author);
    }

}