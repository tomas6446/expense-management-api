CREATE DATABASE impensa_server_spring;

\c impensa_server_spring

CREATE TABLE users(
                      user_id UUID,
                      user_name VARCHAR(255) NOT NULL,
                      user_email VARCHAR(255) NOT NULL UNIQUE,
                      user_currency VARCHAR(255) NOT NULL,
                      user_password VARCHAR(255) NOT NULL,
                      PRIMARY KEY (user_id)
);

CREATE TABLE expenses(
                         expense_id UUID,
                         expense_amount NUMERIC NOT NULL,
                         expense_description VARCHAR(255) NOT NULL,
                         expense_category VARCHAR(255) NOT NULL,
                         expense_date timestamptz NOT NULL DEFAULT now(),
                         user_id UUID,
                         PRIMARY KEY (expense_id),
                         FOREIGN KEY (user_id) REFERENCES users(user_id)
);

DROP DATABASE impensa_server_spring;
