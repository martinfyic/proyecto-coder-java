<h1 align="center"><strong>Proyecto Java Coderhouse 🚀☕</strong></h1>

<p align="center">
  <img src="https://www.channelpartner.es/wp-content/uploads/2021/09/10085_21.jpg.webp" alt="Java Logo">
</p>

El Proyecto final se construye a partir de los desafíos que se realizan clase a clase. Se va creando a medida que el estudiante sube los desafíos entregables a nuestra plataforma.

El objetivo es que cada estudiante pueda utilizar su Proyecto final como parte de su portfolio personal.

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

<h2 align="center"><strong>Entrega Final 🏁</strong></h2>

### **Consigna:**

El proyecto consiste en desarrollar una aplicación que nos permita administrar las ventas de un comercio.
Para ello necesitamos centrarnos en 3 actores, el cliente quién es el que compra los productos que son los elementos que forman las ventas del comercio.

### **Objetivos generales:**

- Integrar los conocimientos vistos hasta ahora
- Poder diseñar una solución
- 
### **Objetivos específicos:**

- Entendimiento de los cambios de negocio
- Agregar validaciones
- Consumo de servicios externos
- Validación del sistema ante nuevos requerimientos

La clase que representa el servicio de la entidad Comprobante, debe tener las siguientes validaciones:

- Cliente existente
- Productos existentes
- Cantidad de productos solicitados sea menor o igual que el stock del producto
- Reducir el stock en la cantidad de unidades vendidas

Ante el cambio del precio de un producto los comprobantes ya generados que contienen este producto no deben sufrir modificaciones.

La respuesta del servicio de creación de comprobante deberá cumplir las siguientes pautas:

- La fecha del comprobante se debe obtener del servicio REST [Worldclockapi](http://worldclockapi.com/api/json/utc/now)
- En caso que el servicio falle, calcular la fecha usando la clase Date de java
- Calcular el precio total de la venta
- Calcular la cantidad de productos que se venden
- Si alguna/s validaciones no se cumple se debe informar en la respuesta

---

<h2 align="center"><strong> Estructura de la Base de Datos 📄 </strong></h2>

### **Tabla `clients`**

Esta tabla almacena información sobre los clientes.

```roomsql
CREATE TABLE coderproyect.clients (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    birthdate DATETIME(6) NOT NULL,
    doc_number varchar(11) NOT NULL,
    PRIMARY KEY (id)
);
```

Campos:

- `id`: Identificador único del cliente (clave primaria).
- `first_name`: Nombre del cliente.
- `last_name`: Apellido del cliente.
- `birthdate`: Fecha de nacimiento del cliente.
- `doc_number`: Número de documento del cliente.

### **Tabla `products`**

Esta tabla almacena información sobre los productos disponibles.

```roomsql
CREATE TABLE coderproyect.products (
    id INT NOT NULL AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(150) DEFAULT NULL,
    stock INT NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);
```

Campos:

- `id`: Identificador único del producto (clave primaria).
- `code`: Código único del producto.
- `description`: Descripción del producto (puede ser nula).
- `stock`: Cantidad en stock del producto.
- `price`: Precio unitario del producto.

### **Tabla `invoice`**

Esta tabla almacena información sobre las facturas emitidas.

```roomsql
CREATE TABLE coderproyect.invoice (
    id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    total DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id)
);
```

Campos:

- `id`: Identificador único de la factura (clave primaria).
- `client_id`: Identificador del cliente asociado a la factura (clave foránea).
- `created_at`: Fecha y hora de creación de la factura.
- `total`: Monto total de la factura.

### **Tabla `invoice_details`**

Esta tabla almacena detalles de los productos incluidos en las facturas.

```roomsql
CREATE TABLE coderproyect.invoice_details (
    id INT NOT NULL AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    unit_price DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products(id)
);
```

Campos:

- `id`: Identificador único del detalle de la factura (clave primaria).
- `invoice_id`: Identificador de la factura asociada (clave foránea).
- `product_id`: Identificador del producto incluido en la factura (clave foránea).
- `quantity`: Cantidad de unidades del producto en la factura.
- `total_price`: Precio total del producto en la factura.
- `unit_price`: Precio unitario del producto en la factura.

### Notas Adicionales

- Se han establecido relaciones clave primaria y clave foránea para mantener la integridad referencial entre las tablas.
- La tabla `invoice_details` se utiliza para almacenar los detalles específicos de los productos incluidos en cada factura.
- La base de datos está diseñada para gestionar información relacionada con clientes, productos, facturas y detalles de facturas de manera organizada y coherente.


### Script SQL 📄

Este script te permitira generar la DB junto a sus tablas con sus relaciones. [script](./src/main/resources/schema.sql)

Luego de `CREATE DATABASE coderproyect;` puedes usar el comando `USE coderproyect;` para trabajar sobre la DB creada, o al momento de generar las tables puedes usar de la siguiente manera de ejemplo `CREATE TABLE coderproyect.clients`


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