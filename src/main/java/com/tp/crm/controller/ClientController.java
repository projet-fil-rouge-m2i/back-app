package com.tp.crm.controller;

import com.tp.crm.model.Client;
import com.tp.crm.model.Order;
import com.tp.crm.model.dto.ClientPutDto;
import com.tp.crm.model.dto.OrderPutDto;
import com.tp.crm.service.ClientService;
import com.tp.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PutMapping("client/{id}")
    public ResponseEntity<?> putClient(@RequestBody ClientPutDto newdata, @PathVariable Integer id) {

        if (clientService.notFound(newdata, id)) {
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");
        }

        Client client = clientService.putClient(newdata, id);

        if (client != null) {
            return ResponseEntity.ok("Modification réussie");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
