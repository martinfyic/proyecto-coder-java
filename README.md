# **Proyecto Java Coderhouse 🚀☕**

![Frontend Web Developer MERN stack](https://www.channelpartner.es/wp-content/uploads/2021/09/10085_21.jpg.webp)

El Proyecto final se construye a partir de los desafíos que se realizan clase a clase. Se va creando a medida que el estudiante sube los desafíos entregables a nuestra plataforma.

El objetivo es que cada estudiante pueda utilizar su Proyecto final como parte de su portfolio personal.

### **Consigna:**

El proyecto consiste en desarrollar una aplicación que nos permita administrar las ventas de un comercio.
Para ello necesitamos centrarnos en 3 actores, el cliente quién es el que compra los productos que son los elementos que forman las ventas del comercio.

---

## **Primer pre entrega 1️⃣**

Entregar el proyecto con las clases que forman parte de la solución correspondiente a la primera entrega de tu proyecto final.

### **Diagrama de Relaciones de las Tablas 📄**

Aquí tienes un diagrama representa las relaciones de las tablas:

#### Tabla: clients
- `id` (PK) INT
- `first_name` VARCHAR(75)
- `last_name` VARCHAR(75)
- `birthdate` DATE
- `doc_number` VARCHAR(11)

#### Tabla: products
- `id` (PK) INT
- `code` VARCHAR(50)
- `description` VARCHAR(150)
- `stock` INT
- `price` DOUBLE

#### Tabla: invoice
- `id` (PK) INT
- `client_id` INT
- `created_at` DATETIME
- `total` DOUBLE
- (FK) `client_id` -> clients(id)

#### Tabla: invoice_details
- `id` (PK) INT
- `invoice_id` INT
- `product_id` INT
- `quantity` INT
- `price` DOUBLE
- (FK) `invoice_id` -> invoice(id)
- (FK) `product_id` -> products(id)

### Script SQL 📄

Este script te permitira generar la DB junto a sus tablas con sus relaciones.

Luego de `CREATE DATABASE coderproyect;` puedes usar el comando `USE coderproyect;` para trabajar sobre la DB creada, o al momento de generar las tables puedes usar de la siguiente manera de ejemplo `CREATE TABLE coderproyect.clients`

```roomsql
DROP DATABASE IF EXISTS coderproyect;

CREATE DATABASE coderproyect;

USE coderproyect;

CREATE TABLE clients (
	id INT AUTO_INCREMENT,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    birthdate DATE NOT NULL,
    doc_number VARCHAR(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products (
	id INT AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(150),
    stock INT NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE invoice (
	id INT AUTO_INCREMENT,
    client_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE invoice_details (
	id INT AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    pirce DOUBLE NOT NULL, 
    PRIMARY KEY (id),
    CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products(id)
);
```