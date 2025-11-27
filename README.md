# Hex Microservice Blueprint

A template repository for building microservices using **Hexagonal Architecture** and **CQRS**.  
Provides a clean separation between domain, application logic, and infrastructure adapters, enabling scalable, maintainable, and framework-agnostic service development.

---

## 🧱 Architecture Overview

This template implements **Hexagonal Architecture** combined with **CQRS**:

### **Domain Layer**
- Pure business rules.
- Contains domain entities, aggregates, value objects, and domain services.
- No framework dependencies.

### **Application Layer**
Implements use cases through **CQRS**:
- **Command** — state-changing operations.
- **Query** — read-only operations.  

Contains:
- Ports (in/out)
- Application services  
- DTO models used only inside the application layer  

### **Infrastructure Layer**
- Framework-specific and external integrations.
- Contains adapters:
  - **Inbound adapters** (REST controllers, messaging consumers, CLI, etc.)
  - **Outbound adapters** (database repositories, external APIs, messaging publishers)
- Configuration (OpenAPI, Spring Boot config, monitoring, etc.)  

This approach results in a flexible and testable microservice architecture with minimal coupling.

---

## 📁 Suggested Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.hexblueprint
│   │   │       ├── application
│   │   │       │   ├── command
│   │   │       │   │   ├── model
│   │   │       │   │   ├── ports
│   │   │       │   │   └── service
│   │   │       │   └── query
│   │   │       │       ├── model
│   │   │       │       ├── ports
│   │   │       │       └── service
│   │   │       ├── domain
│   │   │       │   ├── command
│   │   │       │   ├── query
│   │   │       │   └── common
│   │   │       └── infrastructure
│   │   │           ├── adapters
│   │   │           │   ├── in
│   │   │           │   └── out
│   │   │           ├── configuration
│   │   │           └── monitor
│   └── test
├── .gitignore
└── README.md
```

> You may rename packages to match your organization’s standards.


---

## 🧰 Tech Stack

| Component            | Version                                   | Notes                              |
|----------------------|--------------------------------------------|------------------------------------|
| **Java**             | 21 LTS                                     | Template aligned with modern LTS   |
| **Spring Boot**      | 3.3.x                                      | Native Java 21 support             |
| **Spring Web (MVC)** | latest via Boot                            | REST endpoints                     |
| **Spring Validation**| latest via Boot                            | Input validation                   |
| **Springdoc OpenAPI**| `springdoc-openapi-starter-webmvc-ui` 2.x  | Swagger UI & OpenAPI               |
| **JUnit 5**          | via Boot                                   | Unit & integration tests           |
| **H2 Database**      | latest                                     | Optional local storage             |
| **Lombok**           | 1.18.x                                     | Boilerplate reduction              |
| **MapStruct**        | 1.5.x                                      | Mapping DTO ↔ domain               |

---

# 📡 Available Endpoints

### **Health Check**
```
GET /health
```

### **Swagger / OpenAPI**
- Swagger UI → http://localhost:8080/swagger-ui  
- OpenAPI JSON → http://localhost:8080/v3/api-docs

---

# ⚙️ Base Application Configuration

The main `application.yml` contains only environment-agnostic configuration:

- Application metadata
- Swagger / Springdoc
- Server port
- Basic Actuator exposure
- Default logging settings

Environment-specific configuration must be placed inside dedicated profile files:

```
application-dev.yml
application-prod.yml
```

---

# 🔀 Application Profiles

This template includes multiple Spring Boot profiles.

## **`dev` profile**
- Verbose logging (DEBUG)
- Full Actuator endpoint exposure
- Swagger always enabled
- Health details always visible

Activate:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## **`prod` profile**
- Secure Actuator exposure
- Swagger UI disabled by default
- Optimized logging

Activate:

```bash
java -jar app.jar --spring.profiles.active=prod
```

---

# 🌐 Global CORS Configuration

A global CORS configuration is included:

```
src/main/java/.../infrastructure/configuration/GlobalCorsConfig.java
```

It:

- Applies to all API endpoints
- Allows common development origins
- Supports common HTTP methods
- Can be easily customized per microservice

---

## 🚀 Getting Started


### 1. Create a new microservice from this template

Click **“Use this template” → “Create a new repository”**.

### 2. Clone your newly created repository

```
git clone https://github.com/<your-org>/<your-service>.git
```

### 3. Update the project identifiers

- Change the base package (`com.example.hexblueprint` → your domain).
- Adjust metadata like artifact name, module name, descriptions, etc.

### 4. Start implementing your service

- Model your **domain**.
- Add **command** and **query** use cases in the application layer.
- Add inbound/outbound adapters as needed.

---

## 🧪 Testing Strategy

This template supports testing at multiple levels:

### Unit Tests
- Domain logic  
- Application services  
- Port-level interactions (using mocks)

### Adapter Tests
- REST layer  
- Messaging  
- Database adapters  

### Integration Tests
- End-to-end tests bridging application and infrastructure layers.

You can extend the test setup according to your preferred testing stack.

---

## 🧩 Extending the Blueprint

Common enhancements include:

- Database adapters (SQL/NoSQL)
- Messaging publishers/consumers
- Global exception handling
- Validation layers
- Observability (metrics, tracing, logging)
- API documentation
- Code generation helpers
- CI/CD pipelines

This template stays intentionally minimal so you can adapt it to your ecosystem.

---

## 📚 Philosophy

The objective of this blueprint is to:

- Standardize microservice creation  
- Enforce clear architectural boundaries  
- Reduce framework-coupling  
- Encourage maintainable, test-friendly codebases  
- Provide a reusable, organization-wide starting point  

If your project becomes a tangled mess despite using this template, congratulations:  
the fault is statistically yours, not the template’s.  

---

## 🤝 Contributing

If you improve this blueprint or generalize a pattern, feel free to open a pull request.  
If you break the architecture, feel free not to.

---

## 📄 License

This project is released under **CC0-1.0** — do whatever you want with it.
