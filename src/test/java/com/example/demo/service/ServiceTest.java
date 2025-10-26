package com.example.demo.service;

import com.example.demo.strategy.payment.CreditCardPaymentStrategy;
import com.example.demo.strategy.payment.PayPalPaymentStrategy;
import com.example.demo.strategy.delivery.PostDeliveryStrategy;
import com.example.demo.strategy.delivery.DHLDeliveryStrategy;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void testPaymentService() {
        PaymentService paymentService = new PaymentService();
        CreditCardPaymentStrategy strategy = new CreditCardPaymentStrategy();
        
        assertTrue(paymentService.processPayment(strategy, 100.0, "1234567890123456"));
        assertEquals(103.0, paymentService.calculateTotalWithFee(strategy, 100.0), 0.01);
    }

    @Test
    void testDeliveryService() {
        DeliveryService deliveryService = new DeliveryService();
        PostDeliveryStrategy strategy = new PostDeliveryStrategy();
        
        assertTrue(deliveryService.scheduleDelivery(strategy, "123 Main St", LocalDateTime.now().plusDays(2)));
        assertEquals(35.99, deliveryService.calculateTotalWithDelivery(strategy, 30.0), 0.01);
    }
}
