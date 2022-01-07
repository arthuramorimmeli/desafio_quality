package com.mercadolivre.wave4.desafio_quality.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateNewPropertySuccess() throws Exception {
        String payload = "{\n" +
                "\n" +
                "    \"name\": \"Casa 2\",\n" +
                "    \"district\": {\n" +
                "        \"name\": \"bairro \",\n" +
                "        \"footageValue\": 10\n" +
                "    },\n" +
                "\n" +
                "    \"rooms\": [\n" +
                "        {\n" +
                "            \"name\": \"Room 1\",\n" +
                "            \"width\": 15.0,\n" +
                "            \"length\": 23.5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Room 2\",\n" +
                "            \"width\": 25.0,\n" +
                "            \"length\": 23.5\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldCreateNewPropertyNameDownCaseError() throws Exception {
        String payload = "{\n" +
                "\n" +
                "    \"name\": \"casa 2\",\n" +
                "    \"district\": {\n" +
                "        \"name\": \"bairro \",\n" +
                "        \"footageValue\": 10\n" +
                "    },\n" +
                "\n" +
                "    \"rooms\": [\n" +
                "        {\n" +
                "            \"name\": \"Room 1\",\n" +
                "            \"width\": 15.0,\n" +
                "            \"length\": 23.5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Room 2\",\n" +
                "            \"width\": 25.0,\n" +
                "            \"length\": 23.5\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldGetListOfPropertiesSuccess() throws Exception {
        String payload = "{\n" +
                "\n" +
                "    \"name\": \"Casa 2\",\n" +
                "    \"district\": {\n" +
                "        \"name\": \"bairro \",\n" +
                "        \"footageValue\": 10\n" +
                "    },\n" +
                "\n" +
                "    \"rooms\": [\n" +
                "        {\n" +
                "            \"name\": \"Room 1\",\n" +
                "            \"width\": 15.0,\n" +
                "            \"length\": 23.5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Room 2\",\n" +
                "            \"width\": 25.0,\n" +
                "            \"length\": 23.5\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}";

        MvcResult res = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/property"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void shouldGetPropertiyByIdError() throws Exception {
        String payload = "{\n" +
                "\n" +
                "    \"name\": \"Casa 2\",\n" +
                "    \"district\": {\n" +
                "        \"name\": \"bairro \",\n" +
                "        \"footageValue\": 10\n" +
                "    },\n" +
                "\n" +
                "    \"rooms\": [\n" +
                "        {\n" +
                "            \"name\": \"Room 1\",\n" +
                "            \"width\": 15.0,\n" +
                "            \"length\": 23.5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Room 2\",\n" +
                "            \"width\": 25.0,\n" +
                "            \"length\": 23.5\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/property/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}
