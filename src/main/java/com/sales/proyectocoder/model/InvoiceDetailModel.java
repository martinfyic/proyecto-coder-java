package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Integer quantity;

  @NotNull
  @Positive
  @Column(nullable = false)
  private Double price;


}
