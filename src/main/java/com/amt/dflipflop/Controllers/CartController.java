package com.amt.dflipflop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String getCart(Model model) {
        return "cart";
    }
}
