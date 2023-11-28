package com.tp.crm.service;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.model.OrderPostDTO;
import com.tp.crm.model.OrderPostMapper;
import com.tp.crm.repository.ClientRepository;
import com.tp.crm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Order> getOrder(Integer id) {
        return orderRepository.findById(id);
    }

    public OrderPostDTO addOrder(OrderPostDTO orderPostDTO) {
        Order order = OrderPostMapper.DtoToEntity(orderPostDTO);
        Client client = clientRepository.findById(orderPostDTO.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client n'existe pas"));
        order.setClient(client);
        orderRepository.save(order);

        return orderPostDTO;
    }
}
