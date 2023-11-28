package com.sales.proyectocoder.service;

import com.sales.proyectocoder.DTO.InvoiceDTO;
import com.sales.proyectocoder.model.ClientModel;
import com.sales.proyectocoder.model.InvoiceModel;
import com.sales.proyectocoder.repository.ClientRepository;
import com.sales.proyectocoder.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;
  @Autowired
  private ClientRepository clientRepository;

  /**
   *
   * @return Metodo para devolver todas las invoices
   */
  public List<InvoiceDTO> getAllInvoices() {
    List<InvoiceModel> invoices = invoiceRepository.findAll();
    return invoices.stream().map(invoice -> {
      InvoiceDTO invoiceDTO = new InvoiceDTO();
      invoiceDTO.setClientId(invoice.getClient().getId());
      invoiceDTO.setCreatedAt(invoice.getCreatedAt());
      invoiceDTO.setTotal(invoice.getTotal());
      return invoiceDTO;
    }).collect(Collectors.toList());
  }

  /* TODO
   *  Listar una factura por id
   */


  /* TODO
   *  Crear factura
   */
  public InvoiceModel createInvoice(InvoiceDTO invoiceDTO){
    Optional<ClientModel> optionalClient = clientRepository.findById(invoiceDTO.getClientId());

    if (optionalClient.isPresent()) {
      InvoiceModel invoice = new InvoiceModel();
      invoice.setClient(optionalClient.get());
      invoice.setCreatedAt(new Date());
      invoice.setTotal(invoiceDTO.getTotal());

      return invoiceRepository.save(invoice);
    } else {
      throw new RuntimeException("Cliente no encontrado con ID: " + invoiceDTO.getClientId());
    }
  }

  /* TODO
   *  Actualizar un producto por id
   */

  /* TODO
   *  Eliminar un producto por id
   */

}
