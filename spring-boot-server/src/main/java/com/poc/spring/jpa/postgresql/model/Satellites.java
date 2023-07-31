package com.poc.spring.jpa.postgresql.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "satellites")
public class Satellites {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "category")
  private String category;
  

  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;

  @Column(name = "launched")
  private boolean launched;
  
  

  public Satellites() {

  }

  public Satellites(String category,String name, String description, boolean launched) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.launched = launched;
  }


}
