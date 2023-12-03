package com.sales.proyectocoder.dto;

import com.sales.proyectocoder.model.InvoiceDetailModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class InvoiceDTO {

  @NotNull
  private Integer clientId;

  @NotNull
  private Date createdAt;

  @NotNull
  private Double total;

  private Set<InvoiceDetailModel> invoiceDetail;
}
