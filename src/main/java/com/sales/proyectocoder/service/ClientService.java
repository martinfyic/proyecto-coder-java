package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  /*
   * Listar todos los clientes
   */
  public List<ClientModel> getAllClients() {
    return clientRepository.findAll();
  }

  /*
   * Listar clientes por su id, utilizo Optional para manejar si no existe el cliente.
   */
  public ClientModel getClientById(int id) {
    Optional<ClientModel> optionalClient = clientRepository.findById(id);
    return optionalClient.orElse(null);
  }

  /*
   * Crear cliente
   */
  public ClientModel createClient(ClientModel client) {
    return clientRepository.save(client);
  }

  /* TODO
   *  Actualizar cliente, buscar por id y actualizar
   */

  /* TODO
   *  Eliminar cliente por su id
   */
}
