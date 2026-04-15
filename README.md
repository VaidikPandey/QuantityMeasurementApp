# ⚖️ Quantity Measurement App

A Java application built using **Data Driven Testing (DDT)** that progressively evolves from a simple unit comparison utility into a full **Spring Boot REST API** with database persistence.

---

## 🚀 Tech Stack

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square)
![JPA](https://img.shields.io/badge/Spring%20Data-JPA-green?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square)
![JUnit](https://img.shields.io/badge/JUnit-5-yellow?style=flat-square)

---

## 🌐 Live Demo

| Service | URL |
|---------|-----|
| 🖥️ Frontend | [quantitymeasurement-app.netlify.app](https://quantitymeasurement-app.netlify.app) |
| ⚙️ Backend API | [quantitymeasurementapp-production-7653.up.railway.app](https://quantitymeasurementapp-production-7653.up.railway.app) |
| 🗒️ Documentation | [quantitymeasurementapp-production-7653.up.railway.app/swagger-ui/index.html](https://quantitymeasurementapp-production-7653.up.railway.app/swagger-ui/index.html) |

---

## 📖 Project Journey

This project was built **use case by use case**, each one adding a new layer of functionality on top of the previous.

---

### 🔵 Phase 1 — Length Measurement (UC1–UC7)

| UC | Branch | Description |
|----|--------|-------------|
| UC1 | `feature/UC1-FeetEquality` | Compare two feet values for equality |
| UC2 | `feature/UC2-InchEquality` | Compare two inch values for equality |
| UC3 | `feature/UC3-GenericLength` | Refactor to generic `LengthUnit` enum with conversion factors |
| UC4 | `feature/UC4-YardEquality` | Add `YARDS` unit (1 yard = 3 feet = 36 inches) |
| UC5 | `feature/UC5-UnitConversion` | Convert between units (e.g. 1 FEET → 12 INCH) |
| UC6 | `feature/UC6-UnitAddition` | Add two quantities of different units |
| UC7 | `feature/UC7-TargetUnitAddition` | Result of addition returned in first operand's unit |

---

### 🟣 Phase 2 — Generic Units (UC8–UC12)

| UC | Branch | Description |
|----|--------|-------------|
| UC8 | `feature/UC8-StandaloneUnit` | Extract `IMeasurable` interface — decouple core logic from specific units |
| UC9 | `feature/UC9-WeightMeasurement` | Add `WeightUnit` (KILOGRAM, GRAM, POUND) |
| UC10 | `feature/UC10-GenericQuantity` | Make `Quantity<U extends IMeasurable>` fully generic |
| UC11 | `feature/UC11-VolumeMeasurementEquality` | Add `VolumeUnit` (LITRE, MILLILITRE, GALLON) |
| UC12 | `feature/UC12-QuantityMeasurementOperations` | Add `subtract()` and `divide()` operations |

---

### 🟠 Phase 3 — Architecture (UC13–UC15)

| UC | Branch | Description |
|----|--------|-------------|
| UC13 | `feature/UC13-CentralizedOperations` | Centralize arithmetic into `ArithmeticOperation` enum inside `Quantity` |
| UC14 | `feature/UC14-TemperatureMeasurement` | Add `TemperatureUnit` (CELSIUS, FAHRENHEIT) with non-linear lambda conversion. Temperature blocks arithmetic operations |
| UC15 | `feature/UC15-N-Tier` | Full N-Tier architecture — Controller → Service → Repository → Model |

---

### 🟡 Phase 4 — Database (UC16)

| UC | Branch | Description |
|----|--------|-------------|
| UC16 | `feature/UC16-DatabaseIntegration` | JDBC database integration with H2, connection pooling, `ApplicationConfig`, `ConnectionPool`, `DatabaseException`, and `QuantityMeasurementDatabaseRepository` |

**Key concepts introduced:**
- JDBC connection management
- Connection pooling
- Parameterized SQL queries (SQL injection prevention)
- `application.properties` configuration
- Custom exception hierarchy

---

### 🟢 Phase 5 — Spring Boot REST API (UC17)

| UC | Branch | Description |
|----|--------|-------------|
| UC17 | `feature/UC17-SpringBoot` | Full Spring Boot REST API with JPA, embedded Tomcat, Security config, and Global Exception Handler |

**Key concepts introduced:**
- Spring Boot auto-configuration
- Spring Data JPA (replaces manual JDBC)
- REST Controllers with `@RestController`
- Dependency Injection with `@Autowired`
- Global Exception Handling with `@RestControllerAdvice`
- JPA auto table creation

---

### 🔴 Phase 6 — Security (UC18)

| UC | Branch | Description |
|----|--------|-------------|
| UC18 | `feature/UC18-GoogleAuth` | Google OAuth2 + Email/Password login, JWT token based API security, MySQL database migration |

**Key concepts introduced:**
- Google OAuth2 authentication
- Email + Password registration and login
- BCrypt password hashing
- JWT token generation and validation
- Protected API endpoints
- Spring Security filter chain
- Stateless session management
- MySQL database (migrated from H2)

---

## ⚙️ Running the App

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Docker (optional, for MySQL)

### Database Setup
```sql
CREATE DATABASE quantity_measurement_db;
```

### Steps
```bash
# Clone the repo
git clone https://github.com/VaidikPandey/QuantityMeasurementApp.git

# Copy example properties and fill in your credentials
cp src/main/resources/application.properties.example src/main/resources/application.properties

# Run the app
mvn spring-boot:run
```

App starts at: `http://localhost:8080`

---

## 🧪 Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=QuantityMeasurementAppTest
```

---

## 📐 Supported Units

| Category | Units |
|----------|-------|
| Length | `FEET`, `INCH`, `YARDS`, `CENTIMETER` |
| Weight | `KILOGRAM`, `GRAM`, `POUND` |
| Volume | `LITRE`, `MILLILITRE`, `GALLON` |
| Temperature | `CELSIUS`, `FAHRENHEIT` |

---

## 🧠 Key Concepts Learned

- Data Driven Testing (DDT)
- Object Oriented Design (Generics, Interfaces, Enums)
- N-Tier Architecture (Controller → Service → Repository)
- JDBC & Connection Pooling
- Spring Boot & Dependency Injection
- Spring Data JPA & ORM
- REST API Design
- Global Exception Handling
- MySQL Database
- Google OAuth2 + JWT Security
- BCrypt Password Hashing

---

## 👨‍💻 Author

**Vaidik Pandey**
