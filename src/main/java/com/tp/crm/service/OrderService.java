package com.tp.crm.service;

import com.tp.crm.model.StateOrder;
import com.tp.crm.model.entity.Client;
import com.tp.crm.model.entity.Order;
import com.tp.crm.model.dto.OrderPostDTO;
import com.tp.crm.model.dto.mapper.OrderPostMapper;
import com.tp.crm.repository.ClientRepository;
import com.tp.crm.repository.OrderRepository;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.model.dto.OrderPutDTO;
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

    @Autowired
    private ClientService clientService;

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

    public Order putOrder(OrderPutDTO newdata, Integer id) {

        if (findById(id).isPresent()) {
            Order order = OrderMapper.DtoToEntity(newdata);
            Client client = clientService.findById(newdata.getIdClient());
            if (client != null)
                order.setClient(client);
            System.out.println(order);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean notFound(OrderPutDTO newdata, Integer id) {
        if (findById(newdata.getId()).isPresent()) {
            Order newOrder = findById(newdata.getId()).get();
            if (!newOrder.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }


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
