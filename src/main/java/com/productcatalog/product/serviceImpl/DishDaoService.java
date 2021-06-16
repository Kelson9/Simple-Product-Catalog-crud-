package com.productcatalog.product.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.productcatalog.product.exception.ResourceNotFoundException;
import com.productcatalog.product.model.Dish;
import com.productcatalog.product.repository.DishRepository;
import com.productcatalog.product.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class DishDaoService {

	private static final Logger logger = LoggerFactory.getLogger(DishDaoService.class);
	@Autowired
	DishRepository productRepo;
	

	@Autowired
    private FileStorageService fileStorageService;
	
	
    public ResponseEntity<org.springframework.core.io.Resource> showImage(String fileName, HttpServletRequest request) {
        // Load file as Resource
    	org.springframework.core.io.Resource  resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	
	
	public List<Dish> getDish() {
		return productRepo.findAll();
		
	}
	
	public Dish getDish(Long id) {
		Optional<Dish> product = productRepo.findById(id);
		
		if(!product.isPresent()) {
			throw new ResourceNotFoundException("id - " +id);
		}
			
		
		return product.get();
	}
	
	public ResponseEntity<Object> removeDish(Long id) {
		Optional<Dish> delProduct = productRepo.findById(id);
		productRepo.deleteById(id);
		return new ResponseEntity<>(delProduct, HttpStatus.NO_CONTENT);
	}

	
	public ResponseEntity<Object> postDish(String name, int quantity, String price,  MultipartFile pxtImage) { //@Valid
		
		String fileName = fileStorageService.storeFile(pxtImage);
		
		String productUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();
		
		Dish newProduct = new Dish();		//Product(fileName, productUri, file.getContentType(), file.getSize())
		     newProduct.setName(name);
		     newProduct.setPrice(price);
		     newProduct.setQuantity(quantity);
		     newProduct.setPxtUri(productUri);
		newProduct = productRepo.save(newProduct);
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newProduct.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
public ResponseEntity<Object> editProduct(Long pxtId,String name, int quantity, String price,  MultipartFile pxtImage) {
		
		Optional<Dish> product = productRepo.findById(pxtId);
		if(product.isPresent()) {
			
		
			
			
			String fileName = fileStorageService.storeFile(pxtImage);
			
			Dish productUpdate = product.get();
			productUpdate.setName(name);
			productUpdate.setQuantity(quantity);
			productUpdate.setPrice(price);
			
			String productUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/downloadFile/")
	                .path(fileName)
	                .toUriString();
			
			
			productUpdate.setPxtUri(productUri);
			 
			//productUpdate = productRepo.save(productUpdate);
			
			
			
			return new ResponseEntity<>(productRepo.save(productUpdate), HttpStatus.NO_CONTENT);
			
		}
		throw new ResourceNotFoundException("product_id - " +pxtId);	
	}
	

	
	public ResponseEntity<Object> editProduct(Long pxtId, Dish newProduct) {
		
		Optional<Dish> product = productRepo.findById(pxtId);
		if(product.isPresent()) {
			
		
			Dish productUpdate = product.get();
			productUpdate.setName(newProduct.getName());
			productUpdate.setQuantity(newProduct.getQuantity());
			productUpdate.setPrice(newProduct.getPrice());
			
			
			return new ResponseEntity<>(productRepo.save(productUpdate), HttpStatus.NO_CONTENT);
			
		}
		throw new ResourceNotFoundException("product_id - " +pxtId);
		
		
		 
		
		
	}
	
	
	
}
