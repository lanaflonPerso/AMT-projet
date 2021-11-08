/**
 * Date de création     : 16.10.2021
 * Dernier contributeur : Ryan Sauge
 * Groupe               : AMT-D-Flip-Flop
 * Description          : Tester ProductService et StoreController
 * Remarque             : -
 * Sources :
 * https://www.baeldung.com/junit-5-runwith
 * https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
 */

package com.example.dflipflop;

import com.example.dflipflop.Controllers.StoreController;
import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import  org.junit.jupiter.api.Assertions;




import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductServiceTests {

    /*@Autowired
    private MockMvc mvc;*/

    @Autowired
    private TestEntityManager entityManager;


    @MockBean
    private ProductService productService;

    @Test
    public void testExample() throws Exception {
        Product p = new Product("Honda", "Civic", (float) 12.5, "");
        Product savedUser = this.productService.insert(p);
        //ArrayList<Product> p = productService.getAll();
        //boolean find = false;

        Assertions.assertNotNull(savedUser);
        Product existUser = entityManager.find(Product.class, savedUser.getId());
        Assertions.assertNotNull(existUser);

        /*for (Product element : p){
            if (element.getName().equals("Honda")){
                find = true;
                System.out.println(element);
                break;
            }
        }*/
            //throw new Exception("Product not found");

        /*
        TODO: function to delete a product in the database
         */
    }

}
