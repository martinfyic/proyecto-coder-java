package com.sales.proyectocoder.service;

import com.sales.proyectocoder.model.ProductModel;
import com.sales.proyectocoder.repository.ProductRepository;
import com.sales.proyectocoder.response.DeleteResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  /**
   *
   * @return Listar todos los productos
   */
  public List<ProductModel> getAllProducts() {
    return productRepository.findAll();
  }

  /**
   *
   * @param id Listar producto por su id
   * @return Retorno producto seleccionado
   */
  public ProductModel getProductById(Integer id) {
    return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto con id " + id + " no encontrado"));
  }

  /**
   *
   * @param product producto a crear
   * @return Retorna producto creado
   */
  public ProductModel createProduct(ProductModel product) {
    return productRepository.save(product);
  }

  /*
   *  Actualizar un producto por id
   */
  public ProductModel updateProduct(ProductModel product, Integer id) {
    Optional<ProductModel> productExist = productRepository.findById(id);

    if (productExist.isPresent()) {
      ProductModel productUpdated = productExist.get();

      if (product.getCode() != null) {
        productUpdated.setCode(product.getCode());
      }
      if (product.getDescription() != null) {
        productUpdated.setDescription(product.getDescription());
      }
      if (product.getStock() != null) {
        productUpdated.setStock(product.getStock());
      }
      if (product.getPrice() != null) {
        productUpdated.setPrice(product.getPrice());
      }

      return productRepository.save(productUpdated);
    } else {
      return null;
    }
  }

  /**
   *
   * @param id identificador unico del producto
   * @return retorna String con mensaje
   */
  public DeleteResponse deleteProduct(Integer id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return new DeleteResponse("success", "Producto con ID " + id + " eliminado correctamente.");
    } else {
      return new DeleteResponse("error", "No se encontró un producto con el ID proporcionado: " + id);
    }
  }
}
