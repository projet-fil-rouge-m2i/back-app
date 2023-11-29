package com.tp.crm.controller;

import com.tp.crm.model.dto.ClientGetDTO;
import com.tp.crm.model.dto.ClientPutDTO;
import com.tp.crm.model.dto.ClientPostDTO;
import com.tp.crm.model.dto.mapper.ClientPostMapper;
import com.tp.crm.model.entity.Client;
import com.tp.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> addClient(@RequestBody ClientPostDTO newClient) {
        Client client = clientService.addClient(ClientPostMapper.DtoToEntity(newClient));
        return ResponseEntity.ok(client);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> putClient(@RequestBody ClientPutDTO newdata, @PathVariable Integer id) {

        if (clientService.notFound(newdata, id)) {
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");
        }
        if (clientService.numberExiste(newdata)) {
            return ResponseEntity.badRequest().body("Un client existe deja avec ce numero de telephone");
        }
        if (clientService.emailExiste(newdata)) {
            return ResponseEntity.badRequest().body("Un client existe deja avec cette email");
        }
        Client client = clientService.putClient(newdata, id);

        if (client != null) {
            return ResponseEntity.ok(newdata);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientGetDTO>> findAllClients(){
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findClientById(@PathVariable Integer id) {
        Optional<Client> optional = clientService.getClientById(id);
        if (optional.isPresent()) {
            Client client = optional.get();
            return ResponseEntity.ok(client);
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Integer id) {

        if (clientService.deleteClientById(id) == null) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok("Suppression ok");
        }
    }
}
