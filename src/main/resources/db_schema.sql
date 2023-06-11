CREATE DATABASE internet_shop;

CREATE TABLE internet_shop.product (
     id INT AUTO_INCREMENT,
     name VARCHAR(150) NOT NULL,
     price INT NOT NULL,
     description VARCHAR(300),
     product_type_id INT NOT NULL,
     PRIMARY KEY (id),
     UNIQUE (name),
     FOREIGN KEY (product_type_id) REFERENCES product_type(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE internet_shop.product_type (
  id INT AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(150),
  PRIMARY KEY (id),
  UNIQUE (name)
);

CREATE TABLE internet_shop.users (
   id INT AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   password VARCHAR(70) NOT NULL,
   type ENUM('CLIENT', 'ADMINISTRATOR') NOT NULL,
   blocked BOOLEAN NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE internet_shop.shopping_cart (
   id INT AUTO_INCREMENT,
   user_id INT NOT NULL,
   product_id INT NOT NULL,
   quantity INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE RESTRICT,
   FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

-- Rollback
-- DROP TYPE user_type;
-- DROP TABLE shopping_cart;
-- DROP TABLE users;
-- DROP TABLE product_type;
-- DROP TABLE product;
