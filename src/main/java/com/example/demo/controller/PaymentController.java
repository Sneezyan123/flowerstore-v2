package com.example.demo.controller;

import com.example.demo.service.PaymentService;
import com.example.demo.strategy.payment.CreditCardPaymentStrategy;
import com.example.demo.strategy.payment.PayPalPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/credit-card")
    public Map<String, Object> processCreditCardPayment(@RequestBody Map<String, Object> request) {
        double amount = Double.parseDouble(request.get("amount").toString());
        String cardNumber = request.get("cardNumber").toString();
        
        CreditCardPaymentStrategy strategy = new CreditCardPaymentStrategy();
        boolean success = paymentService.processPayment(strategy, amount, cardNumber);
        double totalWithFee = paymentService.calculateTotalWithFee(strategy, amount);
        
        return Map.of(
            "success", success,
            "paymentMethod", strategy.getPaymentMethod(),
            "originalAmount", amount,
            "fee", strategy.calculateFee(amount),
            "totalAmount", totalWithFee,
            "message", success ? "Payment processed successfully" : "Payment failed"
        );
    }

    @PostMapping("/paypal")
    public Map<String, Object> processPayPalPayment(@RequestBody Map<String, Object> request) {
        double amount = Double.parseDouble(request.get("amount").toString());
        String email = request.get("email").toString();
        
        PayPalPaymentStrategy strategy = new PayPalPaymentStrategy();
        boolean success = paymentService.processPayment(strategy, amount, email);
        double totalWithFee = paymentService.calculateTotalWithFee(strategy, amount);
        
        return Map.of(
            "success", success,
            "paymentMethod", strategy.getPaymentMethod(),
            "originalAmount", amount,
            "fee", strategy.calculateFee(amount),
            "totalAmount", totalWithFee,
            "message", success ? "Payment processed successfully" : "Payment failed"
        );
    }

    @GetMapping("/methods")
    public Map<String, Object> getPaymentMethods() {
        return Map.of(
            "availableMethods", Map.of(
                "creditCard", Map.of(
                    "name", "Credit Card",
                    "feeRate", "3%",
                    "description", "Process payment using credit card"
                ),
                "paypal", Map.of(
                    "name", "PayPal",
                    "feeRate", "2.9% + $0.30",
                    "description", "Process payment using PayPal"
                )
            )
        );
    }
}
