CREATE DATABASE cargodelivery;
SET NAMES UTF8;
CREATE TABLE IF NOT EXISTS cities(
  id         INTEGER AUTO_INCREMENT PRIMARY KEY ,
  name       VARCHAR(30)   NOT NULL
);
INSERT INTO cities (id, name)
VALUES (DEFAULT, 'Винница'),
       (DEFAULT, 'Киев'),
       (DEFAULT, 'Львов'),
       (DEFAULT, 'Харьков'),
       (DEFAULT, 'Одесса');

CREATE TABLE IF NOT EXISTS users(
  id         INTEGER AUTO_INCREMENT PRIMARY KEY ,
  name       VARCHAR(40)   NOT NULL ,
  surname    VARCHAR(40)   NOT NULL ,
  city       VARCHAR(40)   NOT NULL ,
  phone      VARCHAR(40)   NOT NULL ,
  mail       VARCHAR(40)   NOT NULL ,
  password   VARCHAR(200)  NOT NULL ,
  role       INTEGER  NOT NULL
);

INSERT INTO users (id, name , surname, city, phone, mail, password, role)
VALUES (DEFAULT, 'Максим', 'Барко', 'Винница', '+380672121212', 'bar@gmail.com', '1111', 1),
       (DEFAULT, 'Борис', 'Марлечук', 'Киев', '+380675151515', 'mar@gmail.com', '2222', 1);

SELECT u.name, u.surname, u.city, u.phone, u.mail, u.password, u.role
FROM users AS u
WHERE u.id = (?);

SELECT u.id, u.name, u.surname, u.city, u.phone, u.password, u.role
FROM users AS u
WHERE u.mail = (?);

CREATE TABLE IF NOT EXISTS distance(
  firstCity          VARCHAR(8)   NOT NULL ,
  secondCity         VARCHAR(8)   NOT NULL ,
  distance           INTEGER  NOT NULL ,
  delivery_term      INTEGER  NOT NULL
);

INSERT INTO distance (firstCity, secondCity , distance, delivery_term)
VALUES ('2', '1', 270, 2),
       ('3', '1', 380, 2),
       ('3', '2', 560, 3),
       ('4', '1', 740, 3),
       ('4', '2', 480, 2),
       ('4', '3', 1050, 3),
       ('5', '1', 440, 2),
       ('5', '2', 500, 2),
       ('5', '3', 820, 3),
       ('5', '4', 700, 3);

SELECT DISTINCT d.distance FROM distance AS d
WHERE (d.firstCity = (?) AND d.secondCity = (?)) OR (d.firstCity = (?) AND d.secondCity = (?));

CREATE TABLE IF NOT EXISTS orders(
  id                 INTEGER AUTO_INCREMENT PRIMARY KEY ,
  userId             INTEGER       NOT NULL ,
  createDate         VARCHAR(20)   NOT NULL ,
  cityFrom           VARCHAR(30)   NOT NULL ,
  cityTo             VARCHAR(30)   NOT NULL ,
  orderType          VARCHAR(30)   NOT NULL ,
  weight             INTEGER       NOT NULL ,
  startDate          VARCHAR(30)   NOT NULL ,
  endDate            VARCHAR(30)   NOT NULL ,
  recipient          VARCHAR(100)  NOT NULL ,
  recipientPhone     VARCHAR(30)   NOT NULL ,
  deliveryAddress    VARCHAR(100)  NOT NULL ,
  price              INTEGER       NOT NULL
);

INSERT INTO orders (id, userId , createDate, cityFrom, cityTo, orderType, weight, startDate, endDate, recipient,
                      recipientPhone, deliveryAddress, price)
VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?));

CREATE TABLE IF NOT EXISTS company(
  id                 INTEGER AUTO_INCREMENT PRIMARY KEY ,
  name               VARCHAR(30)   NOT NULL ,
  address            VARCHAR(40)   NOT NULL ,
  codEDRPOU          VARCHAR(20)   NOT NULL ,
  codINN             VARCHAR(20)   NOT NULL ,
  bank               VARCHAR(20)   NOT NULL ,
  mfo                VARCHAR(10)   NOT NULL ,
  account            VARCHAR(20)   NOT NULL
);
INSERT INTO company (id, name , address, codEDRPOU, codINN, bank, mfo, account)
VALUES (DEFAULT, 'OOO "Cargo Delivery"', 'г.Винница, ул.Короленко 15/113',
'33944031', '339440326570', 'ПАТ "ОТП Банк"', '300528', '26006455017522');