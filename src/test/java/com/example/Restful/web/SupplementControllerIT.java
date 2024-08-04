package com.example.Restful.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.example.Restful.model.dto.SupplementDTO;
import com.example.Restful.model.entity.Supplement;
import com.example.Restful.repository.SupplementRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class SupplementControllerIT {

    @Autowired
    private SupplementRepository supplementRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        var testEntity = testDTO();

        ResultActions result = mockMvc
                .perform(get("/supplement/{id}", testEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testEntity.getId().intValue())))
                .andExpect(jsonPath("$.description", is(testEntity.getDescription())))
                .andExpect(jsonPath("$.name", is(testEntity.getName())))
                .andExpect(jsonPath("$.photoUrl", is(testEntity.getPhotoUrl())));
    }

    @Test
    public void testDeleteSupplement() throws Exception {
        var testEntity = testDTO();

        mockMvc.perform(delete("/supplement/{id}", testEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
        Assertions.assertTrue(supplementRepository.findById(testEntity.getId()).isEmpty());
    }

    @Test
    public void testCreateSupplement() throws Exception {
        MvcResult result = mockMvc.perform(post("/supplement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {   
                            "name": "Test",
                            "description": "test description",
                            "caloriesPerServing": 123.0,
                            "photoUrl": "https://test/test"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        int id = JsonPath.read(body,"$.id");
        Optional<Supplement> createdSupplement = supplementRepository.findById((long) id);

        Assertions.assertTrue(createdSupplement.isPresent());
        Assertions.assertEquals("Test", createdSupplement.get().getName());
        Assertions.assertEquals("test description", createdSupplement.get().getDescription());
        Assertions.assertEquals(123.0, createdSupplement.get().getCaloriesPerServing());
        Assertions.assertEquals("https://test/test", createdSupplement.get().getPhotoUrl());

    }

    @Test
    public void testGetAllSupplements() throws Exception {
        var testEntityOne = testDTO();
        var testEntityTwo = testDTO();
        var testEntityThree = testDTO();
        var testEntityFour = testDTO();

        MvcResult result =  mockMvc.perform(get("/supplements")
                        .param("page", "0")
                        .param("size", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        List<Map<String, Object>> supplements = JsonPath.read(body  ,"$.content");
        Assertions.assertEquals(3, supplements.size());

        Map<String, Object> firstSupplement = supplements.getFirst();
        Assertions.assertEquals("Test", firstSupplement.get("name"));
        Assertions.assertEquals("test description", firstSupplement.get("description"));
        Assertions.assertEquals(100.0, firstSupplement.get("caloriesPerServing"));
        Assertions.assertEquals("https://test/test", firstSupplement.get("photoUrl"));

    }

    private Supplement testDTO(){
        Supplement supplement = new Supplement();
        supplement.setDescription("test description");
        supplement.setName("Test");
        supplement.setCaloriesPerServing(100.0);
        supplement.setPhotoUrl("https://test/test");
        return supplementRepository.save(supplement);
    }
}
