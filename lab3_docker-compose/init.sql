DROP TABLE IF EXISTS ticket_order CASCADE;
DROP TABLE IF EXISTS shopping_cart CASCADE;
DROP TABLE IF EXISTS movie CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS production CASCADE;
DROP SEQUENCE IF EXISTS production_id_seq, movie_id_seq, customer_id_seq, shopping_cart_id_seq, ticket_order_id_seq;

CREATE SEQUENCE IF NOT EXISTS production_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS movie_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS customer_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS shopping_cart_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS ticket_order_id_seq START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS production (
    id INTEGER PRIMARY KEY DEFAULT nextval('production_id_seq'),
    production_name VARCHAR(255),
    country VARCHAR(255),
    address VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS movie (
    id INTEGER PRIMARY KEY DEFAULT nextval('movie_id_seq'),
    title VARCHAR(255),
    summary TEXT,
    rating DOUBLE PRECISION,
    production_id INTEGER REFERENCES production(id)
    );

CREATE TABLE IF NOT EXISTS customer (
    id INTEGER PRIMARY KEY DEFAULT nextval('customer_id_seq'),
    username VARCHAR(255),
    full_name VARCHAR(255),
    client_password VARCHAR(255),
    date_of_birth DATE
    );


CREATE TABLE IF NOT EXISTS shopping_cart (
    id INTEGER PRIMARY KEY DEFAULT nextval('shopping_cart_id_seq'),
    user_id INTEGER REFERENCES customer(id),
    date_created TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS ticket_order (
                                            id SERIAL PRIMARY KEY,
                                            username INTEGER REFERENCES customer(id),
    client_address VARCHAR(255),
    number_of_tickets INTEGER,
    shopping_cart_id INTEGER REFERENCES shopping_cart(id),
    user_id INTEGER REFERENCES customer(id),
    movie_id INTEGER REFERENCES movie(id)
    );

-- inserting elements in tables

INSERT INTO production (production_name, country, address) VALUES
                                                               ('Production 1', 'Country A', 'Address 1'),
                                                               ('Production 2', 'Country B', 'Address 2'),
                                                               ('Production 3', 'Country C', 'Address 3');

INSERT INTO movie (title, summary, rating, production_id) VALUES
                                                              ('Movie 1', 'Summary 1', 7.5, 1),
                                                              ('Movie 2', 'Summary 2', 8.0, 2),
                                                              ('Movie 3', 'Summary 3', 6.9, 3);

INSERT INTO customer (username, full_name, client_password, date_of_birth) VALUES
                                                                ('user1', 'John, Doe', 'pass1', '1990-01-01'),
                                                                ('user2', 'Alice, Smith', 'pass2', '1985-03-15'),
                                                                ('user3', 'Bob, Johnson', 'pass3', '1995-07-20');

ALTER TABLE movie ADD COLUMN ticket_order_id INTEGER REFERENCES ticket_order(id);