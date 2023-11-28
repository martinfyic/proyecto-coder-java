package com.sales.proyectocoder.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDTO {

  @NotNull
  private Integer clientId;

  @NotNull
  private Date createdAt;

  @NotNull
  private Double total;
}
