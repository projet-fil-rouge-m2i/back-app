package com.tp.crm.controller;

import com.tp.crm.model.Order;
import com.tp.crm.model.dto.OrderPutDto;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

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
        }
    }
}
