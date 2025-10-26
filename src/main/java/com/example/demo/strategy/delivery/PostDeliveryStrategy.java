package com.example.demo.strategy.delivery;

import java.time.LocalDateTime;

public class PostDeliveryStrategy implements Delivery {
    private static final double POST_DELIVERY_COST = 5.99;
    private static final double FREE_SHIPPING_THRESHOLD = 50.00;

    @Override
    public boolean scheduleDelivery(String address, LocalDateTime preferredDate) {
        System.out.println("Scheduling post delivery to: " + address);
        System.out.println("Preferred delivery date: " + preferredDate);
        return address != null && !address.trim().isEmpty() && 
               preferredDate.isAfter(LocalDateTime.now().plusDays(1));
    }

    @Override
    public double calculateDeliveryCost(double orderAmount) {
        if (orderAmount >= FREE_SHIPPING_THRESHOLD) {
            return 0.0;
        }
        return POST_DELIVERY_COST;
    }

    @Override
    public int getEstimatedDeliveryDays() {
        return 5;
    }

    @Override
    public String getDeliveryMethod() {
        return "Post Delivery";
    }
}
