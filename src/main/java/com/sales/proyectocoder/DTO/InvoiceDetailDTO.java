package com.sales.proyectocoder.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceDetailDTO {

  @NotNull
  private Integer invoiceId;

  @NotNull
  private Integer productId;

  @NotNull
  private Integer quantity;

  @NotNull
  private Double price;
}
