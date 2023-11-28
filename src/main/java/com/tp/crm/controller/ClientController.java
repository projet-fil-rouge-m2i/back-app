package com.tp.crm.controller;

import com.tp.crm.model.Client;
import com.tp.crm.model.ClientPostDTO;
import com.tp.crm.model.ClientPostMapper;
import com.tp.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> addClient(@RequestBody ClientPostDTO newClient) {
        Client client = clientService.addClient(ClientPostMapper.DtoToEntity(newClient));
        return ResponseEntity.ok(client);
    }
}
