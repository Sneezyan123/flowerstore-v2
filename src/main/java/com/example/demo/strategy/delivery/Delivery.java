package com.example.demo.strategy.delivery;

import java.time.LocalDateTime;

public interface Delivery {
    boolean scheduleDelivery(String address, LocalDateTime preferredDate);
    double calculateDeliveryCost(double orderAmount);
    int getEstimatedDeliveryDays();
    String getDeliveryMethod();
}
