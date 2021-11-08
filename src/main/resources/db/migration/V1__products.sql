DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    price BIGINT,
    amount BIGINT
);

INSERT INTO products (title, price, amount)
VALUES
('Product1', 3000, 1),
('Product2', 1000, 1),
('Product3', 1500, 1),
('Product4', 4000, 1);