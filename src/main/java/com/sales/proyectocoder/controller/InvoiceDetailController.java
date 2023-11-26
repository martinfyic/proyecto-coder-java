package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.InvoiceDetailModel;
import com.sales.proyectocoder.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invoice-detail")
public class InvoiceDetailController {

  @Autowired
  private InvoiceDetailService invoiceDetailService;

  /**
   *
   * @return Lista de todas las InvoiceDetail
   */
  @GetMapping
  public ResponseEntity<List<InvoiceDetailModel>> getAllInvoiceDetails() {
    List<InvoiceDetailModel> products = invoiceDetailService.getAllInvoiceDetails();
    return ResponseEntity.ok(products);
  }

}
