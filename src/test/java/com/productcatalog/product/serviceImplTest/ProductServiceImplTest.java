package com.productcatalog.product.serviceImplTest;

import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.exception.ResourceNotFoundException;
import com.productcatalog.product.model.Product;
import com.productcatalog.product.repository.ProductRepository;
import com.productcatalog.product.service.ProductService;
import com.productcatalog.product.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public  class ProductServiceImplTest {

  @Mock
  private ProductRepository mockProductRepository;

  private ProductService productService;

  private ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);


  @BeforeEach
  void setUp() {
    productService = new ProductServiceImpl(mockProductRepository);

  }

  @Test
  void  createProduct_Creates_a_NewProduct(){
    ProductDTO productDTO=new ProductDTO();
    productDTO.setId(1L);
    productDTO.setName("Telephone");
    productDTO.setPrice("20000");

    productService.createProduct(productDTO);
    verify(mockProductRepository).save(productArgumentCaptor.capture());
    assertThat(productArgumentCaptor.getValue().getName()).isEqualTo("Telephone");
    assertThat(productArgumentCaptor.getValue().getPrice()).isEqualTo("20000");
  }

   @Test
   void getProduct_ReturnsAProduct(){


     Product product=new Product();
     product.setId(1L);
     product.setPrice("1000");
     product.setName("Bread");

     when(mockProductRepository.findById(anyLong()))
      .thenReturn(Optional.of(product));

    ProductDTO productDTO=productService.getProduct(1L);
    verify(mockProductRepository).findById(1L);

    assertThat(productDTO.getPrice()).isEqualTo("1000");
    assertThat(productDTO.getName()).isEqualTo("Bread");

   }

   @Test
   void getProducts_ReturnsProducts(){
     Product product=new Product();
     product.setId(1L);
     product.setPrice("1000");
     product.setName("Bread");

    when(mockProductRepository.findAll())
       .thenReturn(Collections.singletonList(product));

     List<ProductDTO> productDTOList=productService.getProducts();
     verify(mockProductRepository).findAll();
     assertThat(productDTOList.get(0).getName()).isEqualTo("Bread");
     assertThat(productDTOList.get(0).getPrice()).isEqualTo("1000");

   }

   @Test
  void updateProduct_Updates_a_Product(){
     Product product=new Product();
     product.setId(1L);
     product.setPrice("1000");
     product.setName("Bread");

     ProductDTO productDTO=new ProductDTO();
     productDTO.setId(1L);
     productDTO.setName("Telephone");
     productDTO.setPrice("20000");

     when(mockProductRepository.findById(anyLong()))
       .thenReturn(Optional.of(product));

     productService.updateProduct(productDTO,1L);
     verify(mockProductRepository).findById(1L);
     verify(mockProductRepository).save(productArgumentCaptor.capture());
     assertThat(productArgumentCaptor.getValue().getName()).isEqualTo("Telephone");
     assertThat(productArgumentCaptor.getValue().getPrice()).isEqualTo("20000");

  }

  @Test
  void updateProduct_throwsException_whenResourceNotFound(){

    when(mockProductRepository.findById(anyLong()))
      .thenReturn(Optional.empty());

    ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () ->productService.updateProduct(new ProductDTO(),1L));
    verify(mockProductRepository).findById(1L);
    assertThat(resourceNotFoundException.getMessage()).isEqualTo("Resource not found");

  }

  @Test
    void deleteProduct_Deletes_A_Product(){

    productService.deleteProduct(1L);
    verify(mockProductRepository).deleteById(1L);
  }

}
