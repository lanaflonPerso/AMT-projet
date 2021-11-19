package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.Cart;
import com.amt.dflipflop.Entities.ProductSelection;
import com.amt.dflipflop.Services.CartService;
import com.amt.dflipflop.Services.ProductSelectionService;
import com.amt.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSelectionService selectionService;


    @GetMapping("/cart-create")
    public String createCart(Model model) {
        Cart cart = new Cart();
        ProductSelection sel = new ProductSelection();
        sel.setProduct(productService.get(1));
        sel.setQuantity(2);
        cart.addSelection(sel);
        cartService.save(cart);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/cart")
    public String displayCart(Model model) {
        Cart cart = cartService.get(22);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping(path="/cart") // Map ONLY POST Requests
    public String saveCart (@ModelAttribute Cart cart) throws IOException {
        Cart userCart = cartService.get(22);
        Integer index = 0;
        for (ProductSelection sel: userCart.getSelections()){
            sel.setQuantity(cart.getSelections().get(index).getQuantity());
            index++;
            selectionService.save(sel);
        }
        return "redirect:/cart";
    }
}

