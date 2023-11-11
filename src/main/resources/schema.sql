/*
 Script para poder crear la DB con sus respectivas tablas y relaciones
*/

DROP DATABASE IF EXISTS coderproyect;

CREATE DATABASE coderproyect;

CREATE TABLE coderproyect.clients (
	id INT AUTO_INCREMENT,
    first_name VARCHAR(75) NOT NULL,
    last_name varchar(75) NOT NULL,
    doc_number varchar(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE coderproyect.products (
	id INT AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(150),
    stock INT NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE coderproyect.invoice (
	id INT AUTO_INCREMENT,
    client_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE coderproyect.invoice_details (
	id INT AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    pirce DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products(id)
);