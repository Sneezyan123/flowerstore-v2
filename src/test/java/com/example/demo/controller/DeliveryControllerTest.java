package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostDelivery() throws Exception {
        Map<String, Object> request = Map.of(
            "address", "123 Main St, City, State",
            "preferredDate", LocalDateTime.now().plusDays(2).toString(),
            "orderAmount", 30.0
        );

        mockMvc.perform(post("/api/delivery/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.deliveryMethod").value("Post Delivery"))
                .andExpect(jsonPath("$.estimatedDays").value(5))
                .andExpect(jsonPath("$.deliveryCost").value(5.99));
    }

    @Test
    void testDHLDelivery() throws Exception {
        Map<String, Object> request = Map.of(
            "address", "456 Oak Ave, City, State",
            "preferredDate", LocalDateTime.now().plusDays(2).toString(),
            "orderAmount", 80.0
        );

        mockMvc.perform(post("/api/delivery/dhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.deliveryMethod").value("DHL Express"))
                .andExpect(jsonPath("$.estimatedDays").value(2))
                .andExpect(jsonPath("$.deliveryCost").value(12.99));
    }

    @Test
    void testGetDeliveryMethods() throws Exception {
        mockMvc.perform(get("/api/delivery/methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableMethods.post.name").value("Post Delivery"))
                .andExpect(jsonPath("$.availableMethods.dhl.name").value("DHL Express"));
    }
}
