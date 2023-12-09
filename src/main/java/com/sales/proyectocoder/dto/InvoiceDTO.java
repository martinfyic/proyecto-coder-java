package com.sales.proyectocoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InvoiceDTO {

  private Integer id;
  private Integer clientId;
  private LocalDateTime createdAt;
  private List<InvoiceDetailDTO> details;
  private Double total;

}
