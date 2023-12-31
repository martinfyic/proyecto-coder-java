package com.sales.proyectocoder.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de clientes")
public class ClientModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 75)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 75)
  private String lastName;

  @Column(nullable = false)
  private Date birthdate;

  @Column(name = "doc_number", nullable = false, length = 11)
  private String docNumber;

}
