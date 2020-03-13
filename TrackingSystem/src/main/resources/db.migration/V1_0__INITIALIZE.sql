CREATE TABLE Customer (
                          customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          customer_title VARCHAR(45) NOT NULL,
                          PRIMARY KEY  (customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Shipment (
                          shipment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          shipment_title VARCHAR(45) NOT NULL,
                          customer_id SMALLINT UNSIGNED,
                          PRIMARY KEY  (shipment_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Tracking_Status (
                                 status_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                 status_title VARCHAR(45) NOT NULL,
                                 shipment_id SMALLINT UNSIGNED,
                                 PRIMARY KEY  (status_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Customer (customer_id, customer_title)
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

INSERT INTO Tracking_Status (shipment_id, status_title)
VALUES (1, 'Shipped'),
       (2, 'Delivered'),
       (3, 'Cancelled'),
       (4, 'Returned'),
       (5, 'Shipped'),
       (6, 'Delivered'),
       (7, 'Shipped'),
       (8, 'Delivered');
