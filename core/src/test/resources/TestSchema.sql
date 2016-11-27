CREATE TABLE order_item (
  id INTEGER IDENTITY NOT NULL,
  phone INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  order_id INTEGER NOT NULL
);
CREATE TABLE order_table (
  id INTEGER IDENTITY NOT NULL,
  cart_price DOUBLE NOT NULL,
  total_quantity INTEGER NOT NULL,
  delivery_price DOUBLE NOT NULL,
  total_price DOUBLE NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  delivery_address VARCHAR(45) NOT NULL,
  contact_phone VARCHAR(45) NOT NULL,
  comment VARCHAR(45),
  status VARCHAR(45) NOT NULL
);
CREATE TABLE phone (
  id INTEGER NOT NULL,
  model VARCHAR(45) NOT NULL,
  price DOUBLE,
  color VARCHAR(45),
  display_size VARCHAR(45),
  camera VARCHAR(45),
  length INTEGER,
  width INTEGER,
  PRIMARY KEY (id)
);
ALTER TABLE order_item
  ADD FOREIGN KEY (phone)
REFERENCES phone (id);

ALTER TABLE order_item
  ADD FOREIGN KEY (order_id)
REFERENCES order_table (id);
