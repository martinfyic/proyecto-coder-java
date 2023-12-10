package com.sales.proyectocoder.controller;

import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.response.DeleteResponse;
import com.sales.proyectocoder.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product Controller", description = "Request relacionadas con los productos")
public class ProductController {
  @Autowired
  private ProductService productService;

  @Operation(summary = "Lista todos los productos", description = "Lista todos los productos")
  @GetMapping
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    List<ProductModel> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @Operation(summary = "Muestra un producto seleccionado", description = "Muestra un producto seleccionado por su id")
  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Integer id) {
      ProductModel product = productService.getProductById(id);

    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("status", "error");
      response.put("message", "No se encontró un producto con el ID proporcionado: " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @Operation(summary = "Crear un producto", description = "Crea un producto y lo guarda en DB")
  @PostMapping
  public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
    ProductModel createdProduct = productService.createProduct(product);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @Operation(summary = "Actualizar producto", description = "Actualiza un producto seleccionado por su id")
  @PutMapping("/{id}")
  public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel product, @PathVariable Integer id) {
    ProductModel updatedProduct = productService.updateProduct(product, id);
    return (updatedProduct != null) ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Elimina un producto", description = "Elimina un producto seleccionado por su id")
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Integer id) {
    DeleteResponse response = productService.deleteProduct(id);
    return ResponseEntity.ok(response);
  }

}
