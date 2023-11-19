package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.repository.ClientRepository;
import com.sales.proyectocoder.response.ClientResponse;
import com.sales.proyectocoder.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
  public ClientModel getClientById(Integer id) {
    Optional<ClientModel> optionalClient = clientRepository.findById(id);
    return optionalClient.orElse(null);
  }

  /*
   * Listar cliente por su id, pero mostrar unicamente, firstName,LastName y yearOld
   */
  public ClientResponse getClientYearsOld(Integer id) {
    Optional<ClientModel> clientById = clientRepository.findById(id);

    if (clientById.isPresent()) {
      ClientModel client = clientById.get();
      int yearsOld = calculateYearsOld(client.getBirthdate());
      return new ClientResponse(client.getFirstName(), client.getLastName(), yearsOld);
    } else {
      return null;
    }
  }

  /*
   * Crear cliente
   */
  public ClientModel createClient(ClientModel client) {
    return clientRepository.save(client);
  }

  /*
   *  Actualizar cliente, buscar por id y actualizar
   */
  public ClientModel updateClient(ClientModel client, Integer id) {
    Optional<ClientModel> clientExist = clientRepository.findById(id);

    if (clientExist.isPresent()) {
      ClientModel clientUpdated = clientExist.get();

      if (client.getFirstName() != null) {
        clientUpdated.setFirstName(client.getFirstName());
      }

      if (client.getLastName() != null) {
        clientUpdated.setLastName(client.getLastName());
      }

      if (client.getBirthdate() != null) {
        clientUpdated.setBirthdate(client.getBirthdate());
      }

      if (client.getDocNumber() != null) {
        clientUpdated.setDocNumber(client.getDocNumber());
      }

      return clientRepository.save(clientUpdated);
    } else {
      return null;
    }
  }

  /*
   *  Eliminar cliente por su id y en el caso de no existir enviar mensaje
   */
  public DeleteResponse deleteClient(Integer id) {
    if (clientRepository.existsById(id)) {
      clientRepository.deleteById(id);
      return new DeleteResponse("success", "Cliente con ID " + id + " eliminado correctamente.");
    } else {
      return new DeleteResponse("error", "No se encontró un cliente con el ID proporcionado: " + id);
    }
  }

  /*
   *  Calcular los yearsOld del cliente, recibo por parametro fecvha de nacimiento del cliente guardado en DB
   */
  private int calculateYearsOld(Date birthdate) {
    Instant instant = birthdate.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDate birthdateLocalDate = instant.atZone(zoneId).toLocalDate();
    LocalDate currentDate = LocalDate.now();
    return Period.between(birthdateLocalDate, currentDate).getYears();
  }
}
