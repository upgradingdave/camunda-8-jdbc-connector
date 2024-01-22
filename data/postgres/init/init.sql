CREATE DATABASE camunda;
\c camunda

CREATE TABLE users
(
    id SERIAL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- USERS

INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user1', 'password1', 'user1@email.com', 'John', 'Doe', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user2', 'password2', 'user2@email.com', 'Jane', 'Smith', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user3', 'password3', 'user3@email.com', 'Bob', 'Johnson', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user4', 'password4', 'user4@email.com', 'Sally', 'Williams', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user5', 'password5', 'user5@email.com', 'Tom', 'Brown', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user6', 'password6', 'user6@email.com', 'Linda', 'Jones', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user7', 'password7', 'user7@email.com', 'Alex', 'Miller', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user8', 'password8', 'user8@email.com', 'Lisa', 'Davis', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user9', 'password9', 'user9@email.com', 'David', 'Garcia', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user10', 'password10', 'user10@email.com', 'Katie', 'Martinez', NOW());
INSERT INTO users (username, password, email, first_name, last_name, created_at)
VALUES ('user11', 'password11', 'user11@email.com', 'Mike', 'Robinson', NOW());