package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "clients")
public class ClientModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(name = "first_name", nullable = false, length = 75)
  private String firstName;

  @NotBlank
  @Column(name = "last_name", nullable = false, length = 75)
  private String lastName;

  @NotNull
  @Column(nullable = false)
  private Date birthdate;

  @NotBlank
  @Column(name = "doc_number", nullable = false, length = 11)
  private String docNumber;

}
