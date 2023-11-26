<h1 align="center"><strong>Proyecto Java Coderhouse 🚀☕</strong></h1>

<p align="center">
  <img src="https://www.channelpartner.es/wp-content/uploads/2021/09/10085_21.jpg.webp" alt="Java Logo">
</p>

El Proyecto final se construye a partir de los desafíos que se realizan clase a clase. Se va creando a medida que el estudiante sube los desafíos entregables a nuestra plataforma.

El objetivo es que cada estudiante pueda utilizar su Proyecto final como parte de su portfolio personal.

### **Consigna:**

El proyecto consiste en desarrollar una aplicación que nos permita administrar las ventas de un comercio.
Para ello necesitamos centrarnos en 3 actores, el cliente quién es el que compra los productos que son los elementos que forman las ventas del comercio.

---

<h2 align="center"><strong>Primer pre entrega 1️⃣</strong></h2>

Entregar el proyecto con las clases que forman parte de la solución correspondiente a la primera entrega de tu proyecto final.

### **Objetivos generales:**

- Integrar los conocimientos vistos hasta ahora
- Poder diseñar una solución

### **Objetivos específicos:**

- Establecer las entidades que representan la solución del proyecto
- Poder generar los scripts para modelar la base de datos de acuerdo al diseño de las clases.

---

<h2 align="center"><strong>Segunda pre entrega 2️⃣</strong></h2>

Consiste en tomar el proyecto de la primera entrega para convertirlo en un proyecto Spring Boot el cual utilice alguna librería de jpa para poder conectarse a la base de datos.

### **Objetivos generales:**

- Tomar el proyecto de la primera entrega para convertirlo en un proyecto Spring Boot
- Utilizar alguna librería de jpa para poder conectarse a la base de datos.

### **Objetivos específicos:**

- Cada entidad definida en la primera entrega debe usar la arquitectura de 3 capas para ser manipulada.
- Se espera que las modificaciones se realicen en cascada.
- Generar los scripts de inicialización de datos  para las tablas creadas en la primera entrega

---

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

Este script te permitira generar la DB junto a sus tablas con sus relaciones. [script](./src/main/resources/schema.sql)

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
---

<h2 align="center"><strong> Documentación de la API REST 📄 </strong></h2>

## Base URL - Clients 👤

`api/v1/clients`

## Endpoints 🔗

### 🌐 Obtener todos los clientes

**Endpoint:**

`GET /`

**Descripción:**

Este endpoint devuelve una lista de todos los clientes.

**Parámetros de consulta:**

- Ninguno

**Ejemplo de respuesta exitosa:**

```json
[
  {
    "id": 1,
    "firstName": "Jon",
    "lastName": "Doe",
    "birthdate": "1989-09-18T03:00:00.000+00:00",
    "docNumber": "97429348"
  },
  {
    "id": 2,
    "firstName": "Luis",
    "lastName": "Flores",
    "birthdate": "1989-10-03T03:00:00.000+00:00",
    "docNumber": "25643456"
  },
  {
    "id": 3,
    "firstName": "Mariana ",
    "lastName": "Jordan",
    "birthdate": "2000-10-03T03:00:00.000+00:00",
    "docNumber": "25643456"
  }
      // ... otros clientes
]
```

### 🌐 Obtener un cliente en específico

**Endpoint:**

`GET /{id}`

**Descripción:**

Este endpoint devuelve información sobre un cliente específico.

**Parámetros de consulta:**

- `{id}`: Identificador único del cliente.

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 1,
  "firstName": "Jon",
  "lastName": "Doe",
  "birthdate": "1989-09-18T03:00:00.000+00:00",
  "docNumber": "97429348"
}
```

### 🌐 Obtener un cliente por su id y devuelve su edad

**Endpoint:**

`GET /{id}/years-old`

**Descripción:**

Este endpoint devuelve el nombre, apellido y edad sobre un cliente específico.

**Parámetros de consulta:**

- `{id}`: Identificador único del cliente.

**Ejemplo de respuesta exitosa:**

```json
{
  "firstName": "Jon",
  "lastName": "Doe",
  "yearsOld": 34
}
```

### 🌐 Crear un cliente

**Endpoint:**

`POST /`

**Descripción:**

Este endpoint permite agregar un nuevo cliente a la colección.

**Parámetros del cuerpo de la solicitud:**

- `firstName` (string): Nombre del cliente.
- `lastName` (string): Apellido del cliente.
- `birthdate` (date): Año de nacimiento del cliente.
- `docNumber` (string): Nro de DNI.

**Ejemplo de cuerpo de solicitud:**

```json
{
  "firstName": "Bruce ",
  "lastName": "Wayne ",
  "birthdate": "2005-10-04",
  "docNumber": "25643456"
}
```

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 5,
  "firstName": "Bruce ",
  "lastName": "Wayne ",
  "birthdate": "2005-10-04T00:00:00.000+00:00",
  "docNumber": "25643456"
}
```

