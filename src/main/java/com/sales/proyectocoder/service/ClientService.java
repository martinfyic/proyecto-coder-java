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

  /*
   *  Actualizar cliente, buscar por id y actualizar
   *  Luego de hacer el findById hago un map para setear los datos y en el caso que no exista lo termino con un orElse
   */
  public ClientModel updateClient(ClientModel client, int id) {
    return clientRepository.findById(id)
        .map(existingClient -> {
          existingClient.setFirstName(client.getFirstName());
          existingClient.setLastName(client.getLastName());
          existingClient.setDocNumber(client.getDocNumber());
          return clientRepository.save(existingClient);
        })
        .orElse(null);
  }

  /* TODO
   *  Eliminar cliente por su id
   */
}
