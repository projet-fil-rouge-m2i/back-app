package com.tp.crm.service;

import com.tp.crm.model.dto.OrderDTO;
import com.tp.crm.model.entity.Client;
import com.tp.crm.model.entity.Order;
import com.tp.crm.model.dto.OrderPostDTO;
import com.tp.crm.model.dto.mapper.OrderPostMapper;
import com.tp.crm.repository.ClientRepository;
import com.tp.crm.repository.OrderRepository;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.model.dto.OrderPutDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
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
    @Autowired
    private OrderGeneralService orderGeneralService;

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
        public Order findOrder (Order order){
                return orderRepository.findById(order.getId()).get();
        }
    public Order putOrder(OrderPutDTO newdata, Integer id) {
        Optional<Order> op = findById(id);
        if (op.isPresent()) {
            Order orderBaseDonne = op.get();
            Order order = OrderMapper.DtoToEntity(newdata);
            Client client = clientService.findById(newdata.getIdClient());

            if (client != null)
                order.setClient(client);

            orderGeneralService.checkTaxe(order, orderBaseDonne);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean champsVide(OrderPutDTO orderPutDTO) {
        if (orderPutDTO.getIdClient() == null || orderPutDTO.getNbDays() == null || orderPutDTO.getState() == null || orderPutDTO.getDesignation() == null ||
                orderPutDTO.getTypePresta() == null || orderPutDTO.getUnitPrice() == null) {
            return true;
        }
        return false;
    }

    public boolean clientNonExistant(Integer id) {
        if (clientService.findById(id) == null) {
            return true;
        }
        return false;
    }

    public boolean champsAttendString(OrderPutDTO orderPutDTO) {
        if (!(orderPutDTO.getNbDays() instanceof Integer) || !(orderPutDTO.getUnitPrice() instanceof BigInteger)) {
            return true;
        }
        return false;
    }

    public boolean champsAttendInt(OrderPutDTO orderPutDTO) {
        if (!(orderPutDTO.getNbDays() instanceof Integer) || !(orderPutDTO.getUnitPrice() instanceof BigInteger)) {
            return true;
        }
        return false;
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
