package com.productcatalog.product.controller;

import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private ProductService productService;


  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity.ok(productService.createProduct(productDTO));

  }

  @GetMapping("/{productId}")
  ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {

    return ResponseEntity.ok(productService.getProduct(productId));

  }

  @GetMapping
  ResponseEntity<List<ProductDTO>> getProducts() {
     System.out.println("Hello World");
    return ResponseEntity.ok(productService.getProducts());

  }

  @DeleteMapping("/{productId}")
  ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return  ResponseEntity.noContent().build();
  }
  @PutMapping("/{productId}")
  ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable Long productId){
    productService.updateProduct(productDTO,productId);
    return  ResponseEntity.noContent().build();

  }

}
