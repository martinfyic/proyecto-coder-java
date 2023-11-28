package com.sales.proyectocoder.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

  @NotBlank
  private String code;

  @NotBlank
  private String description;

  @NotNull
  private Integer stock;

  @NotNull
  private Double price;

}
