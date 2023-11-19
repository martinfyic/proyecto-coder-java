package com.sales.proyectocoder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Creo la clase DeleteResponse para dar respuesta cuando elimino un cliente
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponse {

  private String status;
  private String message;

}
