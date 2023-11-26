package com.sales.proyectocoder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Entity
@Data
@Table(name = "products")
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(nullable = false, length = 50)
  private String code;

  @NotBlank
  @Column(length = 150)
  private String description;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Integer stock;

  @NotNull
  @Positive
  @Column(nullable = false)
  private Double price;
}