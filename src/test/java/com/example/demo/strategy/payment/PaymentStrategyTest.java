package com.example.demo.strategy.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PaymentStrategyTest {

    private CreditCardPaymentStrategy creditCardStrategy;
    private PayPalPaymentStrategy payPalStrategy;

    @BeforeEach
    void setUp() {
        creditCardStrategy = new CreditCardPaymentStrategy();
        payPalStrategy = new PayPalPaymentStrategy();
    }

    @Test
    void testCreditCardPaymentSuccess() {
        double amount = 100.0;
        String cardNumber = "1234567890123456";

        boolean result = creditCardStrategy.processPayment(amount, cardNumber);
        assertTrue(result);
        assertEquals("Credit Card", creditCardStrategy.getPaymentMethod());
    }

    @Test
    void testCreditCardPaymentFailure() {
        double amount = 0.0;
        String cardNumber = "1234567890123456";
        boolean result = creditCardStrategy.processPayment(amount, cardNumber);
        assertFalse(result);
    }

    @Test
    void testCreditCardFeeCalculation() {
        double amount = 100.0;

        double fee = creditCardStrategy.calculateFee(amount);
        assertEquals(3.0, fee, 0.01);
    }

    @Test
    void testPayPalPaymentSuccess() {
        double amount = 100.0;
        String email = "test@example.com";
        boolean result = payPalStrategy.processPayment(amount, email);
        assertTrue(result);
        assertEquals("PayPal", payPalStrategy.getPaymentMethod());
    }

    @Test
    void testPayPalPaymentFailure() {
        double amount = 100.0;
        String email = "invalid-email";
        boolean result = payPalStrategy.processPayment(amount, email);
        assertFalse(result);
    }

    @Test
    void testPayPalFeeCalculation() {
        double amount = 100.0;
        double fee = payPalStrategy.calculateFee(amount);
        assertEquals(3.2, fee, 0.01);
    }
}
