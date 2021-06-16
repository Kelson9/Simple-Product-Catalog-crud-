package com.productcatalog.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Dish {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

private String price;

private int quantity;

private String pxtUri;

 public Dish() {}
public Dish(Long id, String name, String price, int quantity, String pxtUri) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
	this.quantity = quantity;
	this.pxtUri = pxtUri;
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



public int getQuantity() {
	return quantity;
}



public void setQuantity(int quantity) {
	this.quantity = quantity;
}



public String getPxtUri() {
	return pxtUri;
}



public void setPxtUri(String pxtUri) {
	this.pxtUri = pxtUri;
}
@Override
public String toString() {
	return "Dish [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", pxtUri=" + pxtUri
			+ "]";
}











}
