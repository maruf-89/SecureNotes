DROP DATABASE IF EXISTS securenotes;

CREATE DATABASE securenotes;

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
VALUES ('admin', '$2a$10$j4thHipE8071xKLQNw0oSupKZL7wUt.s6r/cIwVWXHc6GX.YGvfLu', 'ADMIN');