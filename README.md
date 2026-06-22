# JAVA-SpringBoot-Minicommerce

# 🛒 Mini Commerce - Spring Boot Backend API

A production-grade e-commerce backend REST API built with **Spring Boot 4.0.6**, featuring secure JWT authentication, comprehensive order management, and robust product catalog management.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green?style=flat-square&logo=spring)
![Status](https://img.shields.io/badge/Status-Active%20Development-yellowgreen?style=flat-square)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Architecture](#project-architecture)
- [Database Schema](#database-schema)
- [Getting Started](#getting-started)
- [Roadmap](#roadmap)

## 🎯 Overview

Mini Commerce is a scalable e-commerce backend system that demonstrates enterprise-grade Spring Boot practices. It provides RESTful APIs for managing customers, products, and orders with JWT-based authentication and comprehensive error handling.

This project showcases:
- ✅ Layered Architecture (Controller → Service → Repository)
- ✅ JWT Authentication & Authorization
- ✅ Database-driven Entity Relationships
- ✅ Global Exception Handling
- ✅ DTOs for Request/Response Separation
- ✅ Validation using Jakarta Validation API
- ✅ Docker Support

## ✨ Features

### Current Version (v1.0.0)

#### Authentication & Security
- 🔐 JWT token-based authentication
- 🔑 Secure password management with Spring Security
- 📝 User registration and login endpoints
- 🛡️ Role-based access control setup

#### Customer Management
- 👥 Create and manage customer profiles
- 📧 Unique email-based identification
- 🏠 Customer address management
- 📜 View customer order history with details

#### Product Management
- 📦 Full CRUD operations on products
- 🏷️ Product code-based identification
- 💰 Price and inventory tracking
- 📝 Detailed product descriptions

#### Order Management
- 🛍️ Create orders with multiple products
- 📊 Order tracking and history
- 💵 Automatic total amount calculation
- 🔗 Many-to-many order-product relationships

#### Error Handling
- ✔️ Global exception handler
- 📋 Comprehensive validation messages
- 🎯 Consistent error response format

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 17 |
| **Framework** | Spring Boot | 4.0.6 |
| **Database** | H2 (Development) / PostgreSQL (Production) | Latest |
| **Authentication** | JWT (JJWT) | 0.12.6 |
| **ORM** | Hibernate JPA | Latest |
| **Build Tool** | Maven | 3.9+ |
| **Container** | Docker | Latest |
| **Security** | Spring Security | Latest |
| **Validation** | Jakarta Bean Validation | Latest |

### Key Dependencies
- **spring-boot-starter-security**: Authentication and authorization
- **jjwt**: JWT token generation and validation
- **spring-boot-starter-data-jpa**: Database operations
- **spring-boot-starter-webmvc**: REST API support
- **spring-boot-starter-validation**: Input validation
- **lombok**: Reduce boilerplate code
- **h2database**: Development database

## 🏗️ Project Architecture

### Layered Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   Presentation Layer                    │
│  (REST Controllers - Handle HTTP Requests)              │
├─────────────────────────────────────────────────────────┤
│                   Business Logic Layer                  │
│  (Services - Business Rules & Validations)              │
├─────────────────────────────────────────────────────────┤
│                   Data Access Layer                     │
│  (Repositories - Database Operations)                   │
├─────────────────────────────────────────────────────────┤
│                   Database Layer                        │
│  (H2 / PostgreSQL - Data Persistence)                   │
└─────────────────────────────────────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- **Java 17+** installed
- **Maven 3.9+** installed
- **Docker** (optional, for containerization)
- **Git** for version control

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/BJustin07/JAVA-SpringBoot-Minicommerce.git
   cd JAVA-SpringBoot-Minicommerce
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   The application will start on `http://localhost:8080`

4. **Access the H2 Database Console** (Development only)
   ```
   URL: http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:testdb
   Username: h2
   Password: (leave blank)
   ```

### Docker Setup

1. **Build Docker image**
   ```bash
   docker build -t mini-commerce:1.0.0 .
   ```

2. **Run Docker container**
   ```bash
   docker run -p 8080:8080 mini-commerce:1.0.0
   ```

### Configuration

Update `src/main/resources/application.properties` for your environment:

```properties
# Server Configuration
server.port=8080
spring.application.name=mini-commerce

# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb          # H2 for dev
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

# Security
jwt.secret.key=<your-secret-key-here>
```

### Project Structure Best Practices

This project follows Spring Boot best practices:

#### 1. **Separation of Concerns**
- **Controllers**: Handle HTTP requests/responses
- **Services**: Contain business logic and validations
- **Repositories**: Handle database operations
- **Entities**: Define data models
- **DTOs**: Transfer data between layers

#### 2. **DTOs (Data Transfer Objects)**
Located in `*/dto/` folders within each module:
- `*RequestDTO`: Incoming API request data
- `*ResponseDTO`: Outgoing API response data
- Provides API contract independence from entities

#### 3. **Exception Handling**
- **GlobalExceptionHandler**: Catches all exceptions
- **Custom Exceptions**: Module-specific exception classes
- **Validation Errors**: Handled by Jakarta validation API

#### 4. **Security**
- **JWT Authentication**: Token-based stateless auth
- **Spring Security**: Manages authentication/authorization
- **Password Encoding**: BCrypt for password hashing

### Testing

Run unit tests:
```bash
mvn test
```

Run integration tests:
```bash
mvn verify
```

## 📈 Roadmap

### Version 1.0.0 ✅ (Current)
- ✅ JWT Authentication
- ✅ Customer CRUD operations
- ✅ Product CRUD operations
- ✅ Order creation and management
- ✅ Global exception handling
- ✅ Input validation

### Version 1.1.0 🚀 (In Progress)

#### Database Migration
- [ ] Migrate from H2 to PostgreSQL

#### Order Management Enhancement
- [ ] Create `OrderItem` entity (break N:M relationship)
- [ ] Add `priceAtOrderDate in OrderItem` field to capture product price at order time
- [ ] Implement order filtering:
  - [ ] Filter by order number
  - [ ] Filter by order date (range support)
  - [ ] Filter by customer number
- [ ] Add pagination support for order listings

#### Proposed Features for v1.1.0
- [ ] Order status management (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
- [ ] Order item-level tracking
- [ ] Price history tracking
- [ ] Advanced filtering API with query parameters
- [ ] Audit logging for order changes
- [ ] Order statistics and reporting endpoints
- [ ] Inventory management updates on order creation

## 🎓 Learning Resources

This project demonstrates:
- JWT authentication implementation
- Layered architecture pattern
- Entity relationship mapping in JPA
- Global exception handling
- REST API design principles
- Docker containerization
- Maven build automation

---

**Made by BJustin07**
