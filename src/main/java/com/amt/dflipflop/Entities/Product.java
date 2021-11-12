package com.amt.dflipflop.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

    @OneToMany
    private ArrayList<Category> categories;

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

    public ArrayList<Category> getCategories() {
        return categories;
    }
    public String getCategoriesName() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < categories.size(); ++i) {
            if(i != 0) {
                str.append(" ");
            }
                str.append(categories.get(i).getName());
        }
        return str.toString();
    }
    public ArrayList<Integer> getCategoriesId() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(Category category : categories) {
            ids.add(category.getId());
        }
        return ids;
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }
    public void setCategories(Collection<Category> categories) {
        this.categories = (ArrayList<Category>)categories;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageRelativePath() {
        return "images/" + imageName;
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