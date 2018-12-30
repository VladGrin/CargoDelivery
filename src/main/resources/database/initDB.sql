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
  name       VARCHAR(50)   NOT NULL ,
  surname    VARCHAR(50)   NOT NULL ,
  city       VARCHAR(50)   NOT NULL ,
  phone      VARCHAR(50)   NOT NULL ,
  mail       VARCHAR(50)   NOT NULL ,
  password   VARCHAR(200)   NOT NULL
);

INSERT INTO users (id, name , surname, city, phone, mail, password)
VALUES (DEFAULT, 'Max', 'Barski', 'Vinnitsa', '+380672121212', 'bar@gmail.com', '1111'),
       (DEFAULT, 'Bob', 'Marli', 'Kiev', '+380675151515', 'mar@gmail.com', '2222');

SELECT u.name, u.surname, u.city, u.phone, u.mail, u.password
FROM users AS u
WHERE u.id = (?);

SELECT u.id, u.name, u.surname, u.city, u.phone, u.password
FROM users AS u
WHERE u.mail = (?);
