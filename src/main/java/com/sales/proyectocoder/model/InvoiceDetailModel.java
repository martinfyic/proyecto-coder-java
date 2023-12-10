package com.sales.proyectocoder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_details")
public class InvoiceDetailModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "invoice_id", nullable = false)
  private InvoiceModel invoice;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "product_id", nullable = false)
  @JsonManagedReference
  private ProductModel product;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private Double unitPrice;

  @Column(nullable = false)
  private Double totalPrice;

}
