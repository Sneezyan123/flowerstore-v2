package com.example.demo.strategy.delivery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryStrategyTest {

    private PostDeliveryStrategy postStrategy;
    private DHLDeliveryStrategy dhlStrategy;

    @BeforeEach
    void setUp() {
        postStrategy = new PostDeliveryStrategy();
        dhlStrategy = new DHLDeliveryStrategy();
    }

    @Test
    void testPostDeliverySuccess() {
        // Given
        String address = "123 Main St, City, State";
        LocalDateTime preferredDate = LocalDateTime.now().plusDays(2);

        // When
        boolean result = postStrategy.scheduleDelivery(address, preferredDate);

        // Then
        assertTrue(result);
        assertEquals("Post Delivery", postStrategy.getDeliveryMethod());
        assertEquals(5, postStrategy.getEstimatedDeliveryDays());
    }

    @Test
    void testPostDeliveryFailure() {
        String address = "";
        LocalDateTime preferredDate = LocalDateTime.now().plusDays(2);

        boolean result = postStrategy.scheduleDelivery(address, preferredDate);
        assertFalse(result);
    }

    @Test
    void testPostDeliveryCostWithFreeShipping() {
        double orderAmount = 60.0; // Above $50 threshold
        double cost = postStrategy.calculateDeliveryCost(orderAmount);

        assertEquals(0.0, cost, 0.01);
    }

    @Test
    void testPostDeliveryCostWithoutFreeShipping() {
        double orderAmount = 30.0;

        double cost = postStrategy.calculateDeliveryCost(orderAmount);
        assertEquals(5.99, cost, 0.01);
    }

    @Test
    void testDHLDeliverySuccess() {
        String address = "456 Oak Ave, City, State";
        LocalDateTime preferredDate = LocalDateTime.now().plusDays(2);


        boolean result = dhlStrategy.scheduleDelivery(address, preferredDate);

        assertTrue(result);
        assertEquals("DHL Express", dhlStrategy.getDeliveryMethod());
        assertEquals(2, dhlStrategy.getEstimatedDeliveryDays());
    }

    @Test
    void testDHLDeliveryFailure() {
        String address = null;
        LocalDateTime preferredDate = LocalDateTime.now().plusDays(2);

        boolean result = dhlStrategy.scheduleDelivery(address, preferredDate);

        assertFalse(result);
    }

    @Test
    void testDHLDeliveryCostWithFreeShipping() {
        double orderAmount = 120.0; 

        double cost = dhlStrategy.calculateDeliveryCost(orderAmount);
        assertEquals(0.0, cost, 0.01);
    }

    @Test
    void testDHLDeliveryCostWithoutFreeShipping() {
        double orderAmount = 80.0;

        double cost = dhlStrategy.calculateDeliveryCost(orderAmount);


        assertEquals(12.99, cost, 0.01);
    }
}
