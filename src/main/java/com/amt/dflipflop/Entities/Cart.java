package com.amt.dflipflop.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {

    // needed for SpringBoot Hibernate to create objects
    public Cart() {}

    public Cart(Integer userID){
        this.userID = userID;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer userID;

    @OneToMany
    private Set<Product> employeeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}