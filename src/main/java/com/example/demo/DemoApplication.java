package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Welcome to Demo Application!";
	}

	@GetMapping("/api/status")
	public Map<String, String> getStatus() {
		return Map.of("status", "running", "message", "Application is healthy");
	}

	@GetMapping("/api/info")
	public Map<String, Object> getInfo() {
		return Map.of(
			"application", "Demo Application",
			"version", "1.0.0",
			"framework", "Spring Boot"
		);
	}

	@GetMapping("/api/items")
	public List<String> getItems() {
		return List.of("Item 1", "Item 2", "Item 3");
	}
}
