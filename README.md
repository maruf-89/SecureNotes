# Secure Notes – Java Console Application

Secure Notes is a Java console-based application developed as part of the course IT Security.
The project focuses on secure user management, password hashing with BCrypt, role-based access control, and safe database handling.

---

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Security Concepts](#security-concepts)
- [Project Structure](#project-structure)
- [How to Run the Project](#how-to-run-the-project)
- [Purpose of the Project](#purpose-of-the-project)
- [Author](#author)

---

## Project Description

Secure Notes is a console application where users can create and manage private notes.
The application allows users to:

- Register and log in to an account
- Create, view, edit and delete their own notes
- Change their password after logging in
- View and manage all notes in the system (admin only)

The system is built with a clear separation of responsibilities and a focus on security.

---

## Technologies Used

- Java 17
- MySQL
- Maven
- BCrypt (jBCrypt 0.4)
- IntelliJ IDEA
- Git and GitHub

---

## Security Concepts

### Password hashing with BCrypt
Passwords are never stored in plain text. BCrypt is used during registration to hash the password before it is saved to the database. During login, the entered password is compared against the stored hash.

### Protection against SQL injection
All database queries use `PreparedStatement` with parameterized queries. User input is always treated as data, never as SQL code.

### Role-based access control
The application has two roles: `USER` and `ADMIN`.
Regular users can only view and manage their own notes.
An admin can view and delete all notes in the system.

### Environment variables via .env
Database credentials are stored in a local `.env` file that is never pushed to GitHub.
A `.env.example` file is included in the repository as a template.

---

## Project Structure

```
SecureNotes/
├── sql/
│   └── setup.sql
├── src/
│   └── main/
│       └── java/
│           └── com.securenotes/
│               ├── db/
│               │   └── DatabaseConnection.java
│               ├── menu/
│               │   └── Menu.java
│               ├── notes/
│               │   └── NoteService.java
│               ├── user/
│               │   └── UserService.java
│               ├── util/
│               │   ├── Env.java
│               │   └── Pass.java
│               ├── App.java
│               └── Main.java
├── .env                  (created locally, never pushed)
├── .env.example          (pushed to GitHub as a template)
├── .gitignore
├── pom.xml
└── README.md
```

---

## How to Run the Project

**1. Clone the repository**

```bash
git clone https://github.com/maruf-89/SecureNotes.git
```

**2. Open the project in IntelliJ IDEA**

**3. Run the SQL script in MySQL Workbench**

```
Open setup.sql → select all → Ctrl+Shift+Enter
```

**4. Create a `.env` file in the project root**

```
DB_URL=jdbc:mysql://localhost:3306/securenotes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USER=root
DB_PASS=yourpassword
```

**5. Load Maven dependencies**

```
Right-click pom.xml → Maven → Reload project
```

**6. Run Main.java**

```
Right-click Main.java → Run 'Main.main()'
```

**Default admin account**

| Username | Password |
|----------|----------|
| admin    | admin123 |

---

## Purpose of the Project

This project was created to practice IT security concepts in a Java application.
The focus was on implementing secure authentication, protecting sensitive data and structuring code in a clean and maintainable way.

---

## Author

Maruf Mulk

Java Developer Student

Course: IT Security