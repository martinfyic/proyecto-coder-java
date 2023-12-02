package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.DTO.ClientDTO;
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
@Tag(name="Controlador Client", description = "Request relacionadas con los usuarios")
public class ClientController {
  @Autowired
  private ClientService clientService;

  /**
   * @return Lista de todos los clientes
   */
  @Operation(summary = "Listar todos los usuarios", description = "Listar todos los usuarios en formato string")
  @GetMapping
  public ResponseEntity<List<ClientDTO>> getAllClients() {
    List<ClientDTO> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  /**
   *
   * @param id numero identificador del cliente
   * @return Retorna cliente seleccionado por su id
   */
  @Operation(summary = "Muestra uns usuario", description = "Muestra un usuario por su id")
  @GetMapping("/{id}")
  public ResponseEntity<?> getClientById(@PathVariable Integer id) {
    ClientDTO clientById = clientService.getClientById(id);

    if (clientById != null) {
      return ResponseEntity.ok(clientById);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró un cliente con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

  }

  /**
   *
   * @param id numero identificador del cliente
   * @return Retorna la edad de un cliente seleccionado
   */
  @Operation(summary = "Muestra un usuario para obtener su edad", description = "Muestra un usuario por su id y obtiene la edad")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Request Exitoso"),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
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

  /**
   *
   * @param clientDTO información del cliente a crear, ejemplo: { "firstName": "Bruce ", "lastName": "Wayne ", "birthdate": "2005-10-04", "docNumber": "25643456" }
   * @return Crea cliente creado
   */
  @Operation(summary = "Crear un usuario", description = "Crea un usuario y lo guarda en DB")
  @PostMapping
  public ResponseEntity<ClientModel> createClient(@RequestBody ClientDTO clientDTO) {
    ClientModel createdClient = clientService.createClient(clientDTO);
    return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
  }

  /**
   * @param client información del cliente que vamos a actualizar, ejemplo: { "firstName": "Bruce ", "lastName": "Wayne ", "birthdate": "2005-10-04", "docNumber": "25643456" }
   * @param id     identificador unico del cliente que vamos a actualizar
   * @return Actualiza un cliente seleccionado
   */
  @PutMapping("/{id}")
  public ResponseEntity<ClientModel> updateClient(@RequestBody ClientModel client, @PathVariable Integer id) {
    ClientModel updatedClient = clientService.updateClient(client, id);
    return (updatedClient != null) ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
  }

  /**
   *
   * @param id numero identificador del cliente
   * @return Elimina un cliente seleccionado
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteClient(@PathVariable Integer id) {
    DeleteResponse  response = clientService.deleteClient(id);
    return ResponseEntity.ok(response);
  }
}
