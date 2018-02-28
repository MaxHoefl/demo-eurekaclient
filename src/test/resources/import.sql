-- CREATE TABLE IF NOT EXISTS carts (
--    cart_id BIGINT PRIMARY KEY auto_increment
--);

--CREATE TABLE IF NOT EXISTS  items (
--    item_id BIGINT PRIMARY KEY auto_increment,
--    cart_id BIGINT REFERENCES carts (cart_id),
--    item_name VARCHAR(16)
--); 

INSERT INTO carts (cartid) VALUES (1);
	
INSERT INTO items (itemid, cartid, itemname) VALUES (1, 1, 'tooth brush');
INSERT INTO items (itemid, cartid, itemname) VALUES (2, 1, 'shampoo');
	
	