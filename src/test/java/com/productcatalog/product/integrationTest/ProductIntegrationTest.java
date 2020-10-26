package com.productcatalog.product.integrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.model.Product;
import com.productcatalog.product.repository.ProductRepository;
import com.productcatalog.product.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductIntegrationTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp()  {
  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void createProduct__success_returns_200() throws Exception{
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("Electronics");
    productDTO.setPrice("100000");

    RequestBuilder requestBuilder = post("/api/products")
      .accept(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(productDTO))
      .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
      .andExpect(status().isOk())
      .andReturn();

  }

  @Test
  void getProduct__success_returns_200() throws Exception{
    Product product = new Product();
    product.setName("Electronics");
    product.setPrice("100000");

    Product savedProduct = productRepository.save(product);

    RequestBuilder requestBuilder = get("/api/products/{productId}",savedProduct.getId())
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
      .andExpect(status().isOk())
      .andReturn();
  }


  @Test
  void getProducts__success_returns_200() throws Exception{
    RequestBuilder requestBuilder = get("/api/products")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
      .andExpect(status().isOk())
      .andReturn();
  }
  @Test
  void updateProduct__success_returns_204() throws  Exception{
    Product product = new Product();
    product.setName("Electronics");
    product.setPrice("100000");
    Product savedProduct = productRepository.save(product);

    ProductDTO productDTO = new ProductDTO();
    productDTO.setPrice("200CFA");
    product.setName("Charger");

    RequestBuilder requestBuilder = put("/api/products/{productId}",savedProduct.getId())
       .accept(MediaType.APPLICATION_JSON)
       .content(new ObjectMapper().writeValueAsString(productDTO))
       .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
       .andExpect(status().isNoContent())
       .andReturn();

  }

  @Test
  void deleteProduct__success_returns_204() throws  Exception{
    Product product = new Product();
    product.setName("Electronics");
    product.setPrice("100000");
    Product savedProduct = productRepository.save(product);

    RequestBuilder requestBuilder = delete("/api/products/{productId}",savedProduct.getId())
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
      .andExpect(status().isNoContent())
      .andReturn();


  }

}
