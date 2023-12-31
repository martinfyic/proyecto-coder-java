package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 50)
  private String code;

  @Column(length = 150)
  private String description;

  @Column(nullable = false)
  private Integer stock;

  @Column(nullable = false)
  private Double price;
}