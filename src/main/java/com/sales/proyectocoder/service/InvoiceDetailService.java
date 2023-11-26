package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.InvoiceDetailModel;
import com.sales.proyectocoder.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {

  @Autowired
  private InvoiceDetailRepository invoiceDetailRepository;

  /**
   *
   * @return Lista de todas las InvoiceDetail
   */
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
