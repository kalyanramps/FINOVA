# 🏦 FINOVA — Bank Management System

### A RESTful Backend Application Built with Spring Boot

**Finova** is a backend-focused Bank Management System developed using **Java, Spring Boot, Spring Data JPA, Spring Core, Spring Web, Lombok, and PostgreSQL**. The application is designed to manage banking information through RESTful APIs, with a structured domain model covering **Bank, Account, and Address** entities.

The project demonstrates backend application development, REST API design, object-relational mapping (ORM), database integration, and layered application architecture using the Spring ecosystem.

---

## 📌 Table of Contents

- [🎯 Project Overview](#-project-overview)
- [✨ Key Features](#-key-features)
- [🛠️ Technology Stack](#️-technology-stack)
- [🏗️ Application Architecture](#️-application-architecture)
- [🗃️ Domain Model](#️-domain-model)
- [🔄 Application Workflow](#-application-workflow)
- [🌐 REST API Design](#-rest-api-design)
- [📦 Sample API Requests](#-sample-api-requests)
- [⚙️ Getting Started](#️-getting-started)
- [🔐 Configuration](#-configuration)
- [🧪 Testing the APIs](#-testing-the-apis)
- [📚 Concepts Demonstrated](#-concepts-demonstrated)
- [🚀 Future Enhancements](#-future-enhancements)
- [👨‍💻 Author](#-author)

---

## 🎯 Project Overview

Finova is a backend application that organizes banking data into related entities and exposes application functionality through RESTful web services.

The application focuses on three core domain entities:

- 🏦 **Bank** — Stores bank identification and banking-related information.
- 💳 **Account** — Represents account information associated with a bank.
- 📍 **Address** — Stores address details such as city, state, and PIN code.

The backend uses Spring Boot to simplify application configuration, Spring Web to develop REST endpoints, Spring Data JPA to handle persistence, and PostgreSQL as the relational database.

### 🎯 Project Objectives

- Build a structured backend application using Java and Spring Boot.
- Develop RESTful APIs for managing banking-related resources.
- Map Java entity classes to relational database tables.
- Establish relationships between domain entities.
- Integrate PostgreSQL for persistent data storage.
- Apply separation of concerns through a layered architecture.
- Practice backend development concepts relevant to real-world enterprise applications.

---

## ✨ Key Features

### 🏦 Bank Management
- Maintain bank-related information.
- Represent banks using unique identifiers and banking details.
- Organize bank data into a structured entity model.

### 💳 Account Management
- Represent account details, including account number and account type.
- Associate account records with their corresponding bank.
- Support structured persistence through JPA entity mapping.

### 📍 Address Management
- Store address information, including city, state, and PIN code.
- Maintain address details as a separate domain entity.
- Organize address data for integration with other banking entities.

### 🌐 RESTful Web Services
- Expose backend functionality through HTTP endpoints.
- Use JSON for API request and response payloads.
- Follow resource-oriented API design principles.

### 🗄️ Database Integration
- Integrate PostgreSQL with the Spring Boot application.
- Use Spring Data JPA for database interaction.
- Reduce boilerplate persistence code through repository abstractions.

### 🧩 Maintainable Backend Design
- Separate web, business, and persistence responsibilities.
- Use Spring's dependency injection and application context.
- Leverage Lombok to reduce repetitive Java code.

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Core programming language |
| 🌱 Spring Boot | Application setup and backend development |
| 🧩 Spring Core | Dependency injection and IoC container |
| 🌐 Spring Web | REST controllers and HTTP request handling |
| 🗃️ Spring Data JPA | Repository abstraction and data persistence |
| 🔗 JPA / Hibernate | ORM and entity-to-table mapping |
| 🐘 PostgreSQL | Relational database |
| ⚡ Lombok | Reduction of Java boilerplate |
| 📦 Maven | Build and dependency management, if configured |
| 🧪 Postman | API testing |
| 🔧 Git & GitHub | Version control and source code management |

---

## 🏗️ Application Architecture

Finova follows a layered backend architecture that separates the responsibilities of handling HTTP requests, processing application logic, and interacting with the database.

```text
              ┌──────────────────────┐
              │   Client / Postman   │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │   REST Controller    │
              │    Spring Web        │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │    Service Layer     │
              │  Business Operations │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │  Repository Layer    │
              │   Spring Data JPA    │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │  JPA / Hibernate     │
              │    ORM Mapping       │
              └──────────┬───────────┘
                         │
                         ▼
              ┌──────────────────────┐
              │     PostgreSQL       │
              │   Relational Data    │
              └──────────────────────┘
```

### 🔍 Layer Responsibilities

**1. Controller Layer**
- Receives HTTP requests.
- Maps API endpoints to application operations.
- Returns appropriate HTTP responses.

**2. Service Layer**
- Encapsulates business logic.
- Coordinates operations involving repositories and entities.
- Helps keep controllers focused on request handling.

**3. Repository Layer**
- Provides data access abstractions.
- Uses Spring Data JPA to perform database operations.
- Reduces the need for repetitive persistence code.

**4. Entity Layer**
- Defines the domain objects, such as `Bank`, `Account`, and `Address`.
- Maps Java objects to relational database tables.
- Represents the relationships between domain entities.

**5. Database Layer**
- Stores persistent banking and address information.
- Uses PostgreSQL as the relational database management system.

*Note: The diagram represents the recommended logical architecture. Adapt the layers to match the actual packages and classes in the repository.*

---

## 🗃️ Domain Model

The current domain design centers around three entities.

### 🏦 1. Bank Entity

Represents a bank and its identifying information.

| Attribute | Description |
|---|---|
| `bankId` | Unique identifier for a bank |
| `bankName` | Name of the bank, if included in the implementation |
| `ifscCode` | Bank or branch identification code, if modeled |
| `addressId` | Address reference, depending on the entity mapping |

### 💳 2. Account Entity

Represents an account associated with a bank.

| Attribute | Description |
|---|---|
| `accountId` | Unique identifier for an account |
| `accountNo` | Account number |
| `accountType` | Type of account |
| `accountHolderName` | Account holder's name, if implemented |
| `bankId` | Reference to the associated bank |

### 📍 3. Address Entity

Represents address details.

| Attribute | Description |
|---|---|
| `addressId` | Unique identifier for an address |
| `flatId` | Flat or building identifier, if used |
| `city` | City name |
| `state` | State name |
| `pincode` | Postal PIN code |

### 🔗 Entity Relationships

The intended domain design connects banks, accounts, and addresses.

- A bank can be associated with account records.
- An account can reference its associated bank.
- A bank or another domain entity can reference address information, depending on the implemented mapping.

The precise cardinality and foreign-key structure depend on the JPA annotations used in the source code, such as `@OneToMany`, `@ManyToOne`, `@OneToOne`, and `@JoinColumn`.

---

## 🔄 Application Workflow

A typical API request follows this sequence:

1. A client sends an HTTP request to a REST endpoint.
2. The Spring Web controller receives and processes the request.
3. The application performs the required operation in the service layer, where implemented.
4. The repository interacts with the database through Spring Data JPA.
5. Hibernate translates entity operations into database queries.
6. PostgreSQL stores or retrieves the requested information.
7. The application returns an HTTP response to the client.

This approach separates API handling from persistence and makes the application easier to maintain and extend.

---

## 🌐 REST API Design

The following endpoint structure is a suggested REST API convention for Finova. Confirm the actual mappings in your controller classes before treating these routes as implemented.
I have built 35+ REST APIs in this project across three moduless.

| HTTP Method | Suggested Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/banks` | Create a bank |
| `GET` | `/api/banks` | Retrieve banks |
| `GET` | `/api/banks/{id}` | Retrieve a bank by ID |
| `PUT` | `/api/banks/{id}` | Update a bank |
| `DELETE` | `/api/banks/{id}` | Delete a bank |
| `POST` | `/api/accounts` | Create an account |
| `GET` | `/api/accounts` | Retrieve accounts |
| `GET` | `/api/accounts/{id}` | Retrieve an account by ID |
| `PUT` | `/api/accounts/{id}` | Update an account |
| `DELETE` | `/api/accounts/{id}` | Delete an account |
| `POST` | `/api/addresses` | Create an address |
| `GET` | `/api/addresses` | Retrieve addresses |
| `GET` | `/api/addresses/{id}` | Retrieve an address by ID |
| `PUT` | `/api/addresses/{id}` | Update an address |
| `DELETE` | `/api/addresses/{id}` | Delete an address |

### 📡 HTTP Methods

- **GET** — Retrieve resources.
- **POST** — Create resources.
- **PUT** — Replace or update a resource.
- **DELETE** — Remove a resource.

Use appropriate HTTP status codes, such as `200 OK`, `201 Created`, `204 No Content`, `400 Bad Request`, and `404 Not Found`, according to the outcome of each operation.

---

## 📦 Sample API Requests

The examples below illustrate possible JSON payloads for the domain model. Adjust field names and request formats to match your actual entity classes and controller mappings.

### 🏦 Create a Bank

**Example request body**

```json
{
  "bankName": "Finova Bank",
  "ifscCode": "FINO0001234"
}
```

### 💳 Create an Account

**Example request body**

```json
{
  "accountNo": "123456789012",
  "accountType": "SAVINGS",
  "bank": {
    "bankId": 1
  }
}
```

### 📍 Create an Address

**Example request body**

```json
{
  "flatId": "FLAT-101",
  "city": "Bengaluru",
  "state": "Karnataka",
  "pincode": "560001"
}
```

These are illustrative examples rather than verified request formats. Depending on the actual implementation, an API may accept a bank ID, an address ID, a nested entity, or a dedicated request DTO.

---

## ⚙️ Getting Started

Follow these steps to run the backend locally.

### Prerequisites

Install the following tools:

- ☕ Java Development Kit (JDK), compatible with your project
- 📦 Maven, if the project uses Maven
- 🐘 PostgreSQL
- 🔧 Git
- 🧪 Postman or another HTTP client

### 1. Clone the Repository

```bash
git clone https://github.com/kalyanramps/Finova.git
```

Navigate to the project directory:

```bash
cd Finova
```

*Replace the repository URL if your GitHub repository uses a different name or location.*

### 2. Create a PostgreSQL Database

Open PostgreSQL and create a database:

```sql
CREATE DATABASE finova_db;
```

Ensure the PostgreSQL service is running before starting the application.

### 3. Configure Database Connectivity

Update the database connection properties in `application.properties` or `application.yml`.

See the [Configuration](#-configuration) section for an example.

### 4. Run the Application

For a Maven-based Spring Boot project, execute:

```bash
mvn spring-boot:run
```

Alternatively, build the application:

```bash
mvn clean package
```

Then run the generated JAR:

```bash
java -jar target/finova-*.jar
```

The application should start on the configured server port, commonly `8080`, unless another port is specified.

### 5. Test the APIs

Open Postman and send requests to the endpoints defined by your controller classes.

For example, if your application exposes the suggested bank endpoint:

```text
GET http://localhost:8080/api/banks
```

The actual URL depends on the controller's `@RequestMapping` and method-level mappings.

---

## 🔐 Configuration

A sample PostgreSQL configuration for Spring Boot is shown below.

**File:** `src/main/resources/application.properties`

```properties
spring.application.name=finova

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/finova_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

### ⚠️ Configuration Notes

- Set `DB_USERNAME` and `DB_PASSWORD` in your local environment.
- Never commit database passwords, production credentials, or other secrets to GitHub.
- `spring.jpa.hibernate.ddl-auto=update` is convenient during development, but production environments should generally use controlled database migrations.
- Disable SQL logging in production when it could expose sensitive information.
- Ensure that the PostgreSQL JDBC driver dependency is present in the build configuration.

The configuration above is an example. Retain the properties appropriate to your existing project.

---

## 🧪 Testing the APIs

Use Postman or another HTTP client to verify API behavior.

Recommended test scenarios include:

- Creating a bank and retrieving its details.
- Creating an account associated with an existing bank.
- Creating and retrieving address information.
- Verifying that invalid identifiers return appropriate responses.
- Checking behavior when a requested record does not exist.
- Confirming that database changes persist between application restarts.
- Verifying that entity relationships are stored and retrieved correctly.

If validation and centralized exception handling are implemented, test invalid request bodies and expected error responses as well.

---

## 📚 Concepts Demonstrated

This project provides practical experience with several Java backend development concepts.

### ☕ Core Java
- Object-oriented programming
- Classes, objects, and encapsulation
- Entity modeling
- Java annotations

### 🌱 Spring Framework
- Inversion of Control (IoC)
- Dependency Injection (DI)
- Spring application context
- Annotation-based configuration

### 🌐 REST API Development
- 35+ REST APIs
- HTTP methods and status codes
- Resource-oriented API design
- JSON request and response handling
- Controller-based request mapping

### 🗃️ Persistence and ORM
- JPA entity mapping
- Hibernate ORM
- Spring Data repository abstractions
- Relational database integration
- Entity relationships and foreign keys

### 🐘 PostgreSQL
- Relational data modeling
- Database connectivity
- Persistent storage
- SQL-backed data management

### 🧹 Code Maintainability
- Lombok annotations
- Separation of concerns
- Layered architecture
- Reusable repository operations

---

## 🚀 Future Enhancements

The following improvements could extend Finova into a more complete banking backend.

- [ ] Add Bean Validation using annotations such as `@NotNull`, `@NotBlank`, and `@Size`.
- [ ] Implement centralized exception handling using `@ControllerAdvice`.
- [ ] Introduce DTOs to separate API contracts from JPA entities.
- [ ] Add structured logging and consistent error responses.
- [ ] Implement pagination and sorting for collection endpoints.
- [ ] Add unit tests using JUnit and Mockito.
- [ ] Add integration tests for REST endpoints and database operations.
- [ ] Use Flyway or Liquibase for database schema migrations.
- [ ] Introduce Spring Security and role-based access control.
- [ ] Add authentication using JWT, if appropriate for the application.
- [ ] Document the REST APIs using Swagger / OpenAPI.
- [ ] Containerize the application and PostgreSQL using Docker.

These are potential enhancements and should not be considered existing features unless they have been implemented.

---

## 👨‍💻 Author

**Kalyan Ram P S**

🎓 B.E. in Information Science and Engineering

💻 Java Backend Development | Spring Boot | Spring Data JPA | PostgreSQL | REST APIs

🔗 **GitHub:** [@kalyanramps](https://github.com/kalyanramps)

---

## ⭐ Project Highlights

Finova demonstrates the development of a Java-based backend application using the Spring ecosystem, with a focus on RESTful web services, relational database integration, and structured domain modeling.

The project serves as a foundation for exploring enterprise-style backend development and extending a simple banking domain into a more comprehensive application.

**If you find this project useful, consider giving the repository a ⭐ on GitHub!**

---

*Built with ☕ Java, 🌱 Spring Boot, and 🐘 PostgreSQL.*
