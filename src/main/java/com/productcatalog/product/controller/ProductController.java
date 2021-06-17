package com.productcatalog.product.controller;

import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

  private ProductService productService;


  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity.ok(productService.createProduct(productDTO));

  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {

    return ResponseEntity.ok(productService.getProduct(productId));

  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getProducts() {
     System.out.println("Hello World");
    return ResponseEntity.ok(productService.getProducts());

  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return  ResponseEntity.noContent().build();
  }
  @PutMapping("/{productId}")
  public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long productId){
    productService.updateProduct(productDTO,productId);
    return  ResponseEntity.noContent().build();

  }

}
