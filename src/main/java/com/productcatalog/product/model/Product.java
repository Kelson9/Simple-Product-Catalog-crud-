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

}
