package com.sales.proyectocoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InvoiceDetailDTO {

  private Integer productId;
  private Integer quantity;
  private Double unitPrice;
  private Double totalPrice;

}
