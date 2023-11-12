package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.InvoiceDetailModel;
import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvoiceDetailService {

  @Autowired
  private InvoiceDetailRepository invoiceDetailRepository;

  public List<InvoiceDetailModel> getAllInvoiceDetails() {
    return invoiceDetailRepository.findAll();
  }

  /* TODO
   *  Listar una invoiceDetail por id
   */

  /* TODO
   *  Crear invoiceDetail
   */

  /* TODO
   *  Actualizar un invoiceDetail por id
   */

  /* TODO
   *  Eliminar un invoiceDetail por id
   */
}
