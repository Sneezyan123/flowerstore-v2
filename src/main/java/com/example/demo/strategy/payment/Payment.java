package com.example.demo.strategy.payment;

public interface Payment {
    boolean processPayment(double amount, String paymentDetails);
    String getPaymentMethod();
    double calculateFee(double amount);
}
