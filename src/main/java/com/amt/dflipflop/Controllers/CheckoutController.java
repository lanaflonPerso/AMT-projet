package com.amt.dflipflop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String reviewCheckout(Model model) {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkoutCart(Model model) {
        // We should inject error or success in the model
        return "checkout";
    }

}
