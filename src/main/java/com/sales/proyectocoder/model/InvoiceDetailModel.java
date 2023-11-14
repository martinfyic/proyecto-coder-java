package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "invoice_id", nullable = false)
  private InvoiceModel invoice;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private ProductModel product;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private Double price;


}
