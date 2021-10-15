package com.amt.dflipflop.Controllers;
import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class StoreController {

    @Autowired
    private ProductService productService;

    @GetMapping("/store")
    public String getStorePage(Model model) {

        ArrayList<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "store";
    }

    @GetMapping("/store/product/{id}")
    public String getStoreProduct(@PathVariable("id") Integer productId, Model model) {

        Product product = productService.get(productId);

        if (product == null) {
            return "redirect:/store/product";
        }

        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/store/add-product")
    public String getAddProductPage(Model model) {
        return "add-product";
    }

    @PostMapping(path="/store/add-product") // Map ONLY POST Requests
    public @ResponseBody
    String addNewProduct (@ModelAttribute("product") Product product, BindingResult result) {
        System.out.println(product);
        if(result.hasErrors()){
            return "add-product";
        }
        // Add the product via a product service
        return "redirect:/store/product/" + product.getId();
    }

}
