/**
 * Date de création     : 16.10.2021
 * Dernier contributeur : Ryan Sauge
 * Groupe               : AMT-D-Flip-Flop
 * Description          : Tester ProductService et StoreController
 * Remarque             : -
 * Sources :
 * https://www.baeldung.com/junit-5-runwith
 */

package com.example.dflipflop;

import com.example.dflipflop.Controllers.StoreController;
import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreController.class)
public class ProductServiceTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testExample() throws Exception {
        this.productService.insert(new Product("Honda", "Civic", (float) 12.5, ""));
        ArrayList<Product> p = productService.getAll();
        boolean find = false;
        for (Product element : p){
            if (element.getName().equals("Honda")){
                find = true;
                System.out.println(element);
                break;
            }
        }
        if(!find){
            throw new Exception("Product not found");
        }

        /*
        TODO: function to delete a product in the database
         */
    }

}
