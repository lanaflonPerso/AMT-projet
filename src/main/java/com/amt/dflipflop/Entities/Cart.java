package com.amt.dflipflop.Entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "carts")
public class Cart {

    public Cart(){
    // Add user
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private boolean submitted;

    @OneToMany
    @JoinColumn(name = "selection_id")
    private List<ProductSelection> selections;

    public boolean isSubmitted() {
        return submitted;
    }

    public float getTotal(){
        float total = 0.f;
        for (ProductSelection selection: this.selections) {
            total += selection.getProduct().getPrice() * selection.getQuantity();
        }
        return total;
    }

    public List<ProductSelection> getSelections() {
        return selections;
    }
}
