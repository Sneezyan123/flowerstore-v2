package com.example.demo.strategy.payment;

public class PayPalPaymentStrategy implements Payment {
    private static final double PAYPAL_FEE_RATE = 0.029;
    private static final double PAYPAL_FIXED_FEE = 0.30;
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("PayPal email: " + maskEmail(paymentDetails));
        return amount > 0 && paymentDetails != null && paymentDetails.contains("@");
    }

    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }

    @Override
    public double calculateFee(double amount) {
        return (amount * PAYPAL_FEE_RATE) + PAYPAL_FIXED_FEE;
    }

    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "****@****.com";
        }
        String[] parts = email.split("@");
        if (parts[0].length() <= 2) {
            return "**@" + parts[1];
        }
        return parts[0].substring(0, 2) + "***@" + parts[1];
    }
}
