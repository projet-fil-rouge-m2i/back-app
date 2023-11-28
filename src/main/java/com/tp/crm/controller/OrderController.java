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
    public ResponseEntity<Order> addOrder(@RequestBody OrderPostDTO newOrder) {
        Order order = orderService.addOrder(OrderPostMapper.DtoToEntity(newOrder));
        return ResponseEntity.ok(order);
    }
}
