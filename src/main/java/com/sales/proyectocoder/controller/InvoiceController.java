package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.DTO.InvoiceDTO;
import com.sales.proyectocoder.model.InvoiceModel;
import com.sales.proyectocoder.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  /**
   *
   * @return Lista de todas las invoices
   */
  @GetMapping
  public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
    List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.ok(invoices);
  }

  /* TODO
  *   POST - Crear Invoice
  */
  @PostMapping
  public InvoiceModel createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
    return invoiceService.createInvoice(invoiceDTO);
  }

  /* TODO
   *   GET - Listar invoice por ID
   */

}
