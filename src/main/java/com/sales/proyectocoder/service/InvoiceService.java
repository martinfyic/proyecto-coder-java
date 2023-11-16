package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.InvoiceModel;
import com.sales.proyectocoder.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  public List<InvoiceModel> getAllInvoice() {
    return invoiceRepository.findAll();
  }

  /* TODO
   *  Listar una factura por id
   */

  /* TODO
   *  Crear factura
   */

  /* TODO
   *  Actualizar un producto por id
   */

  /* TODO
   *  Eliminar un producto por id
   */
}
