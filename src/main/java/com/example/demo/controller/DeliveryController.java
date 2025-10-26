package com.example.demo.controller;

import com.example.demo.service.DeliveryService;
import com.example.demo.strategy.delivery.DHLDeliveryStrategy;
import com.example.demo.strategy.delivery.PostDeliveryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/post")
    public Map<String, Object> schedulePostDelivery(@RequestBody Map<String, Object> request) {
        String address = request.get("address").toString();
        String dateStr = request.get("preferredDate").toString();
        LocalDateTime preferredDate = LocalDateTime.parse(dateStr);
        double orderAmount = Double.parseDouble(request.get("orderAmount").toString());
        
        PostDeliveryStrategy strategy = new PostDeliveryStrategy();
        boolean success = deliveryService.scheduleDelivery(strategy, address, preferredDate);
        double totalWithDelivery = deliveryService.calculateTotalWithDelivery(strategy, orderAmount);
        
        return Map.of(
            "success", success,
            "deliveryMethod", strategy.getDeliveryMethod(),
            "estimatedDays", strategy.getEstimatedDeliveryDays(),
            "deliveryCost", strategy.calculateDeliveryCost(orderAmount),
            "totalAmount", totalWithDelivery,
            "message", success ? "Delivery scheduled successfully" : "Delivery scheduling failed"
        );
    }

    @PostMapping("/dhl")
    public Map<String, Object> scheduleDHLDelivery(@RequestBody Map<String, Object> request) {
        String address = request.get("address").toString();
        String dateStr = request.get("preferredDate").toString();
        LocalDateTime preferredDate = LocalDateTime.parse(dateStr);
        double orderAmount = Double.parseDouble(request.get("orderAmount").toString());
        
        DHLDeliveryStrategy strategy = new DHLDeliveryStrategy();
        boolean success = deliveryService.scheduleDelivery(strategy, address, preferredDate);
        double totalWithDelivery = deliveryService.calculateTotalWithDelivery(strategy, orderAmount);
        
        return Map.of(
            "success", success,
            "deliveryMethod", strategy.getDeliveryMethod(),
            "estimatedDays", strategy.getEstimatedDeliveryDays(),
            "deliveryCost", strategy.calculateDeliveryCost(orderAmount),
            "totalAmount", totalWithDelivery,
            "message", success ? "Delivery scheduled successfully" : "Delivery scheduling failed"
        );
    }

    @GetMapping("/methods")
    public Map<String, Object> getDeliveryMethods() {
        return Map.of(
            "availableMethods", Map.of(
                "post", Map.of(
                    "name", "Post Delivery",
                    "cost", "$5.99",
                    "estimatedDays", 5,
                    "freeShippingThreshold", "$50.00",
                    "description", "Standard post delivery"
                ),
                "dhl", Map.of(
                    "name", "DHL Express",
                    "cost", "$12.99",
                    "estimatedDays", 2,
                    "freeShippingThreshold", "$100.00",
                    "description", "Express delivery with tracking"
                )
            )
        );
    }
}
