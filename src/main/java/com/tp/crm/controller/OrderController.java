package com.tp.crm.controller;

import com.tp.crm.model.*;
import com.tp.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Integer id) {
        Optional<Order> optional = orderService.getOrder(id);
        if(optional.isPresent()) {
            Order order = optional.get();
            OrderDTO dto = OrderMapper.entityToDto(order);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderPostDTO> addOrder(@RequestBody OrderPostDTO newOrder) {
        OrderPostDTO orderPostDTO = orderService.addOrder(newOrder);
        return ResponseEntity.ok(orderPostDTO);


    @PutMapping("order/{id}")
    public ResponseEntity<?> putOrder(@RequestBody OrderPutDto newdata, @PathVariable Integer id) {

        if (orderService.notFound(newdata, id)) {
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");
        }

        Order order = orderService.putOrder(newdata, id);

        if (order != null) {
            return ResponseEntity.ok("Modification réussie");
        }else{
            return ResponseEntity.badRequest().build();

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
