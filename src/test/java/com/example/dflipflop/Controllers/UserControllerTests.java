package com.example.dflipflop.Controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.dflipflop.Controllers.UserController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldDisplayUserOrders() throws Exception {
        this.mockMvc.perform(get("/user/orders")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldDisplayUserAddresses() throws Exception {
        this.mockMvc.perform(get("/user/addresses")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldDisplayUserAddAddress() throws Exception {
        this.mockMvc.perform(get("/user/add-address")).andDo(print()).andExpect(status().isOk());
    }

}