package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "invoice")
public class InvoiceModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientModel client;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private double total;

  @OneToMany(mappedBy = "invoice")
  private List<InvoiceDetailModel> invoiceDetails;
}
