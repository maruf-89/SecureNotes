CREATE DATABASE IF NOT EXISTS securenotes;

USE securenotes;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(20)
);

CREATE TABLE notes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(username, password, role)
VALUES ('admin', 'temp', 'ADMIN');