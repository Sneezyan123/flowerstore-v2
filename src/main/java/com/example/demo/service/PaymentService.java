package com.example.demo.service;

import com.example.demo.strategy.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    
    public boolean processPayment(Payment paymentStrategy, double amount, String paymentDetails) {
        System.out.println("Using payment method: " + paymentStrategy.getPaymentMethod());
        double fee = paymentStrategy.calculateFee(amount);
        System.out.println("Payment fee: $" + String.format("%.2f", fee));
        
        return paymentStrategy.processPayment(amount, paymentDetails);
    }
    
    public double calculateTotalWithFee(Payment paymentStrategy, double amount) {
        return amount + paymentStrategy.calculateFee(amount);
    }
}
