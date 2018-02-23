-- CREATE TABLE IF NOT EXISTS carts (
--    cart_id BIGINT PRIMARY KEY auto_increment
--);

--CREATE TABLE IF NOT EXISTS  items (
--    item_id BIGINT PRIMARY KEY auto_increment,
--    cart_id BIGINT REFERENCES carts (cart_id),
--    item_name VARCHAR(16)
--); 

INSERT INTO carts (cart_id) VALUES (1);
INSERT INTO carts (cart_id) VALUES (2);
INSERT INTO carts (cart_id) VALUES (3);
	
INSERT INTO items (item_id, cart_id, item_name) VALUES (1, 1, 'tooth brush');
INSERT INTO items (item_id, cart_id, item_name) VALUES (2, 1, 'shampoo');
INSERT INTO items (item_id, cart_id, item_name) VALUES (3, 2, 'body wash');
	
	