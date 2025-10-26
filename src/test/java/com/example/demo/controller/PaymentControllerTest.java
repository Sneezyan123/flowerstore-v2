package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
class PaymentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetPaymentMethods() throws Exception {
        mockMvc.perform(get("/api/payment/methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableMethods.creditCard.name").value("Credit Card"))
                .andExpect(jsonPath("$.availableMethods.paypal.name").value("PayPal"));
    }

    @Test
    void testCreditCardPayment() throws Exception {
        Map<String, Object> request = Map.of(
            "amount", 100.0,
            "cardNumber", "1234567890123456"
        );

        mockMvc.perform(post("/api/payment/credit-card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"))
                .andExpect(jsonPath("$.originalAmount").value(100.0))
                .andExpect(jsonPath("$.fee").value(3.0));
    }

    @Test
    void testPayPalPayment() throws Exception {
        Map<String, Object> request = Map.of(
            "amount", 100.0,
            "email", "test@example.com"
        );

        mockMvc.perform(post("/api/payment/paypal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.paymentMethod").value("PayPal"))
                .andExpect(jsonPath("$.originalAmount").value(100.0))
                .andExpect(jsonPath("$.fee").value(3.2));
    }
}
