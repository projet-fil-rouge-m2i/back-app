package com.tp.crm.service;

import com.tp.crm.model.StateClient;
import com.tp.crm.model.dto.OrderPutDTO;
import com.tp.crm.model.entity.Client;
import com.tp.crm.model.dto.mapper.ClientMapper;
import com.tp.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tp.crm.model.dto.ClientPutDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }


    public Client putClient (ClientPutDTO newdata, Integer id) {

        if (getClient(id).isPresent()) {
            Client client = ClientMapper.DtoToEntity(newdata);
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

    public boolean numberExiste(ClientPutDTO clientPutDTO) {
        if(clientRepository.findByPhoneNumber(clientPutDTO.getPhoneNumber()) != null){
            return true;
        }
        return false;
    }
    public boolean emailExiste(ClientPutDTO clientPutDTO) {
        if(clientRepository.findByEmail(clientPutDTO.getEmail()) != null){
            return true;
        }
        return false;
    }
    public boolean notFound(ClientPutDTO newdata, Integer id) {
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

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public Client deleteClientById(Integer id) {
        Optional<Client> find = clientRepository.findById(id);
        if (find.isPresent()) {
            Client client = find.get();
            clientRepository.deleteById(client.getId());
            return client;
        }
        return null;

    }
}
