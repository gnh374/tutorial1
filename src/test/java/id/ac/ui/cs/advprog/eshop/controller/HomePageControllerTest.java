package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomePageController.class)
public class HomePageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void HomePageControllerTest() throws Exception{
        mockMvc.perform(get(""))
                .andExpect(status().isOk()) // Verify HTTP status is OK
                .andExpect(view().name("HomePage")); // Verify view name is "createProduct"
    }


}
