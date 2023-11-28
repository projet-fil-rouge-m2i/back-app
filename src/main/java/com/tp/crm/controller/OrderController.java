package com.tp.crm.controller;

import com.tp.crm.model.Order;
import com.tp.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("orders")
    public List<Order> getOrder(){
        return orderService.getOrders();
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer id) {

        if (orderService.deleteOrderById(id) == null) {
            return ResponseEntity.notFound().build();

        } else {
             // Utilisation de l'ID de l'objet récupéré
            return ResponseEntity.ok("Suppression ok");
        }
    }
}
