package com.sales.proyectocoder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

  @NotNull
  private Integer id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotNull
  private Date birthdate;

  @NotBlank
  private String docNumber;
}
