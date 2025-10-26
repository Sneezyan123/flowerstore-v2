package com.example.demo.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testFlower() {
        Flower flower = new Flower(1L, "Rose", "Red", 15.99, "Beautiful red rose");
        
        assertEquals(1L, flower.getId());
        assertEquals("Rose", flower.getName());
        assertEquals("Red", flower.getColor());
        assertEquals(15.99, flower.getPrice(), 0.01);
        assertEquals("Beautiful red rose", flower.getDescription());
    }

    @Test
    void testItem() {
        Flower flower = new Flower(1L, "Rose", "Red", 15.99, "Beautiful red rose");
        Item item = new Item(1L, flower, 2, 15.99);
        
        assertEquals(1L, item.getId());
        assertEquals(flower, item.getFlower());
        assertEquals(2, item.getQuantity());
        assertEquals(15.99, item.getUnitPrice(), 0.01);
        assertEquals(31.98, item.getTotalPrice(), 0.01);
    }

    @Test
    void testOrder() {
        Flower flower = new Flower(1L, "Rose", "Red", 15.99, "Beautiful red rose");
        Item item = new Item(1L, flower, 2, 15.99);
        Order order = new Order(1L, "John Doe", "john@example.com", "123 Main St", 
                               List.of(item), 31.98, OrderStatus.PENDING);
        
        assertEquals(1L, order.getId());
        assertEquals("John Doe", order.getCustomerName());
        assertEquals("john@example.com", order.getCustomerEmail());
        assertEquals("123 Main St", order.getCustomerAddress());
        assertEquals(1, order.getItems().size());
        assertEquals(31.98, order.getTotalAmount(), 0.01);
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertNotNull(order.getOrderDate());
    }

    @Test
    void testOrderStatus() {
        assertEquals("PENDING", OrderStatus.PENDING.name());
        assertEquals("CONFIRMED", OrderStatus.CONFIRMED.name());
        assertEquals("PROCESSING", OrderStatus.PROCESSING.name());
        assertEquals("SHIPPED", OrderStatus.SHIPPED.name());
        assertEquals("DELIVERED", OrderStatus.DELIVERED.name());
        assertEquals("CANCELLED", OrderStatus.CANCELLED.name());
    }
}
