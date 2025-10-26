package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void testGetPaymentMethods() throws Exception {
        mockMvc.perform(get("/api/payment/methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableMethods.creditCard.name").value("Credit Card"))
                .andExpect(jsonPath("$.availableMethods.paypal.name").value("PayPal"));
    }
}
