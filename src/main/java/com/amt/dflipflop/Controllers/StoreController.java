package com.amt.dflipflop.Controllers;
import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class StoreController {

    @GetMapping("/insert_items") // Uncomment if needed
    public String insertItems(Model model) {
        productService.insert(new Product("Produit 1", "Super produit 1", 3.5f, "shoes-img3"));
        productService.insert(new Product("Produit 2", "Super produit 2", 6.7f, "shoes-img9"));
        productService.insert(new Product("Produit 3", "Super produit 3", 33f, "shoes-img4"));
        productService.insert(new Product("Produit 4", "Super produit 4", 11.4f, "shoes-img9"));
        productService.insert(new Product("Produit 5", "Super produit 5", 42f, "shoes-img2"));
        productService.insert(new Product("Produit 6", "Super produit 6", 12f, "shoes-img9"));

        return "redirect:store";
    }


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
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping(path="/store/add-product") // Map ONLY POST Requests
    public @ResponseBody
    String addNewProduct (@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile, BindingResult result) {
        productService.insert(product);
        // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        if(result.hasErrors()){
            return "add-product";
        }
        // Add the product via a product service
        return "<head>\n" +
                "  <meta http-equiv=\"refresh\" content=\"0; URL=/store/add-product\" />\n" +
                "</head>";
    }

}
