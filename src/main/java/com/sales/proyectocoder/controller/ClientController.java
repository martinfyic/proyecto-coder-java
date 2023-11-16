package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;

  /*
   *   Listar todos los clientes
   */
  @GetMapping
  public ResponseEntity<List<ClientModel>> getAllClients() {
    List<ClientModel> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  /*
   *   Listar un cliente por su id
   */
  @GetMapping("/{id}")
  public ResponseEntity<ClientModel> getClientById(int id) {
    ClientModel clientById = clientService.getClientById(id);
    return (clientById != null) ? ResponseEntity.ok(clientById) : ResponseEntity.notFound().build();
  }

  /*
   *   Crear cliente recibiendo por el body la data
   */
  @PostMapping
  public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client) {
    ClientModel createdClient = clientService.createClient(client);
    return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
  }

  /*
   *   Actualizar cliente recibiendo por el body la data y el id
   */
  @PutMapping("/{id}")
  public ResponseEntity<ClientModel> updateClient(@RequestBody ClientModel client, int id) {
    ClientModel updatedClient = clientService.updateClient(client, id);
    return (updatedClient != null) ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
  }

  /* TODO
   *  Eliminar  @DeleteMapping("/{id}") eliminar cliente por su id
   */
}
