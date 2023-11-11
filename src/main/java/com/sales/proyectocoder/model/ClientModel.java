package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "clients")
public class ClientModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name", nullable = false, length = 75)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 75)
  private String lastName;

  @Column(name = "doc_number", nullable = false, length = 11)
  private String docNumber;

  @OneToMany(mappedBy = "client")
  private List<InvoiceModel> invoices;
}
