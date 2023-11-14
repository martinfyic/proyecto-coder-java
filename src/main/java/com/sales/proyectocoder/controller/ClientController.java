package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<List<ClientModel>> getAllClients() {
    List<ClientModel> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientModel> getClientById(int id) {
    ClientModel clientById = clientService.getClientById(id);
    if (clientById != null) {
      return ResponseEntity.ok(clientById);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client) {
    ClientModel createdClient = clientService.createClient(client);
    return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
  }

  /* TODO
   *   Actualizar @PutMapping("/{id}")
   */

  /* TODO
   *  Actualizar @DeleteMapping("/{id}")
   */
}
