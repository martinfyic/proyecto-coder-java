package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.response.ClientYearsOldResponse;
import com.sales.proyectocoder.response.DeleteResponse;
import com.sales.proyectocoder.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   *   Listar un cliente por su id si no lo encuentra devuelve un objeto con el mensaje y status
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getClientById(@PathVariable Integer id) {
    ClientModel clientById = clientService.getClientById(id);

    if (clientById != null) {
      return ResponseEntity.ok(clientById);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró un cliente con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

  }

  /*
   *   Listar un cliente por su id y mostrar en JSON {firstName, lastname, yearsOld}
   */
  @GetMapping("/{id}/years-old")
  public ResponseEntity<?> getClientYearsOld(@PathVariable Integer id) {
    ClientYearsOldResponse clientResponse = clientService.getClientYearsOld(id);

    if (clientResponse != null) {
      return ResponseEntity.ok(clientResponse);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró un cliente con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
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
  public ResponseEntity<ClientModel> updateClient(@RequestBody ClientModel client, @PathVariable Integer id) {
    ClientModel updatedClient = clientService.updateClient(client, id);
    return (updatedClient != null) ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
  }

  /*
   *  Eliminar  @DeleteMapping("/{id}") eliminar cliente por su id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteClient(@PathVariable Integer id) {
    DeleteResponse  response = clientService.deleteClient(id);
    return ResponseEntity.ok(response);
  }
}
