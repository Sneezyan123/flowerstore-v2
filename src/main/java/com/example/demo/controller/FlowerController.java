package com.example.demo.controller;

import com.example.demo.model.Flower;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    @GetMapping
    public List<Flower> getAllFlowers() {
        return List.of(
            new Flower(1L, "Rose", "Red", 15.99, "Beautiful red rose"),
            new Flower(2L, "Tulip", "Yellow", 12.50, "Bright yellow tulip"),
            new Flower(3L, "Lily", "White", 18.75, "Elegant white lily"),
            new Flower(4L, "Sunflower", "Yellow", 8.99, "Cheerful sunflower"),
            new Flower(5L, "Orchid", "Purple", 25.00, "Exotic purple orchid")
        );
    }

    @GetMapping("/featured")
    public List<Flower> getFeaturedFlowers() {
        return List.of(
            new Flower(1L, "Rose", "Red", 15.99, "Beautiful red rose"),
            new Flower(3L, "Lily", "White", 18.75, "Elegant white lily"),
            new Flower(5L, "Orchid", "Purple", 25.00, "Exotic purple orchid")
        );
    }

    @GetMapping("/cheap")
    public List<Flower> getCheapFlowers() {
        return List.of(
            new Flower(4L, "Sunflower", "Yellow", 8.99, "Cheerful sunflower"),
            new Flower(2L, "Tulip", "Yellow", 12.50, "Bright yellow tulip")
        );
    }
}
