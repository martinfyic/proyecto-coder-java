package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.response.ClientYearsOldResponse;
import com.sales.proyectocoder.response.DeleteResponse;
import com.sales.proyectocoder.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/clients")
@Tag(name = "Client Controller", description = "Request relacionadas con los usuarios")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @Operation(summary = "Lista todos los usuarios", description = "Lista todos los usuarios")
  @GetMapping
  public ResponseEntity<List<ClientModel>> getAllClients() {
    List<ClientModel> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @Operation(summary = "Muestra un usuario", description = "Muestra un usuario por su id")
  @GetMapping("/{id}")
  public ResponseEntity<?> getClientById(@PathVariable Integer id) {
    ClientModel client = clientService.getClientById(id);

    if (client != null) {
      return ResponseEntity.ok(client);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró un cliente con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

  }

  @Operation(summary = "Muestra un usuario para con el calculo de su edad", description = "Muestra un usuario por su id y obtiene la edad")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Request Exitoso"), @ApiResponse(responseCode = "400", description = "Bad Request")})
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

  @Operation(summary = "Crear un usuario", description = "Crea un usuario y lo guarda en DB")
  @PostMapping
  public ResponseEntity<?> createClient(@RequestBody ClientModel client) {

    if (client == null || client.getId() != null || client.getFirstName() == null || client.getLastName() == null || client.getBirthdate() == null || client.getDocNumber() == null) {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "Datos de cliente no válidos");
      return ResponseEntity.badRequest().body(response);
    }

    ClientModel createdClient = clientService.createClient(client);
    return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
  }

  @Operation(summary = "Actualiza usuario", description = "Actualiza un usuario buscado por su id")
  @PutMapping("/{id}")
  public ResponseEntity<?> updateClient(@RequestBody ClientModel client, @PathVariable Integer id) {
    ClientModel existingClient = clientService.getClientById(id);

    if (existingClient == null) {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "Cliente no encontrado con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    ClientModel updatedClient = clientService.updateClient(client, id);
    return (updatedClient != null) ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Elimina un usuario por su id", description = "Elimina un usuario de la DB por su id")
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteClient(@PathVariable Integer id) {
    DeleteResponse response = clientService.deleteClient(id);
    return ResponseEntity.ok(response);
  }
}
