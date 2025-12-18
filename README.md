# ⬢ Hexagonal Microservice Blueprint (CQRS)

A production-ready template for building microservices using **Hexagonal Architecture (Ports & Adapters)** and **CQRS**.

This blueprint provides a strict separation of concerns, ensuring that your **Domain** logic remains pure and unaffected by framework or infrastructure changes.

---

## 🧱 Architecture Overview

This project follows the **Dependency Rule**: source code dependencies can only point **inwards**.

### 1. Domain Layer (The Core)
* **Path:** `src/main/java/.../domain`
* **Responsibility:** Pure business logic and rules.
* **Dependencies:** None. Pure Java. No Spring, no Hibernate, no Lombok on entities.
* **Components:** Entities, Value Objects, Domain Services, Business Exceptions.

### 2. Application Layer (The Orchestrator)
* **Path:** `src/main/java/.../application`
* **Responsibility:** Orchestrates use cases and enforces **CQRS**:
  * **Commands:** State-changing operations.
  * **Queries:** Read-only operations.
* **Components:**
  * **Input Ports** (Use Case interfaces)
  * **Use Case implementations** (command/query)
  * **Output Ports** (interfaces to external systems)

### 3. Infrastructure Layer (The Implementation)
* **Path:** `src/main/java/.../infrastructure`
* **Responsibility:** Technical details and frameworks.
* **Dependencies:** Spring Boot, persistence, messaging, external APIs.
* **Components:** Controllers, adapters, repositories, configuration.

---

## 📂 Project Structure

```text
├── src
│   ├── main
│   │   └── java
│   │       └── com.wolfbonobo.hex.blueprint
│   │           ├── application
│   │           │   ├── command
│   │           │   │   ├── model
│   │           │   │   └── usecase
│   │           │   ├── query
│   │           │   │   ├── model
│   │           │   │   └── usecase
│   │           │   ├── ports
│   │           │   │   ├── in
│   │           │   │   │   ├── command
│   │           │   │   │   └── query
│   │           │   │   └── out
│   │           │   │       ├── persistence
│   │           │   │       └── external
│   │           │   └── common
│   │           │       └── events
│   │           ├── domain
│   │           │   ├── model
│   │           │   ├── service
│   │           │   └── exception
│   │           └── infrastructure
│   │               ├── adapters
│   │               │   ├── in
│   │               │   │   ├── rest
│   │               │   │   └── messaging
│   │               │   └── out
│   │               │       ├── persistence
│   │               │       ├── messaging
│   │               │       └── external
│   │               ├── configuration
│   │               └── observability
│   └── test
│       └── java
│           └── com.wolfbonobo.hex.blueprint
│               ├── architecture
│               ├── application
│               ├── domain
│               └── infrastructure
```

---

## 📏 Naming Conventions

| Concept | Suffix | Example | Location |
|-------|--------|---------|----------|
| Input Port | `UseCase` | `CreateOrderUseCase` | `application/ports/in` |
| Use Case Impl | `UseCaseImpl` | `CreateOrderUseCaseImpl` | `application/*/usecase` |
| Output Port | `Port` | `OrderRepositoryPort` | `application/ports/out` |
| Adapter (DB) | `Adapter` | `OrderJpaAdapter` | `infrastructure/adapters/out` |
| Adapter (Web) | `Controller` | `OrderController` | `infrastructure/adapters/in` |
| Domain Entity | — | `Order` | `domain/model` |

---

## 🧰 Tech Stack

| Component | Version | Notes |
|---------|---------|------|
| Java | 21 LTS | Modern LTS baseline |
| Spring Boot | 3.3.x | Java 21 native |
| Spring Web | via Boot | REST APIs |
| Spring Validation | via Boot | Input validation |
| Springdoc OpenAPI | 2.x | Swagger UI |
| Lombok | 1.18.x | Boilerplate reduction |
| MapStruct | 1.5.x | DTO ↔ Domain mapping |
| ArchUnit | 1.3.x | Architecture enforcement |
| H2 | latest | Local/dev database |

---

## 🧪 Testing Strategy

* **Architecture Tests:** ArchUnit (mandatory)
* **Unit Tests:** Domain & application use cases
* **Port Tests:** Mocks or manual test doubles
* **Integration Tests:** Infrastructure adapters

---

## 📚 Philosophy

This blueprint exists to:

- Enforce architectural boundaries
- Reduce framework coupling
- Encourage testable code
- Serve as an organization-wide standard

If the architecture is broken, the tests should fail.
If they don’t, fix the tests.

---

## 🚀 Quick Start

1.  **Build the project:**
    ```bash
    mvn clean install
    ```
2.  **Run locally:**
    ```bash
    mvn spring-boot:run
    ```
3.  **Check Health:**
    Access `http://localhost:8080/actuator/health`
4.  **View API Docs:**
    Access `http://localhost:8080/swagger-ui.html`

---

## 📄 License

MIT — use it freely.
