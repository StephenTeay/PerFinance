# PerFina - Personalized Finance Manager

A Spring Boot application for managing personal finances with built-in pagination support.

## Getting Started

### Prerequisites
- Java Development Kit (JDK)
- Maven
- An IDE (IntelliJ IDEA recommended) or terminal access

### Installation

1. **Clone the repository** into your preferred directory

2. **Install dependencies** using one of these methods:
    - **IntelliJ IDEA**: Maven will automatically resolve dependencies
    - **Command line**: Run `mvn install` from the project root

3. **Configure the server port** (optional)
    - Open `application.properties`
    - Modify the `server.port` property to your preferred port (default: 8080)

### Running the Application

Choose one of these methods:

**Option 1: Command Line**
```bash
./mvnw spring-boot:run
```

**Option 2: IDE**
- Open `src/main/java/com/teay/finance/FinanceApplication.java`
- Click the Run/Play button

### Testing the API

Once the application is running, access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui/index.html#/
```

Use the interactive interface to test all available endpoints.