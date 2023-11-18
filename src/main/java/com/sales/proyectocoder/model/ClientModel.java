package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "clients")
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
