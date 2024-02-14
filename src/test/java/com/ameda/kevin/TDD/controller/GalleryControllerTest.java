package com.ameda.kevin.TDD.controller;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.controller
*
*/

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GalleryController.class)
@AutoConfigureMockMvc
public class GalleryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void shouldFindAllGallery() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/galleries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.
                        jsonPath("$", Matchers.hasSize(3)));  //$ root
    }
}
