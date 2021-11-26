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


    // Only used for testing
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

    /**
     * Displays the user's cart
     * @return the cart page
     */
    @GetMapping("/cart")
    public String displayCart(Model model) {
        Cart cart = cartService.get(22);
        model.addAttribute("cart", cart);
        return "cart";
    }

    /**
     * Empties the cart
     * @return the cart page
     */
    @GetMapping("/cart/empty")
    public String emptyCart(Model model) {
        Cart userCart = cartService.get(22);
        userCart.empty();
        cartService.save(userCart);
        return "redirect:/cart";
    }

    /**
     * Saves the cart selection
     * @param cart the new cart to save
     * @return Cart page
     * @throws IOException If fails to write the cart
     */
    @PostMapping(path="/cart")
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

    /**
     * Adds an item selection to the cart
     * @param selection Product selection
     * @return The product page
     * @throws IOException If it can't add the selection
     */
    @PostMapping(path="/cart/add")
    public String addProduct (@ModelAttribute ProductSelection selection) throws IOException {
        // Let's check if we already have a selection for that product
        Cart userCart = cartService.get(22);
        for ( ProductSelection sel : userCart.getSelections()){
            if(sel.getProduct().getId() == selection.getProduct().getId()){
                sel.setQuantity(sel.getQuantity() + selection.getQuantity());
                cartService.save(userCart);
                return "/store/product/" + selection.getProduct().getId();
            }
        }

        userCart.addSelection(selection);
        cartService.save(userCart);
        return "/store/product/" + selection.getProduct().getId();
    }
}

