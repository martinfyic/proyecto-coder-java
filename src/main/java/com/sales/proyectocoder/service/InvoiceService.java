package com.sales.proyectocoder.service;

import com.sales.proyectocoder.dto.InvoiceDTO;
import com.sales.proyectocoder.dto.InvoiceDetailDTO;
import com.sales.proyectocoder.model.InvoiceDetailModel;
import com.sales.proyectocoder.model.InvoiceModel;
import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.repository.InvoiceDetailRepository;
import com.sales.proyectocoder.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

  @Autowired
  InvoiceDetailRepository invoiceDetailRepository;
  @Autowired
  private InvoiceRepository invoiceRepository;
  @Autowired
  private ProductService productService;
  @Autowired
  private ClientService clientService;

  public List<InvoiceModel> getAllInvoices() {
    return invoiceRepository.findAll();
  }

  public InvoiceModel getInvoiceById(Integer id) {
    return invoiceRepository.findById(id).orElse(null);
  }


  public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
    if (invoiceDTO.getCreatedAt() == null) {
      invoiceDTO.setCreatedAt(LocalDateTime.now());
    }

    InvoiceModel savedInvoice = invoiceRepository.save(mapInvoiceToEntity(invoiceDTO));
    return mapInvoiceToDTO(savedInvoice);
  }

  private InvoiceModel mapInvoiceToEntity(InvoiceDTO invoiceDTO) {
    InvoiceModel invoice = new InvoiceModel();
    invoice.setClient(clientService.getClientById(invoiceDTO.getClientId()));
    invoice.setDetails(new ArrayList<>());
    invoice.setCreatedAt(invoiceDTO.getCreatedAt());

    double total = 0;
    List<InvoiceDetailModel> newDetailsList = new ArrayList<>();

    for (InvoiceDetailDTO detailDTO : invoiceDTO.getDetails()) {
      ProductModel product = productService.getProductById(detailDTO.getProductId());

      if (product.getStock() < detailDTO.getQuantity()) {
        throw new RuntimeException("No hay suficiente stock para el producto: " + product.getCode());
      }

      InvoiceDetailModel detail = new InvoiceDetailModel();
      detail.setProduct(product);
      detail.setQuantity(detailDTO.getQuantity());
      detail.setUnitPrice(product.getPrice());
      detail.setTotalPrice(detail.getQuantity() * detail.getUnitPrice());
      detail.setInvoice(invoice);

      newDetailsList.add(detail);

      total += detail.getTotalPrice();

      product.setStock(product.getStock() - detailDTO.getQuantity());
      productService.createProduct(product);
    }

    invoice.setTotal(total);
    invoice.getDetails().addAll(newDetailsList);

    invoice = invoiceRepository.save(invoice);
    invoiceDetailRepository.saveAll(newDetailsList);

    return invoice;
  }

  private InvoiceDTO mapInvoiceToDTO(InvoiceModel invoice) {
    InvoiceDTO invoiceDTO = new InvoiceDTO();
    invoiceDTO.setClientId(invoice.getClient().getId());
    invoiceDTO.setCreatedAt(invoice.getCreatedAt());

    List<InvoiceDetailDTO> detailsDTO = invoice.getDetails().stream()
        .map(this::mapDetailToDTO)
        .collect(Collectors.toList());
    invoiceDTO.setDetails(detailsDTO);

    return invoiceDTO;
  }

  private InvoiceDetailDTO mapDetailToDTO(InvoiceDetailModel detail) {
    InvoiceDetailDTO detailDTO = new InvoiceDetailDTO();
    detailDTO.setProductId(detail.getProduct().getId());
    detailDTO.setQuantity(detail.getQuantity());
    detailDTO.setUnitPrice(detail.getUnitPrice());
    detailDTO.setTotalPrice(detail.getTotalPrice());

    return detailDTO;
  }

}

