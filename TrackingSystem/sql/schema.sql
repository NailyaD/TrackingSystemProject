DROP SCHEMA IF EXISTS tracking;
CREATE SCHEMA tracking;
USE tracking;

CREATE TABLE Customer (
                          customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          customer_name VARCHAR(45) NOT NULL,
                          PRIMARY KEY  (customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Shipment (
                          shipment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          shipment_title VARCHAR(45) NOT NULL,
                          customer_id SMALLINT UNSIGNED,
                          PRIMARY KEY  (shipment_id),
                          FOREIGN KEY (customer_id) REFERENCES Customer (customer_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Status (
                          tracking_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          status_title ENUM ('Shipped', 'Delivered', 'Cancelled', 'Returned'),
                          shipment_id SMALLINT UNSIGNED,
                          PRIMARY KEY  (tracking_id),
                          FOREIGN KEY (shipment_id) REFERENCES Shipment (shipment_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Customer (customer_id, customer_name)
VALUES (1,'Ivan Ivanov'),
       (2,'Piotr Petrov'),
       (3,'Mikhail Mikhailov'),
       (4,'Andrey Andreyev');

INSERT INTO Shipment (shipment_id, shipment_title, customer_id)
VALUES (1, 'Shipment', 1),
       (2, 'Letter', 2),
       (3, 'Letter', 1),
       (4, 'Postal packet', 1),
       (5, 'Shipment', 3),
       (6, 'Shipment', 2),
       (7, 'Postal packet', 3),
       (8, 'Shipment', 4);

INSERT INTO Status (tracking_id, status_title, shipment_id)
VALUES (1, 'Shipped', 1),
       (2, 'Delivered', 2),
       (3, 'Cancelled', 3),
       (4, 'Returned', 4),
       (5, 'Shipped', 5),
       (6, 'Delivered', 6),
       (7, 'Shipped', 7),
       (8, 'Delivered', 8);

/*Запросы*/
/*Получить все отправления клиента*/

select * from Shipment where customer_id = 1;

/*Или можно так*/

select c.customer_name, s.shipment_title
from Customer as c join Shipment as s
on c.customer_id = s.customer_id where c.customer_name = 'Ivan Ivanov';

/*получить последний статус отправления*/
select * from Status where shipment_id = 2 order by tracking_id desc limit 1;
