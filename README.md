# GetYourSlot 

GetYourSlot is a REST API designed to manage appointment bookings between clients and service providers. The application handles authentication, appointment scheduling, role-based permissions, and service management through a secure JWT-based workflow.

The project was built with Spring Boot and PostgreSQL following a layered architecture that separates controllers, business logic, and data access.
## Features

- User registration and login with JWT authentication
- Role-based access control (CLIENT / ADMIN)
- Create, cancel and list appointments
- Overlap validation to prevent double-booking
- Admin endpoints to manage services and confirm appointments
- Dockerized PostgreSQL database

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Security + JWT (jjwt)
- Spring Data JPA + Hibernate
- PostgreSQL 16
- Docker & Docker Compose
- Lombok
- Maven

## Getting Started

### Prerequisites

Before running make sure that the following are installed.

```bash
java -version
mvn -version
docker --version
docker compose version
```

Expected versions:

- Java 21+
- Maven 3.9+
- Docker 20+
- Docker Compose v2+

### Run the database

```bash
docker compose up -d
```

### Start the application

```bash
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Duser.timezone=UTC"
```

Or run directly from IntelliJ with the VM option `-Duser.timezone=UTC`.

## API Endpoints

### Auth (public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Services (public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/services` | List all services |
| GET | `/api/services/{id}` | Get service by ID |

### Appointments (requires login)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/appointments` | Book an appointment |
| GET | `/api/appointments/my` | Get my appointments |
| PUT | `/api/appointments/{id}/cancel` | Cancel an appointment |

### Admin (requires ADMIN role)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/appointments` | List all appointments |
| PUT | `/api/admin/appointments/{id}/confirm` | Confirm an appointment |
| POST | `/api/admin/services` | Create a new service |

## Authentication

All protected endpoints require a Bearer token in the Authorization header:

```
Authorization: Bearer <your_token>
```

## Project Structure

```
src/main/java/com/getyourslot/
├── config/        # JWT filter and Security configuration
├── controller/    # REST controllers
├── dto/           # Request and response objects
├── exception/     # Global error handling
├── model/         # JPA entities
├── repository/    # Data access layer
└── service/       # Business logic
```