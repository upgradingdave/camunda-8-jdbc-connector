IF NOT EXISTS (SELECT name FROM sys.schemas WHERE name = 'camunda')
BEGIN
    EXEC ('CREATE SCHEMA [camunda] AUTHORIZATION [dbo]')
END;

IF (NOT EXISTS (SELECT *
            FROM INFORMATION_SCHEMA.TABLES
            WHERE TABLE_SCHEMA = 'camunda'
              AND  TABLE_NAME = 'users'))
BEGIN
    CREATE TABLE camunda.users
    (
        id INT NOT NULL IDENTITY(1,1),
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        email VARCHAR(255) UNIQUE,
        first_name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255) NOT NULL,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (id)
    )
END;