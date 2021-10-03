package com.example.dflipflop.Controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.dflipflop.Controllers.StoreController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StoreController.class)
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldDisplayStore() throws Exception {
        this.mockMvc.perform(get("/store")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldDisplayProduct() throws Exception {
        this.mockMvc.perform(get("/store/product/0")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldDisplayAddProduct() throws Exception {
        this.mockMvc.perform(get("/store/add-product")).andDo(print()).andExpect(status().isOk());
    }
}