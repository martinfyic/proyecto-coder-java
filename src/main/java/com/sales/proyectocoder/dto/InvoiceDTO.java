package com.sales.proyectocoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InvoiceDTO {

  private Integer id;
  private Integer clientId;
  private Date createdAt;
  private List<InvoiceDetailDTO> details;
  private Double total;

}
