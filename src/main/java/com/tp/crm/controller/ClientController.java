package com.tp.crm.controller;

import com.tp.crm.model.Client;
import com.tp.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("clients/{id}")
    public ResponseEntity<?> fiindClientById(@PathVariable Integer id) {
        Optional<Client> optional = clientService.getClientById(id);
        if (optional.isPresent()) {
            Client client = optional.get();
            return ResponseEntity.ok(client);
        } else
            return ResponseEntity.notFound().build();
    }
    @DeleteMapping("clients/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Integer id) {

        if (clientService.deleteClientById(id) == null) {
            return ResponseEntity.notFound().build();

        } else {
            // Utilisation de l'ID de l'objet récupéré
            return ResponseEntity.ok("Suppression ok");
        }
    }
}
