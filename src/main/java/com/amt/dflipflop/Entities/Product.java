package com.amt.dflipflop.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Product {

    // needed for SpringBoot Hibernate to create objects
    public Product() {}

    public Product(String name, String description, Float price,  String imageName){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Float price;

    private String imageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageRelativePath() {
        // assuming we have only PNG files
        return "images/" + imageName + ".png";
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}