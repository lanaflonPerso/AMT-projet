package com.amt.dflipflop.Entities;

import javax.persistence.*;

@Entity
@Table(name = "productSelection")
public class ProductSelection{

    public ProductSelection() {

    }

    public ProductSelection(Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Product product;
    private Integer quantity;
}