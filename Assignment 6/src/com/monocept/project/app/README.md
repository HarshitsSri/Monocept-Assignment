# Student Course Registration Management System

A console-based Java application developed using JDBC and MySQL for managing students, course registrations, fee management, and report generation.

The project follows a layered architecture approach and demonstrates practical implementation of JDBC connectivity, transaction management, SQL joins, aggregation queries, normalization, and input validation.

GitHub supports Mermaid diagrams directly inside README markdown files. ([GitHub Docs][1])

---

# Features

## Student Management

* Add New Student
* Search Student by ID
* Update Student Name
* Update Student Branch
* Delete Student Completely

---

## Course Registration

* Register Student for Course
* Update Course Fee
* Cancel Course Registration

---

## Reports

* View All Students with Registered Courses
* Generate High Paying Students Report
* Generate Course-wise Student Count Report

---

# Technologies Used

| Technology   | Purpose                   |
| ------------ | ------------------------- |
| Java         | Core Programming Language |
| JDBC         | Database Connectivity     |
| MySQL        | Relational Database       |
| SQL          | Query Execution           |
| OOPs         | Application Design        |
| Git & GitHub | Version Control           |

---

# Project Architecture

The project follows a layered architecture structure:

* Presentation Layer
* Service Layer
* DAO Layer
* Model Layer
* Utility Layer

This improves:

* Code Maintainability
* Reusability
* Scalability
* Separation of Concerns

---

# Database Design

The database is normalized using foreign key relationships.

---

## student Table

Stores student details.

| Column    | Type    |
| --------- | ------- |
| id        | INT     |
| name      | VARCHAR |
| age       | INT     |
| branch_id | INT     |

---

## branch Table

Stores available branches.

| Column      | Type    |
| ----------- | ------- |
| branch_id   | INT     |
| branch_name | VARCHAR |

---

## course Table

Stores available courses.

| Column      | Type    |
| ----------- | ------- |
| course_id   | INT     |
| course_name | VARCHAR |

---

## registration Table

Stores course registration details.

| Column     | Type   |
| ---------- | ------ |
| reg_id     | INT    |
| student_id | INT    |
| course_id  | INT    |
| fees_paid  | DOUBLE |

---

# Database Normalization

The project follows normalization principles by separating:

* Student Data
* Branch Data
* Course Data
* Registration Data

This avoids:

* Data Redundancy
* Duplicate Entries
* Inconsistent Data Storage

---

# Transaction Management

Transaction management is implemented in critical operations such as:

* Student Course Registration
* Delete Student Completely

If any operation fails during execution, all changes are rolled back to maintain consistency and atomicity.

---

# Input Validation

A dedicated `InputValidator` utility class is implemented for handling console input safely.

## Validations Included

* Integer Validation
* Double Validation
* String Validation
* Branch Selection Validation
* Course Selection Validation

This prevents invalid user input and runtime exceptions.

---

# SQL Concepts Used

The project demonstrates practical implementation of:

* INNER JOIN
* LEFT JOIN
* GROUP BY
* HAVING Clause
* Aggregation Functions
* Foreign Keys
* Transaction Handling

---

# Project Structure

```text
src
│
├── app
│   └── MainApp.java
│
├── dao
│   ├── StudentDAO.java
│   └── RegistrationDAO.java
│
├── model
│   ├── Student.java
│   └── Registration.java
│
├── service
│   └── StudentService.java
│
├── util
│   └── DBUtil.java
│
└── inputvalidator
    └── InputValidator.java
```

---

# Class Diagram

```mermaid
classDiagram

class MainApp {
    +main(String[] args)
}

class StudentService {
    -DBUtil dbutil
    -StudentDAO studentDao
    -RegistrationDAO registrationDao

    +studentRegistrationService(Registration r)
    +addNewStudent(Student s)
    +viewAllStudentWithRegistration()
    +viewAllDetailsOfStudentById(int id)
    +updateStudentDetails(int id, String name, int branchId, int choice)
    +updateCourseFees(int studentId, int courseId, double newFeesPaid)
    +cancelRegistration(int studentId, int courseId)
    +deleteStudentCompletely(int id)
    +highPayingStudents(double amount)
    +courseWiseCountService()
}

class StudentDAO {
    +insertStudent(Connection connection, Student s) boolean
    +isStudentExist(Connection connection, int id) boolean
    +deleteStudentRecord(Connection connection, int id) boolean
    +updateName(Connection connection, int id, String name) boolean
    +updateBranch(Connection connection, int id, int branchId) boolean
}

class RegistrationDAO {
    +addCourse(Connection connection, Registration r) boolean
    +isCourseAlreadyRegistered(int studentId, int courseId, Connection connection) boolean
    +displayStudentWithCourses(Connection connection)
    +updateCourseFee(Connection connection, int studentId, int courseId, double fee) boolean
    +cancelCourseRegistration(Connection connection, int studentId, int courseId) boolean
    +deleteAllRegistrationOfAStudent(Connection connection, int studentId) boolean
    +courseWiseCount(Connection connection)
    +displayDetailsById(Connection connection, int studentId)
    +getHighPayingStudentsTotal(Connection connection, double amount)
}

class Student {
    -int id
    -String name
    -int branchId
    -int age

    +Student(int id, String name, int branchId, int age)

    +getId() int
    +getName() String
    +getBranchId() int
    +getAge() int
}

class Registration {
    -int studentId
    -int courseId
    -double feesPaid

    +Registration(int studentId, int courseId, double feesPaid)

    +getStudentId() int
    +getCourseId() int
    +getFeesPaid() double
}

class DBUtil {
    +connectionToDatabase() Connection
}

class InputValidator {
    +getValidInt(Scanner sc, String message) int
    +getValidDouble(Scanner sc, String message) double
    +getValidString(Scanner sc, String message) String
    +getBranchChoice(Scanner sc) int
    +getCourseChoice(Scanner sc) int
}

MainApp --> StudentService
MainApp --> InputValidator

StudentService --> StudentDAO
StudentService --> RegistrationDAO
StudentService --> DBUtil

StudentService --> Student
StudentService --> Registration

StudentDAO --> Student
RegistrationDAO --> Registration

Registration --> Student
```

---

# Application Workflow

```mermaid
flowchart TD

A[User Input] --> B[MainApp]
B --> C[StudentService]

C --> D[StudentDAO]
C --> E[RegistrationDAO]

D --> F[(MySQL Database)]
E --> F
```

---

# How to Run the Project

## Clone Repository

```bash
git clone <repository-url>
```

---

## Configure Database

Update MySQL database credentials inside:

```text
DBUtil.java
```

---

## Run Application

Run:

```text
MainApp.java
```

---

# Key Learning Outcomes

This project helped in understanding:

* JDBC Connectivity
* Layered Architecture
* CRUD Operations
* SQL Query Writing
* Transaction Management
* Database Normalization
* Exception Handling
* Input Validation
* Aggregation Queries

---

# Author

Harshit Srivastava

[1]: https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/creating-diagrams?utm_source=chatgpt.com "Creating Mermaid diagrams"
