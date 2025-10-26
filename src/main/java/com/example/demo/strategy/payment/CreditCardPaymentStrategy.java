package com.example.demo.strategy.payment;

public class CreditCardPaymentStrategy implements Payment {
    private static final double CREDIT_CARD_FEE_RATE = 0.03;

    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card details: " + maskCardNumber(paymentDetails));
        return amount > 0 && paymentDetails != null && !paymentDetails.trim().isEmpty();
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }

    @Override
    public double calculateFee(double amount) {
        return amount * CREDIT_CARD_FEE_RATE;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
