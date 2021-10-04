package com.example.dflipflop.Controllers;
import com.example.dflipflop.Repositories.ProductRepository;
import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private ProductService productService;

    /* TODO: REMOVE -> DEBUG ONLY */
    /*@GetMapping("/insert_items")*/ // Uncomment if needed
    public String insertItems(Model model) {
        productService.insert(new Product("Produit 1", "Super produit 1", 3.5f, "shoes-img3"));
        productService.insert(new Product("Produit 2", "Super produit 2", 6.7f, "shoes-img9"));
        productService.insert(new Product("Produit 3", "Super produit 3", 33f, "shoes-img4"));
        productService.insert(new Product("Produit 4", "Super produit 4", 11.4f, "shoes-img9"));
        productService.insert(new Product("Produit 5", "Super produit 5", 42f, "shoes-img2"));
        productService.insert(new Product("Produit 6", "Super produit 6", 12f, "shoes-img9"));

        return "redirect:store";
    }

    @GetMapping("/store")
    public String getStorePage(Model model) {

        ArrayList<Product> products = productService.findAll();
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
