package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.exceptions.EntityNotFoundException;
import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.response.DeleteResponse;
import com.sales.proyectocoder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  /**
   * @return Lista de productos guardados en DB
   */
  @GetMapping
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    List<ProductModel> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  /**
   * @param id número identificador del producto
   * @return Retorna producto seleccionado
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Integer id) {
    try {
      ProductModel product = productService.getProductById(id);
      return ResponseEntity.ok(product);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * @param product información del producto a crear, ejemplo: { "code": "TSHIRT", "description": "Remera clasica blanca", "stock": 40, "price": 1200 }
   * @return Retorna producto creado
   */
  @PostMapping
  public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
    ProductModel createdProduct = productService.createProduct(product);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  /**
   * @param product información para actualizar producto
   * @param id      número identificador del producto
   * @return Retorna producto actualizado
   */
  @PutMapping("/{id}")
  public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel product, @PathVariable Integer id) {
    ProductModel updatedProduct = productService.updateProduct(product, id);
    return (updatedProduct != null) ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
  }

  /**
   * @param id número identificador del producto
   * @return Devuelve String con mensaje de success o error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Integer id) {
    DeleteResponse response = productService.deleteProduct(id);
    return ResponseEntity.ok(response);
  }

}
