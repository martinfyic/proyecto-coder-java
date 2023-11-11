package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, length = 50)
  private String code;

  @Column(length = 150)
  private String description;

  @Column(nullable = false)
  private int stock;

  @Column(nullable = false)
  private double price;

  @OneToMany(mappedBy = "product")
  private List<InvoiceDetailModel> invoiceDetails;
}
