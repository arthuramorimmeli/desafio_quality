package com.mercadolivre.wave4.desafio_quality.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
public class DistrictControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateNewPropertySuccess() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"bairro \",\n" +
                "    \"footageValue\": 10\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/district")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldCreateNewPropertyError() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"bairro \",\n" +
                "    \"footageValue\": 40\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/district")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
