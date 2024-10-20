CREATE DATABASE library_jdbc;
USE library_jdbc;

CREATE TABLE books(
id VARCHAR(10) PRIMARY KEY,
title VARCHAR(255),
author VARCHAR(255),
genre VARCHAR(100),
isAvailable BOOLEAN DEFAULT TRUE,
issuedTo VARCHAR(10)
);

CREATE TABLE user(
id VARCHAR(10) PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE loans(
bookId VARCHAR(10),
userId  VARCHAR(10),
issueDate DATE,
dueDate DATE,
PRIMARY KEY(bookId,userId),
FOREIGN KEY(bookId) REFERENCES books(id),
FOREIGN KEY(userId) REFERENCES user(id)
);
SELECT * FROM books;
SELECT * FROM user;
SELECT * FROM loans;

