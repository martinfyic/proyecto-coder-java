package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.repository.ClientRepository;
import com.sales.proyectocoder.response.ClientYearsOldResponse;
import com.sales.proyectocoder.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  /**
   *
   * @return Lista con todos los clientes en DB, si no hay clientes retorna un []
   */
  public List<ClientModel> getAllClients() {
    return clientRepository.findAll();
  }

  /**
   *
   * @param id identificador unico del cliente
   * @return devuelve cliente seleccionado, si no lo encuentra devuelve null
   */
  public ClientModel getClientById(Integer id) {
    return clientRepository.findById(id).orElse(null);
  }

  /**
   *
   * @param id identificador unico del cliente
   * @return devuelve cliente con el cálculo de cuantos años tiene, de lo contrario devuelve null
   */
  public ClientYearsOldResponse getClientYearsOld(Integer id) {
    Optional<ClientModel> clientById = clientRepository.findById(id);

    if (clientById.isPresent()) {
      ClientModel client = clientById.get();
      int yearsOld = calculateYearsOld(client.getBirthdate());
      return new ClientYearsOldResponse(client.getFirstName(), client.getLastName(), yearsOld);
    } else {
      return null;
    }
  }

  /**
   *
   * @param client objeto del tipo ClientModel
   * @return devuelve cliente creado
   */
  public ClientModel createClient(ClientModel client) {
    return clientRepository.save(client);
  }

  /**
   *
   * @param client Cliente que se debe actualizar
   * @param id Identificador unico del cliente que se va a actualizar
   * @return Retorna el cliente con la información actualizada, se pueden actualizar todos los atributos o solo el necesario
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

  /**
   *
   * @param id Idetificador unico del cliente que se quiere eliminar
   * @return Retorna un objeto con status de la operacion y mensaje
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
   *  Metodo para calcular los yearsOld del cliente, recibo por parametro fecha de nacimiento del cliente guardado en DB
   */
  private int calculateYearsOld(Date birthdate) {
    Instant instant = birthdate.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDate birthdateLocalDate = instant.atZone(zoneId).toLocalDate();
    LocalDate currentDate = LocalDate.now();
    return Period.between(birthdateLocalDate, currentDate).getYears();
  }
}
