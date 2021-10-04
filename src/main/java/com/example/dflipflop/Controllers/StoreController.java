package com.example.dflipflop.Controllers;

import com.example.dflipflop.Entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoreController {

    @GetMapping("/store")
    public String getStorePage(Model model) {
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
