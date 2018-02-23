CREATE TABLE IF NOT EXISTS carts (
    cart_id BIGINT PRIMARY KEY auto_increment
);

CREATE TABLE IF NOT EXISTS  items (
    item_id BIGINT PRIMARY KEY auto_increment,
    cart_id BIGINT REFERENCES carts (cart_id),
    item_name VARCHAR(16)
);

