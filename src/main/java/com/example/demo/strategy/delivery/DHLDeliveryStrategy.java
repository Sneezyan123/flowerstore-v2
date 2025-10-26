package com.example.demo.strategy.delivery;

import java.time.LocalDateTime;

public class DHLDeliveryStrategy implements Delivery {
    private static final double DHL_DELIVERY_COST = 12.99;
    private static final double FREE_SHIPPING_THRESHOLD = 100.00;

    @Override
    public boolean scheduleDelivery(String address, LocalDateTime preferredDate) {
        System.out.println("Scheduling DHL delivery to: " + address);
        System.out.println("Preferred delivery date: " + preferredDate);
        System.out.println("Generating DHL tracking number...");
        return address != null && !address.trim().isEmpty() && 
               preferredDate.isAfter(LocalDateTime.now().plusDays(1));
    }

    @Override
    public double calculateDeliveryCost(double orderAmount) {
        if (orderAmount >= FREE_SHIPPING_THRESHOLD) {
            return 0.0;
        }
        return DHL_DELIVERY_COST;
    }

    @Override
    public int getEstimatedDeliveryDays() {
        return 2;
    }

    @Override
    public String getDeliveryMethod() {
        return "DHL Express";
    }
}
