package com.tp.crm.controller;

import com.tp.crm.model.dto.OrderPutDTO;
import com.tp.crm.model.dto.OrderDTO;
import com.tp.crm.model.dto.OrderPostDTO;
import com.tp.crm.model.dto.mapper.ClientMapper;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.model.entity.Order;
import com.tp.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Integer id) {
        Optional<Order> optional = orderService.getOrder(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            OrderDTO dto = OrderMapper.entityToDto(order);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addOrder(@RequestBody OrderPostDTO newOrder) {
        if (orderService.champsVidePost(newOrder)) {
            return ResponseEntity.badRequest().body("Un des champs n'a pas été rempli");
        }

        if(orderService.clientNonExistant(newOrder.getIdClient())){
            return ResponseEntity.badRequest().body("Le client que vous voulez assigner à la prestation n'existe pas");
        }

        OrderPostDTO orderPostDTO = orderService.addOrder(newOrder);
        return ResponseEntity.ok(orderPostDTO);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> putOrder(@RequestBody OrderPutDTO newdata, @PathVariable Integer id) {

        if (orderService.notFound(newdata, id)) {
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");
        }

        if (orderService.champsVide(newdata)) {
            return ResponseEntity.badRequest().body("Un des champs n'a pas été rempli");
        }

        if(orderService.clientNonExistant(newdata.getIdClient())){
            return ResponseEntity.badRequest().body("Le client que vous voulez assigner à la prestation n'existe pas");
        }

        Order order = orderService.putOrder(newdata, id);

        if (order != null) {
            OrderDTO orderDTO = OrderMapper.entityToDto(order);
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Order> getOrder() {
        return orderService.getOrders();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer id) {

        if (orderService.deleteOrderById(id) == null) {
            return ResponseEntity.notFound().build();

        } else {
            // Utilisation de l'ID de l'objet récupéré
            return ResponseEntity.ok("Suppression ok");
        }
    }
}
