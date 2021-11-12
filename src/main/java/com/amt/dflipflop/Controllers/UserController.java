package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.Cart;
import com.amt.dflipflop.Entities.ProductSelection;
import com.amt.dflipflop.Services.CartService;
import com.amt.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/user/orders")
    public String getUserOrders(Model model) {

        if(cartService.get(0) == null){
            Cart cart = new Cart();
            ProductSelection sel = new ProductSelection();
            sel.setProduct(productService.get(0));
            cart.addSelection(sel);
            cartService.insert(cart);
        }
        else{
            Cart userCart = cartService.get(0);
            userCart.getTotal();
        }

        return "orders";
    }

    @GetMapping("/user/addresses")
    public String getAddressesPage(Model model) {
        return "addresses";
    }

    @GetMapping("/user/add-address")
    public String getAddAddressPage(Model model) {
        return "add-address";
    }

    @PostMapping(path="/user/add-address") // Map ONLY POST Requests
    public @ResponseBody
    String addNewAddress () {
        return "add-address";
    }

}