IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user1@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES ('user1', 'password1', 'user1@email.com', 'John', 'Doe')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user2@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES ('user2', 'password2', 'user2@email.com', 'Jane', 'Smith')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user3@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user3', 'password3', 'user3@email.com', 'Bob', 'Johnson')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user4@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user4', 'password4', 'user4@email.com', 'Sally', 'Williams')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user5@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user5', 'password5', 'user5@email.com', 'Tom', 'Brown')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user6@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user6', 'password6', 'user6@email.com', 'Linda', 'Jones')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user7@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user7', 'password7', 'user7@email.com', 'Alex', 'Miller')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user8@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user8', 'password8', 'user8@email.com', 'Lisa', 'Davis')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user9@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user9', 'password9', 'user9@email.com', 'David', 'Garcia')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user10@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES  ('user10', 'password10', 'user10@email.com', 'Katie', 'Martinez')
    END;

IF (NOT EXISTS (SELECT * FROM camunda.users WHERE email = 'user11@email.com'))
    BEGIN
        INSERT INTO camunda.users (username, password, email, first_name, last_name)
        VALUES ('user11', 'password11', 'user11@email.com', 'Mike', 'Robinson')
    END;












