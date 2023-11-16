package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  /*
   * Listar todos los productos
   */
  public List<ProductModel> getAllProducts() {
    return productRepository.findAll();
  }

  /* TODO
   *  Listar un producto por id
   */

  /* TODO
   *  Crear producto
   */

  /* TODO
   *  Actualizar un producto por id
   */

  /* TODO
   *  Eliminar un producto por id
   */
}
