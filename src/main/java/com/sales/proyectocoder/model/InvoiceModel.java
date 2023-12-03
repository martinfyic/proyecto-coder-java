package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "invoice")
public class InvoiceModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", nullable = false)
  private ClientModel client;

  @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
  private Set<InvoiceDetailModel> invoiceDetails;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(nullable = false)
  private double total;
}
