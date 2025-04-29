package com.example.lab2_sbmysql.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LocationController.class)
@WithMockUser(username = "user", roles = "")
class LocationControllerTest {
    @MockitoBean
    LocationController locationController;

    @MockitoBean
    LocationService locationService;

    @Autowired
    private MockMvc mockMvc;

    LocationDto locationDto;

    @Test
    void getAllLocations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllLPublicLocations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/public"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getPublicLocationByCoordinate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/public/63.45/15.11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllPublicLocationsByCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/public/category/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllLocationsByCoordinate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/public/108.04/17.763/10000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void createLocation() throws Exception {
        String location = """
                {
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/locations").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(location))
                .andExpect(status().isCreated());
    }

    @Test
    void updateLocation() throws Exception {
        String updateLocation = """
                {
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/locations/3").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateLocation))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteLocation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/locations/3").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
