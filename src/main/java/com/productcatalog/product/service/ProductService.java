package com.productcatalog.product.service;

import com.productcatalog.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

  ProductDTO createProduct(ProductDTO productDTO);

  ProductDTO getProduct(Long productId);

  List<ProductDTO> getProducts();

  void deleteProduct(Long productId);

  void updateProduct(ProductDTO productDTO,Long productId);
}
