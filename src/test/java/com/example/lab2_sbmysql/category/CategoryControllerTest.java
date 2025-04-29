package com.example.lab2_sbmysql.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
@WithMockUser(username = "user", roles = "")
class CategoryControllerTest {
    @MockitoBean
    CategoryController categoryController;

    @MockitoBean
    CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    CategoryDto categoryDto;

    @Test
    void getAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createCategory() throws Exception {
        String location = """
                {
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/categories").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(location))
                .andExpect(status().isCreated());
    }
}
