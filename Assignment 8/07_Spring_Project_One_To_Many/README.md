# Department Employee Management System

A Spring Boot REST API project demonstrating One-to-Many Mapping between Department and Employee using Spring Boot, Spring Data JPA, MySQL, Spring Security, DTOs, Validation, Exception Handling, Pagination, Swagger/OpenAPI, and Logging.

---

## Project Overview

This project implements a One-to-Many relationship:

Department (1) → Employees (Many)

Example:

Engineering Department
├── Rahul Sharma
├── Priya Mehta
└── Sneha Patil

---

## Features

### Department Management
- Create Department with Employees
- Get All Departments
- Get Department By ID
- Update Department with Employees
- Delete Department

### Employee Management
- Multiple employees under one department
- Unique employee email validation
- Automatic employee deletion when department is deleted

### Security
- Spring Security Basic Authentication
- Role-Based Authorization
- BCrypt Password Encoding
- Stateless Session Management

### Validation
- Required field validation
- Email validation
- Salary validation
- Employee list validation

### Exception Handling
- Resource Not Found Exception
- Duplicate Resource Exception
- Validation Exception Handling
- Access Denied Handling
- Global Exception Handler

### Additional Features
- DTO Pattern
- ModelMapper
- Pagination
- Swagger Documentation
- Logging

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- Swagger/OpenAPI
- Lombok
- ModelMapper
- Maven

---

## Project Structure

```text
src/main/java/com/swabhav/demo

├── config
├── controller
├── dto
├── exception
├── model
├── repository
├── service
└── DemoApplication.java
```

---

## Database Setup

Create database:

```sql
CREATE DATABASE one_to_many_demo;
```

---

## Application Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/one_to_many_demo
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

springdoc.swagger-ui.path=/swagger-ui.html
```

---

## Authentication

### ADMIN

Username: admin

Password: admin123

### USER

Username: user

Password: user123

---

## Authorization Rules

| Method | Endpoint | Access |
|----------|----------|----------|
| GET | /api/departments/** | USER, ADMIN |
| POST | /api/departments/** | ADMIN |
| PUT | /api/departments/** | ADMIN |
| DELETE | /api/departments/** | ADMIN |

---

## API Endpoints

### Create Department

POST /api/departments

### Get All Departments

GET /api/departments

### Get Department By ID

GET /api/departments/{id}

### Update Department

PUT /api/departments/{id}

### Delete Department

DELETE /api/departments/{id}

### Pagination

GET /api/departments/page?pageNumber=0&pageSize=5

---

## Sample Request

```json
{
  "department_name": "IT",
  "location": "Noida",
  "employees": [
    {
      "employee_name": "Rahul Sharma",
      "email": "rahul@gmail.com",
      "salary": 50000
    }
  ]
}
```

---

## Swagger Documentation

Swagger UI

http://localhost:8080/swagger-ui.html

OpenAPI JSON

http://localhost:8080/v3/api-docs

---

## Postman Collection

Located inside:

```text
postman/
```

Files:

```text
Department_Employee_One_To_Many_API.postman_collection.json
Department_Employee_One_To_Many_API.postman_environment.json
```

---

## Tested Scenarios

- Create Department as ADMIN
- Create Department as USER (Forbidden)
- Get All Departments
- Get Department By ID
- Update Department
- Delete Department
- Duplicate Department Validation
- Duplicate Employee Email Validation
- Pagination Validation
- Access Denied Validation
- Validation Errors

---

## Screenshots

Folder:

```text
screenshots/
```

Recommended screenshots:

```text
swagger-ui.png
create-department.png
get-all-departments.png
pagination.png
validation-error.png
access-denied.png
database-tables.png
```

---

## Learning Outcomes

- Spring Boot REST APIs
- One-To-Many Mapping
- DTO Pattern
- Spring Security
- Validation
- Exception Handling
- Pagination
- Swagger/OpenAPI
- Logging
- Layered Architecture

---

## Author

Harshit

B.Tech Information Technology

ABES Engineering College