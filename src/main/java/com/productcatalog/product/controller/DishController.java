package com.productcatalog.product.controller;

import javax.servlet.http.HttpServletRequest;

import com.productcatalog.product.model.Dish;
import com.productcatalog.product.serviceImpl.DishDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class DishController {
	
	
	@Autowired
	DishDaoService productService;
		
	
	@GetMapping("/dish")
	public List<Dish> retrieveProducts() {
		return productService.getDish();
	}
	
	@GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        return productService.showImage(fileName, request);
    }
	
	@GetMapping("/dish/{dishId}")
	public Dish retrieveProduct(@PathVariable Long dishId) {
		return productService.getDish(dishId);
	}
	
	@DeleteMapping("/dish/{dishId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long dishId) {
		return productService.removeDish(dishId);
	}
	
	@PostMapping("/dish")
	public ResponseEntity<Object> createCategory( @RequestParam String name, @RequestParam int quantity,
			@RequestParam String price, @RequestPart MultipartFile pxtImage) {
		
		return productService.postDish(name, quantity, price, pxtImage);
		
	}
	
//	@PutMapping("/products/{productId}")
//	public ResponseEntity<Object> updateCategory(@PathVariable int productId, @RequestParam String name, 
//			@RequestParam int quantity, @RequestParam String price, @RequestPart MultipartFile pxtImage) {
//		
//		return productService.editProduct(productId, name, quantity, price, pxtImage);
//		
//	}	
	
	
	

}
