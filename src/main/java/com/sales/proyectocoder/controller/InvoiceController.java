package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.dto.InvoiceDTO;
import com.sales.proyectocoder.model.InvoiceModel;
import com.sales.proyectocoder.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @GetMapping
  public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
    List<InvoiceDTO> invoices = invoiceService.getAllInvoicesDTO();
    return ResponseEntity.ok(invoices);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InvoiceModel> getInvoiceById(@PathVariable Integer id) {
    InvoiceModel invoice = invoiceService.getInvoiceById(id);
    return ResponseEntity.ok(invoice);
  }

  @PostMapping
  public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
    InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
    return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
  }

}
