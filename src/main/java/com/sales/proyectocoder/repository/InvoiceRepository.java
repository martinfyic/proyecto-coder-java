package com.sales.proyectocoder.repository;

import com.sales.proyectocoder.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {
}
