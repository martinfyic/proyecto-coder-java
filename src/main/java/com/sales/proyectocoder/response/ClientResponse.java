package com.sales.proyectocoder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* Creo la clase ClientResponse para usar en la respuesta poder crear y devolver firstName, lastName, yearsOld en ClientService y ClientController la edad del cliente en el endpoint api/v1/clients/{id}/years-old sin tener que guardar en DB
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

  private String firstName;
  private String lastName;
  private int yearsOld;
}
