package com.example.demo.service;

import com.example.demo.strategy.delivery.Delivery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {
    
    public boolean scheduleDelivery(Delivery deliveryStrategy, String address, LocalDateTime preferredDate) {
        System.out.println("Using delivery method: " + deliveryStrategy.getDeliveryMethod());
        System.out.println("Estimated delivery time: " + deliveryStrategy.getEstimatedDeliveryDays() + " days");
        
        return deliveryStrategy.scheduleDelivery(address, preferredDate);
    }
    
    public double calculateTotalWithDelivery(Delivery deliveryStrategy, double orderAmount) {
        double deliveryCost = deliveryStrategy.calculateDeliveryCost(orderAmount);
        System.out.println("Delivery cost: $" + String.format("%.2f", deliveryCost));
        
        return orderAmount + deliveryCost;
    }
}
