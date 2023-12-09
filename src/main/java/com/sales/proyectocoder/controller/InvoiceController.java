package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.dto.InvoiceDTO;
import com.sales.proyectocoder.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/invoices")
@Tag(name = "Invoice Controller", description = "Request relacionadas con las facturas")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @Operation(summary = "Lista de todas las facturas", description = "Lista todas las facturas")
  @GetMapping
  public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
    List<InvoiceDTO> invoices = invoiceService.getAllInvoicesDTO();
    return ResponseEntity.ok(invoices);
  }

  @Operation(summary = "Muestra factura seleccionada por su ID", description = "Muestra factura seleccionada por su ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getInvoiceById(@PathVariable Integer id) {
    InvoiceDTO invoice = invoiceService.getInvoiceById(id);

    if (invoice != null) {
      return ResponseEntity.ok(invoice);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró una factura con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

  }

  @Operation(summary = "Crear una factura", description = "Crea una factura y lo guarda en DB")
  @PostMapping
  public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {

    if (invoiceDTO == null || invoiceDTO.getClientId() == null) {
      return ResponseEntity.badRequest().build();
    }

    InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
    return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
  }

}
