package com.tp.crm.service;

import com.tp.crm.model.Order;
import com.tp.crm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
@Autowired
    OrderRepository orderRepository;
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    public Order deleteOrderById(Integer id) {
        Optional<Order> find = orderRepository.findById(id);
        if (find.isPresent()) {
            Order order = find.get();
            orderRepository.deleteById(order.getId()); // Utilisation de l'ID de l'objet récupér
            return order;
        }
        return null;
    }
}
