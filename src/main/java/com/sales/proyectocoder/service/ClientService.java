package com.sales.proyectocoder.service;

import com.sales.proyectocoder.DTO.ClientDTO;
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
import java.util.stream.Collectors;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  /**
   *
   * @return Lista con todos los clientes en DB
   */
  public List<ClientDTO> getAllClients() {
    List<ClientModel> clients = clientRepository.findAll();

    return clients.stream().map(client -> {
      ClientDTO clientDTO = new ClientDTO();
      clientDTO.setId(client.getId());
      clientDTO.setFirstName(client.getFirstName());
      clientDTO.setLastName(client.getLastName());
      clientDTO.setBirthdate(client.getBirthdate());
      clientDTO.setDocNumber(client.getDocNumber());
      return clientDTO;
    }).collect(Collectors.toList());
  }

  /**
   *
   * @param id identificador unico del cliente
   * @return devuelve cliente seleccionado
   */
  public ClientDTO getClientById(Integer id) {
    final Optional<ClientModel> optionalClient = clientRepository.findById(id);
    if (optionalClient.isEmpty()) {
      return null;
    }
    final ClientModel client = optionalClient.get();
    return new ClientDTO(client.getId(), client.getFirstName(), client.getLastName(), client.getBirthdate(), client.getDocNumber());
  }

  /**
   *
   * @param id identificador unico del cliente
   * @return devuelve cliente con el calculo de cuantos años tiene
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
  public ClientModel createClient(ClientDTO clientDTO) {
    ClientModel client = new ClientModel();
    client.setFirstName(clientDTO.getFirstName());
    client.setLastName(clientDTO.getLastName());
    client.setBirthdate(clientDTO.getBirthdate());
    client.setDocNumber(clientDTO.getDocNumber());
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
