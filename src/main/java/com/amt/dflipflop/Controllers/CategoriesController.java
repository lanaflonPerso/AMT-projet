package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.Category;
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

    @GetMapping("/categories/")
    public String displayCategoriesAndForm(Model model) {
        ArrayList<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);


        model.addAttribute("categories", categories);
        return "categories";
    }

    /**
     *
     * @param category The category get from the form (front-end)
     * @param multipartFile The stream for the picture
     * @param result State of the request
     * @return The redirection to a page
     * @throws IOException If write fail
     */
    @PostMapping(path="/categories/add-category") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCategory (@ModelAttribute("category") Category category, @RequestParam("image") MultipartFile multipartFile, BindingResult result) throws IOException {

        // Add the category via a category service
        categoryService.insert(category);

        if(result.hasErrors()){
            return "categories/add-category";
        }

        return "<head>\n" +
                "  <meta http-equiv=\"refresh\" content=\"0; URL=/categories\" />\n" +
                "</head>";
    }

}

