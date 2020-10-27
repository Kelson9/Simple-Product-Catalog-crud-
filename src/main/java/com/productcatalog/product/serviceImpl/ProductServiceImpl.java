package com.productcatalog.product.serviceImpl;

import com.productcatalog.product.dto.ProductDTO;
import com.productcatalog.product.exception.ResourceNotFoundException;
import com.productcatalog.product.model.Product;
import com.productcatalog.product.repository.ProductRepository;
import com.productcatalog.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.productcatalog.product.exception.ErrorCodes.RESOURCE_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

  private  ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product= new Product();
    product.setId(productDTO.getId());
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    productRepository.save(product);

    ProductDTO productDTO1=new ProductDTO();
    productDTO1.setName(product.getName());
    productDTO1.setId(product.getId());
    productDTO1.setPrice(product.getPrice());
    return  productDTO1;

  }

  @Override
  public ProductDTO getProduct(Long productId) {
    Optional<Product> product =productRepository.findById(productId);
    if(!product.isPresent()) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage());
    }
    ProductDTO productDTO=new ProductDTO();
    productDTO.setId(productId);
    productDTO.setName(product.get().getName());
    productDTO.setPrice(product.get().getPrice());
    return  productDTO;
  }

  @Override
  public List<ProductDTO> getProducts() {
    List<Product> product=productRepository.findAll();
    List<ProductDTO> productDTOS=new ArrayList<>();
    for(Product product1: product){
      ProductDTO productDTO = new ProductDTO();
      productDTO.setPrice(product1.getPrice());
      productDTO.setName(product1.getName());
      productDTO.setId((product1.getId()));
      productDTOS.add(productDTO);
    }
    return  productDTOS;
  }

  @Override
  public void deleteProduct(Long productId) {
   productRepository.deleteById(productId);
  }

  @Override
  public void updateProduct(ProductDTO productDTO,Long productId){
      Optional<Product> product = productRepository.findById(productId);
      if (!product.isPresent()) {
        throw new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage());
      }

      Product product1 = new Product();
      product1.setName(productDTO.getName());
      product1.setPrice(productDTO.getPrice());
      product1.setId(productId);

      productRepository.save(product1);
    }

}
