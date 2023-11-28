package com.tp.crm.service;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.model.StatClient;
import com.tp.crm.model.StatOrder;
import com.tp.crm.model.OrderPostDTO;
import com.tp.crm.model.OrderPostMapper;
import com.tp.crm.repository.ClientRepository;
import com.tp.crm.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.model.dto.OrderPutDto;
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

    // Modification d'une prestation
    public Order putOrder(OrderPutDto newdata, Integer id) {

        if (findById(id).isPresent()) {
            StatOrder etatOrderDto = newdata.getState();
            Order order = OrderMapper.DtoToEntity(newdata);
            // Gérer l'état de l'order du dto (STRING) en INTEGER
            gererEtatOrder(etatOrderDto, order);
            // Envoyer l'objet client en fonction de l'attribut idClient récuperer dans OrderDto
            Client client = clientService.findById(newdata.getIdClient());
            if (client != null)
                order.setClient(client);

            return orderRepository.save(order);
        }
        return null;
    }

    public boolean notFound(OrderPutDto newdata, Integer id) {
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

    public void gererEtatOrder(StatOrder etat, Order order) {
        switch (etat) {
            case CANCELED:
                order.setState(0);
                break;
            case OPTION:
                order.setState(1);
                break;
            case CONFIRMED:
                order.setState(2);
                break;
            default:
                break;
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
