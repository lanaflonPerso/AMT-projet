package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    final static Integer NB_LATEST_PRODUCTS = 3;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAll();


        // show the N last products
        int count = products.size();
        if(count > NB_LATEST_PRODUCTS)
            products = products.subList(count - NB_LATEST_PRODUCTS, count);

        model.addAttribute("products", products);
        return "index";
    }

}
