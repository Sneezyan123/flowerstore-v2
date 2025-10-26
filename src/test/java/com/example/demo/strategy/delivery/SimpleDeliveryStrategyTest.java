package com.example.demo.strategy.delivery;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class SimpleDeliveryStrategyTest {

    @Test
    void testPostDeliveryStrategy() {
        PostDeliveryStrategy strategy = new PostDeliveryStrategy();
        
        assertTrue(strategy.scheduleDelivery("123 Main St", LocalDateTime.now().plusDays(2)));
        assertEquals("Post Delivery", strategy.getDeliveryMethod());
        assertEquals(5, strategy.getEstimatedDeliveryDays());
        assertEquals(5.99, strategy.calculateDeliveryCost(30.0), 0.01);
        assertEquals(0.0, strategy.calculateDeliveryCost(60.0), 0.01);
    }

    @Test
    void testDHLDeliveryStrategy() {
        DHLDeliveryStrategy strategy = new DHLDeliveryStrategy();
        
        assertTrue(strategy.scheduleDelivery("456 Oak Ave", LocalDateTime.now().plusDays(2)));
        assertEquals("DHL Express", strategy.getDeliveryMethod());
        assertEquals(2, strategy.getEstimatedDeliveryDays());
        assertEquals(12.99, strategy.calculateDeliveryCost(80.0), 0.01);
        assertEquals(0.0, strategy.calculateDeliveryCost(120.0), 0.01);
    }
}
