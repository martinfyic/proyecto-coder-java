package com.sales.proyectocoder.repository;

import com.sales.proyectocoder.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
}
