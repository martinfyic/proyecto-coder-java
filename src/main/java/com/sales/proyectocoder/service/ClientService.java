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

  public List<ClientModel> getAllClients() {
    return clientRepository.findAll();
  }

  public ClientModel getClientById(Integer id) {
    return clientRepository.findById(id).orElse(null);
  }

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

  public ClientModel createClient(ClientModel client) {
    if (client == null || client.getId() != null) {
      throw new IllegalArgumentException("Datos de cliente no válidos");
    }

    return clientRepository.save(client);
  }

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
