package com.tp.crm.service;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.model.StatClient;
import com.tp.crm.model.dto.ClientPutDto;
import com.tp.crm.model.dto.OrderPutDto;
import com.tp.crm.model.dto.mapper.ClientMapper;
import com.tp.crm.model.dto.mapper.OrderMapper;
import com.tp.crm.repository.ClientRepository;
import com.tp.crm.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;

    // Modification d'une prestation
    public Client putClient(ClientPutDto newdata, Integer id) {

        if (getClient(id).isPresent()) {
            StatClient etatClientDto = newdata.getState();
            Client client = ClientMapper.DtoToEntity(newdata);
            // Gérer l'état du client du dto (STRING) en INTEGER
            gererEtatOrder(etatClientDto, client);

            return clientRepository.save(client);
        }
        return null;
    }
    public Client findById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    public boolean notFound(ClientPutDto newdata, Integer id) {
        if (getClient(newdata.getId()).isPresent()) {
            Client newClient = getClient(newdata.getId()).get();
            if (!newClient.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Client> getClient(Integer id) {
        return clientRepository.findById(id);
    }

    public void gererEtatOrder(StatClient etat, Client client) {
        switch (etat) {
            case ACTIVE:
                client.setState(0);
                break;
            case INACTIVE:
                client.setState(1);
                break;
            default:
                break;
        }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }
    public Client deleteClientById(Integer id) {
        Optional<Client> find = clientRepository.findById(id);
        if (find.isPresent()) {
            Client client = find.get();
            clientRepository.deleteById(client.getId()); // Utilisation de l'ID de l'objet récupér
            return client;
        }
        return null;

    }
}
