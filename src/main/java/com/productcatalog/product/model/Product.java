package com.productcatalog.product.model;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Product {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long id;

  @Column
  private String name;

  @Column
  private String price;
  
  public Product() {}

public Product(Long id, String name, String price) {
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
  
  

}
