package com.productcatalog.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

  private Long id;
  private   String name;
  private  String price;
  
  public ProductDTO() {}
public ProductDTO(Long id, String name, String price) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
@Override
public String toString() {
	return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + "]";
}
  
  

}
