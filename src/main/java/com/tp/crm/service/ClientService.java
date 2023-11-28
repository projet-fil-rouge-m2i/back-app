package com.tp.crm.service;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
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
