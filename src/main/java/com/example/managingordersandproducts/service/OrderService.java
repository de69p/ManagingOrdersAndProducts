package com.example.managingordersandproducts.service;

import com.example.managingordersandproducts.model.Order;
import com.example.managingordersandproducts.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setCustomerName(order.getCustomerName());
            existingOrder.setCustomerAddress(order.getCustomerAddress());
            existingOrder.setCustomerEmail(order.getCustomerEmail());
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    public boolean deleteOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return true;
        } else {
            return false;
        }
    }
}

