package com.tp.crm.controller;

import com.tp.crm.model.*;
import com.tp.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tp.crm.service.OrderService;

import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> addClient(@RequestBody ClientPostDTO newClient) {
        Client client = clientService.addClient(ClientPostMapper.DtoToEntity(newClient));
        return ResponseEntity.ok(client);


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
            return ResponseEntity.ok("Suppression ok");
        }

    }
}
