# Hex Microservice Blueprint

A template repository for building microservices using **Hexagonal Architecture** and **CQRS**.  
Provides a clean separation between domain, application logic, and infrastructure adapters, enabling scalable, maintainable, and framework-agnostic service development.

---

## 🧱 Architecture Overview

This blueprint follows a strict **Ports & Adapters (Hexagonal)** pattern combined with **CQRS**:

### **Domain Layer**
- Pure business rules.
- Contains domain entities, aggregates, value objects, and domain services.
- No framework dependencies.

### **Application Layer**
Implements use cases through **CQRS**:
- **Command** — state-changing operations.
- **Query** — read-only operations.  
Defines **ports** (interfaces) used by inbound and outbound adapters.

### **Infrastructure Layer**
- Framework-specific and external integrations.
- Contains adapters:
  - **Inbound adapters** (REST controllers, messaging consumers, CLI, etc.)
  - **Outbound adapters** (database repositories, external APIs, messaging publishers)
- Includes configuration, monitoring, and any cross-cutting mechanisms.

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

> You are free to rename packages to match your organization conventions.

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
