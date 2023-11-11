package com.sales.proyectocoder.repository;

import com.sales.proyectocoder.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ClientModel, Integer> {
}
