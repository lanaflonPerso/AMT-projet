package com.amt.dflipflop.Controllers;

import com.amt.dflipflop.Entities.User;
import com.amt.dflipflop.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
class Person {
    private Integer id;
    private String name;

    // standard constructor, getters, setters
}
interface PersonService {

    public Person saveUpdatePerson(Person person);
    public Person findPersonById(Integer id);
}
@Controller
public class UserController {

    @GetMapping("/user/orders")
    public String getUserOrders(Model model) {
        return "orders";
    }

    @GetMapping("/user/addresses")
    public String getAddressesPage(Model model) {
        return "addresses";
    }

    @GetMapping("/user/add-address")
    public String getAddAddressPage(Model model) {
        return "add-address";
    }

    @PostMapping(path="/user/add-address") // Map ONLY POST Requests
    public @ResponseBody
    String addNewAddress () {
        return "add-address";
    }

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "authentification/users";
    }



    @GetMapping("/login")
    public String login(Model model) {
        //model.addAttribute("name", name);
        return "authentification/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "authentification/signup_form.html";
    }

    /*
    https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
    https://devstory.net/11647/spring-boot-restful-client-avec-resttemplate#a13889219
     */
    @PostMapping("/process_register")
    public String processRegister(User user) {
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        */
        //https://www.baeldung.com/spring-resttemplate-post-json
        //String createPersonUrl = "http://mobile.iict.ch/api/json";");
        String createPersonUrl = "http://localhost:3000/accounts/register";

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(user);

        // Send request with POST method.
        ResponseEntity<User> result
                = restTemplate.postForEntity(createPersonUrl, requestBody, User.class);

        System.out.println("Status code:" + result.getStatusCode());

        // Code = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
            User e = result.getBody();
            System.out.println("(Client Side) Employee Created: "+ e.getUsername());
        }

        return "authentification/register_success";
    }
}
