package com.example.demo.strategy.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimplePaymentStrategyTest {

    @Test
    void testCreditCardPaymentStrategy() {
        CreditCardPaymentStrategy strategy = new CreditCardPaymentStrategy();
        
        assertTrue(strategy.processPayment(100.0, "1234567890123456"));
        assertEquals("Credit Card", strategy.getPaymentMethod());
        assertEquals(3.0, strategy.calculateFee(100.0), 0.01);
    }

    @Test
    void testPayPalPaymentStrategy() {
        PayPalPaymentStrategy strategy = new PayPalPaymentStrategy();
        
        assertTrue(strategy.processPayment(100.0, "test@example.com"));
        assertEquals("PayPal", strategy.getPaymentMethod());
        assertEquals(3.2, strategy.calculateFee(100.0), 0.01);
    }
}
