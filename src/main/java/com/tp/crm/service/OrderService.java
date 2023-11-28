package com.tp.crm.service;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.model.dto.OrderPutDto;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientService clientService;

    // Modification d'une prestation
    public Order putOrder(OrderPutDto newdata, Integer id) {

        if (findById(id).isPresent()) {
            String etatOrderDto = newdata.getState();
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

    public void gererEtatOrder(String etat, Order order) {
        switch (etat) {
            case "CANCELED":
                order.setState(0);
                break;
            case "OPTION":
                order.setState(1);
                break;
            case "CONFIRMED":
                order.setState(2);
                break;
            default:
                break;
        }
    }
}