### 🌐 Actualizar información de un cliente

**Endpoint:**

`PUT /{id}`

**Descripción:**
Este endpoint permite actualizar la información de un cliente, se pueden actualizar toda la información o solo la seleccionada.

**Parámetros de ruta:**

- `{id}`: Identificador único del cliente.

**Parámetros del cuerpo de la solicitud:**

- Cualquier parámetro que desees actualizar (puede incluir `firstName`, `lastName`, `birthdate`, `docNumber`).

**Ejemplo de cuerpo de solicitud:**

```json
{
  "firstName": "Clark",
  "lastName": "Kent"
}
```

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 5,
  "firstName": "Clark",
  "lastName": "Kent",
  "birthdate": "2005-10-04T00:00:00.000+00:00",
  "docNumber": "25643456"
}
```

### 🌐 Eliminar un cliente

**Endpoint:**

`DELETE /{id}`

**Descripción:**

Este endpoint permite eliminar un cliente de la colección.

**Parámetros de ruta:**

- `{id}`: Identificador único del cliente.

**Ejemplo de respuesta exitosa:**

```json
{
  "status": "success",
  "message": "Cliente con ID 3 eliminado correctamente."
}
```

## Base URL - Products 📦

`api/v1/products`

## Endpoints 🔗

### 🌐 Obtener todos los productos

**Endpoint:**

`GET /`

**Descripción:**

Este endpoint devuelve una lista de todos los productos.

**Parámetros de consulta:**

- Ninguno

**Ejemplo de respuesta exitosa:**

```json
[
  {
    "id": 1,
    "code": "JEAN001",
    "description": "Pantalon jean skin",
    "stock": 20,
    "price": 3200
  },
  {
    "id": 3,
    "code": "TSHIRT",
    "description": "Remera clasica blanca",
    "stock": 40,
    "price": 1200
  }
      // ... otros clientes
]
```

### 🌐 Obtener un producto en específico

**Endpoint:**

`GET /{id}`

**Descripción:**

Este endpoint devuelve información sobre un producto específico.

**Parámetros de consulta:**

- `{id}`: Identificador único del producto.

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 1,
  "code": "JEAN001",
  "description": "Pantalon jean skin",
  "stock": 20,
  "price": 3200
}
```

### 🌐 Crear un producto

**Endpoint:**

`POST /`

**Descripción:**

Este endpoint permite agregar un nuevo producto a la colección.

**Parámetros del cuerpo de la solicitud:**

- `code` (string): Codigo del producto.
- `description` (string): Descripcion del producto.
- `stock` (integer): Cantidad disponible del producto.
- `price` (double): Precio del producto.

**Ejemplo de cuerpo de solicitud:**

```json
{
  "code": "CAT2023001",
  "description": "Gorro clasico beisbol",
  "stock": 25,
  "price": 1030.65
}
```

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 4,
  "code": "CAT2023001",
  "description": "Gorro clasico beisbol",
  "stock": 25,
  "price": 1030.65
}
```

### 🌐 Actualizar información de un Producto

**Endpoint:**

`PUT /{id}`

**Descripción:**
Este endpoint permite actualizar la información de un producto, se pueden actualizar toda la información o solo la seleccionada.

**Parámetros de ruta:**

- `{id}`: Identificador único del Producto.

**Parámetros del cuerpo de la solicitud:**

- Cualquier parámetro que desees actualizar (puede incluir `code`, `description`, `stock`, `price`).

**Ejemplo de cuerpo de solicitud:**

```json
{
  "description": "Gorro deportivo beisbol",
  "stock": 8,
  "price": 1199.98
}
```

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 4,
  "code": "CAT2023001",
  "description": "Gorro deportivo beisbol",
  "stock": 8,
  "price": 1199.98
}
```

### 🌐 Eliminar un producto

**Endpoint:**

`DELETE /{id}`

**Descripción:**

Este endpoint permite eliminar un producto de la colección.

**Parámetros de ruta:**

- `{id}`: Identificador único del producto.

**Ejemplo de respuesta exitosa:**

```json
{
  "status": "success",
  "message": "Producto con ID 4 eliminado correctamente."
}
```