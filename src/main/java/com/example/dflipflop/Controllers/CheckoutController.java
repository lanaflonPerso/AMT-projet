package com.example.dflipflop.Controllers;

import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
