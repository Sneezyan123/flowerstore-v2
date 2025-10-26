# Spring Boot Demo Application

This is a Spring Boot web application that demonstrates various architectural patterns and features including:

- **Web Application Layers**: API Layer, Service Layer, Data Access Layer
- **Strategy Pattern**: Payment and Delivery strategies
- **RESTful APIs**: Multiple endpoints for different functionalities
- **Testing**: Comprehensive unit and integration tests
- **CI/CD**: GitHub Actions workflow for automated testing

## Features

### Models
- **Flower**: Represents flower products with properties like name, color, price, and description
- **Order**: Represents customer orders with items, customer information, and status
- **Item**: Represents individual items in an order (contains Flower and quantity)

### Payment Strategies
- **CreditCardPaymentStrategy**: Processes credit card payments with 3% fee
- **PayPalPaymentStrategy**: Processes PayPal payments with 2.9% + $0.30 fee

### Delivery Strategies
- **PostDeliveryStrategy**: Standard post delivery (5 days, $5.99, free over $50)
- **DHLDeliveryStrategy**: Express delivery (2 days, $12.99, free over $100)

### API Endpoints

#### General
- `GET /` - Welcome message
- `GET /api/status` - Application status
- `GET /api/info` - Application information
- `GET /api/items` - Sample items list

#### Flowers
- `GET /api/flowers` - Get all flowers
- `GET /api/flowers/featured` - Get featured flowers
- `GET /api/flowers/cheap` - Get cheap flowers

#### Payment
- `POST /api/payment/credit-card` - Process credit card payment
- `POST /api/payment/paypal` - Process PayPal payment
- `GET /api/payment/methods` - Get available payment methods

#### Delivery
- `POST /api/delivery/post` - Schedule post delivery
- `POST /api/delivery/dhl` - Schedule DHL delivery
- `GET /api/delivery/methods` - Get available delivery methods

## Prerequisites

- Java 17 or later
- Gradle 8.5 or later

## Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

3. **Access the application**:
   - Application will be available at `http://localhost:8080`
   - Try the endpoints:
     - `http://localhost:8080/api/flowers`
     - `http://localhost:8080/api/payment/methods`
     - `http://localhost:8080/api/delivery/methods`

## Running Tests

```bash
./gradlew test
```

## Testing API Endpoints

### Payment Example
```bash
curl -X POST http://localhost:8080/api/payment/credit-card \
  -H "Content-Type: application/json" \
  -d '{"amount": 100.0, "cardNumber": "1234567890123456"}'
```

### Delivery Example
```bash
curl -X POST http://localhost:8080/api/delivery/dhl \
  -H "Content-Type: application/json" \
  -d '{"address": "123 Main St", "preferredDate": "2024-01-15T10:00:00", "orderAmount": 80.0}'
```

## Project Structure

```
src/
├── main/java/com/example/demo/
│   ├── controller/          # REST controllers (API Layer)
│   │   ├── DemoApplication.java
│   │   ├── FlowerController.java
│   │   ├── PaymentController.java
│   │   └── DeliveryController.java
│   ├── model/              # Domain models
│   │   ├── Flower.java
│   │   ├── Order.java
│   │   ├── Item.java
│   │   └── OrderStatus.java
│   ├── service/            # Business logic (Service Layer)
│   │   ├── PaymentService.java
│   │   └── DeliveryService.java
│   └── strategy/           # Strategy pattern implementations
│       ├── payment/
│       │   ├── Payment.java
│       │   ├── CreditCardPaymentStrategy.java
│       │   └── PayPalPaymentStrategy.java
│       └── delivery/
│           ├── Delivery.java
│           ├── PostDeliveryStrategy.java
│           └── DHLDeliveryStrategy.java
└── test/java/com/example/demo/
    ├── controller/          # Integration tests
    ├── strategy/           # Unit tests
    └── DemoApplicationTests.java
```

## Architecture Patterns

### Strategy Pattern
The application uses the Strategy pattern for payment and delivery processing, allowing easy extension with new payment methods or delivery options.

### Layered Architecture
- **API Layer**: Controllers handle HTTP requests/responses
- **Service Layer**: Business logic and orchestration
- **Model Layer**: Domain objects and data structures

## CI/CD

The project includes GitHub Actions workflows:

### Main Workflow (`.github/workflows/ci.yml`)
- Runs on every push and pull request
- Sets up Java 17 environment
- Caches Gradle dependencies
- Runs all tests
- Uploads test results as artifacts

### Alternative Workflow (`.github/workflows/ci-with-reports.yml`)
- Same as main workflow but includes test report generation
- Requires proper repository permissions for check runs
- Use this if you want detailed test reports in GitHub

### Test Status
- **Local**: Requires Java 17 (Spring Boot 3.2.0 compatibility)
- **CI**: Runs successfully with Java 17 in GitHub Actions
- **Test Count**: 20+ tests covering all functionality

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request
