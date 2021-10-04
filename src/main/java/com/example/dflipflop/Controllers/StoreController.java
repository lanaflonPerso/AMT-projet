package com.example.dflipflop.Controllers;

import com.example.dflipflop.Entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
public class StoreController {

    @GetMapping("/store")
    public String getStorePage(Model model) {
        List<Product> products = Arrays.asList(
                new Product(0, "Produit 1", "Super produit 1", 3.5f, "shoes-img9"),
                new Product(0, "Produit 2", "Super produit 2", 6.7f, "shoes-img9")
        );
        model.addAttribute("products", products);
        return "store";
    }

    @GetMapping("/store/product/{id}")
    public String getStoreProduct(@PathVariable("id") Integer productId, Model model) {
        return "product";
    }

    @GetMapping("/store/add-product")
    public String getAddProductPage(Model model) {
        return "add-product";
    }

    @PostMapping(path="/store/add-product") // Map ONLY POST Requests
    public @ResponseBody
    String addNewProduct (@ModelAttribute("product") Product product, BindingResult result) {
        if(result.hasErrors()){
            return "add-product";
        }
        // Add the product via a product service
        return "redirect:/store/product/" + product.getId();
    }

}
