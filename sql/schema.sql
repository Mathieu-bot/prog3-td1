CREATE TABLE product (
    id INTEGER PRIMARY KEY ,
    name VARCHAR(100),
    price NUMERIC(10,2),
    creation_datetime TIMESTAMP
);

CREATE TABLE product_category (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100),
    product_id INTEGER REFERENCES product(id)
);