package com.amt.dflipflop.Services;

import com.amt.dflipflop.Entities.Cart;
import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public ArrayList<Cart> getAll() {

        Iterable<Cart> it = cartRepository.findAll();

        ArrayList<Cart> carts = new ArrayList<>();
        it.forEach(carts::add);

        return carts;
    }

    public Cart get(Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    // should we make it return void ?
    public Cart insert(Cart cart){
        return cartRepository.save(cart);
    }

    public Long count() {
        return cartRepository.count();
    }

    public Cart update(Cart cart){
        return cartRepository.save(cart);
    }


    /**
     * Returns a list of products related to the given cart
     * @param id of the cart
     * @return the list of products filtered with the cart
     * @implNote It is not optimal, but I couldn't find a better way to implement it with hibernate
     */
    /*
    public List<Product> getProducts(Integer id){
        Cart cart = get(id);
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < cart.nbProduct(); ++i){
            products.add(cart.getProductById(i));
        }
        return products;
    }

     */
}