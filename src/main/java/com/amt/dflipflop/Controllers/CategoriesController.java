package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.Category;
import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.nio.file.*;

@Controller
public class CategoriesController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String displayCategoriesAndForm(Model model) {
        ArrayList<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "categories";
    }

    /**
     * @param category The category get from the form (front-end)
     * @return The redirection to a page
     * @throws IOException If write fail
     */
    @PostMapping(path="/categories/add-category") // Map ONLY POST Requests
    public String addNewCategory (@ModelAttribute Category category) throws IOException {


        if(categoryService.categoryExists(category.getName())){
            return "redirect:/categories";
        }

        // Add the category via a category service
        categoryService.insert(category);
        return "redirect:/categories";
    }

    /**
     * Called with /categories/remove?id=N
     * @return The redirection to a page
     * @throws IOException If suppress fail
     */
    @GetMapping(path="/categories/remove") // Map ONLY POST Requests
    public
    String removeCategory (@RequestParam(value = "id") Integer id) throws IOException {

        // Add the category via a category service
        categoryService.remove(id);

        return "redirect:/categories";
    }

}

