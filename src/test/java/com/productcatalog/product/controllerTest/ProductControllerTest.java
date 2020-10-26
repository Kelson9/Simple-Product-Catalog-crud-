package com.productcatalog.product.controllerTest;

import com.productcatalog.product.controller.ProductController;
import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {


  @Mock
  private ProductService mockProductService;

  private ProductController productController;

  @BeforeEach
  void setUp() {
    productController = new ProductController(mockProductService);

  }

  @Test
  void createProduct_CallsProductService() {
    ProductDTO productDTO = new ProductDTO();
    ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);
    verify(mockProductService).createProduct(productDTO);
    assertThat(response.getStatusCodeValue()).isEqualTo(200);
  }

  @Test
  void getA_Product_CallsProductService(){
    when(mockProductService.getProduct(anyLong()))
      .thenReturn(new ProductDTO());

    ResponseEntity<ProductDTO> response = productController.getProduct(1L);
    verify(mockProductService).getProduct(1L);
    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody()).isInstanceOf(ProductDTO.class);
  }

  @Test
  void getProducts_CallsProductService(){

    ResponseEntity<List<ProductDTO>> response = productController.getProducts();
    verify(mockProductService).getProducts();
    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody()).isInstanceOf(List.class);
  }

  @Test
  void  updateProduct_CallsProductService(){
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("Laptop");
    ResponseEntity<?> response = productController.updateProduct(productDTO,1L);
    verify(mockProductService).updateProduct(productDTO,1L);
    assertThat(response.getStatusCodeValue()).isEqualTo(204);
  }

  @Test
  void deleteProduct_CallsProductService(){
    ResponseEntity<?> response = productController.deleteProduct(1L);
    verify(mockProductService).deleteProduct(1L);
    assertThat(response.getStatusCodeValue()).isEqualTo(204);

  }

}
