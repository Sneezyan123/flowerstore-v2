package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasicFunctionalityTest {

    @Test
    void testBasicMath() {
        assertEquals(4, 2 + 2);
        assertTrue(10 > 5);
        assertFalse(3 > 5);
    }

    @Test
    void testStringOperations() {
        String hello = "Hello";
        String world = "World";
        assertEquals("Hello World", hello + " " + world);
    }

    @Test
    void testListOperations() {
        var list = java.util.List.of("a", "b", "c");
        assertEquals(3, list.size());
        assertTrue(list.contains("b"));
    }
}
