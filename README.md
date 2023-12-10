<h1 align="center"><strong>Proyecto Java Coderhouse 🚀☕</strong></h1>

<p align="center">
  <img src="https://res.cloudinary.com/practicaldev/image/fetch/s--PHvvmnw8--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/91ovedmu4grqhjh434rq.png" alt="Java Logo">
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

<h2 align="center"><strong> Documentación de la API REST 📄 </strong></h3>

<p align="center">
    <a href="https://documenter.getpostman.com/view/22676653/2s9Ykhfj6T#intro" target="_blank">
    <svg width="256px" height="256px" viewBox="0 0 256 256" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid">
    <g>
        <path d="M254.953118,144.253071 C263.911504,74.1217108 214.38443,10.0052669 144.381048,1.04688158 C74.3776647,-7.9115038 10.0052669,41.6155696 1.04688158,111.618952 C-7.9115038,181.622335 41.6155696,245.866756 111.618952,254.953118 C181.750312,263.911504 245.866756,214.38443 254.953118,144.253071 Z" fill="#FF6C37"></path>
        <g transform="translate(50.181225, 45.198924)">
            <path d="M124.018448,36.9853339 L70.012182,90.9916 L54.7829269,75.7623449 C107.893354,22.6519173 113.140409,27.2590869 124.018448,36.9853339 L124.018448,36.9853339 Z" fill="#FFFFFF"></path>
            <path d="M70.012182,92.2713693 C69.6282512,92.2713693 69.3722974,92.1433924 69.1163435,91.8874385 L53.7591114,76.6581834 C53.2472037,76.1462757 53.2472037,75.3784141 53.7591114,74.8665063 C107.765378,20.8602402 113.396363,25.9793176 124.78631,36.2174723 C125.042264,36.4734262 125.170241,36.72938 125.170241,37.1133108 C125.170241,37.4972416 125.042264,37.7531955 124.78631,38.0091494 L70.7800436,91.8874385 C70.6520667,92.1433924 70.2681359,92.2713693 70.012182,92.2713693 Z M56.574604,75.7623449 L70.012182,89.1999229 L122.098794,37.1133108 C112.628501,28.6668332 106.229654,26.1072945 56.574604,75.7623449 L56.574604,75.7623449 Z" fill="#FF6C37"></path>
            <path d="M85.497391,106.476809 L70.7800436,91.7594616 L124.78631,37.7531955 C139.247703,52.342566 117.619601,76.0182987 85.497391,106.476809 Z" fill="#FFFFFF"></path>
            <path d="M85.497391,107.756578 C85.1134602,107.756578 84.8575064,107.628601 84.6015525,107.372648 L69.8842051,92.6553001 C69.6282512,92.3993463 69.6282512,92.1433924 69.6282512,91.7594616 C69.6282512,91.3755308 69.7562282,91.1195769 70.012182,90.8636231 L124.018448,36.857357 C124.530356,36.3454492 125.298217,36.3454492 125.810125,36.857357 C129.137525,39.9288034 130.929203,44.2800191 130.801226,48.7592118 C130.545272,62.9646515 114.420178,81.0093992 86.5212065,107.372648 C86.1372757,107.628601 85.7533449,107.756578 85.497391,107.756578 L85.497391,107.756578 Z M72.5717207,91.7594616 C80.7622445,100.077962 84.2176217,103.405363 85.497391,104.685132 C106.997516,84.2088225 127.857756,63.2206053 127.985733,48.7592118 C128.11371,45.4318115 126.833941,42.1044113 124.658333,39.5448726 L72.5717207,91.7594616 Z" fill="#FF6C37"></path>
            <path d="M55.0388808,76.1462757 L65.9169201,87.024315 C66.172874,87.2802689 66.172874,87.5362228 65.9169201,87.7921767 C65.7889432,87.9201536 65.7889432,87.9201536 65.6609663,87.9201536 L43.1370259,92.7832771 C41.9852335,92.911254 40.961418,92.1433924 40.7054642,90.9916 C40.5774872,90.3517153 40.8334411,89.7118307 41.2173719,89.3278999 L54.2710192,76.2742526 C54.526973,76.0182987 54.9109038,75.8903218 55.0388808,76.1462757 Z" fill="#FFFFFF"></path>
            <path d="M42.7530951,94.0630464 C40.8334411,94.0630464 39.4256948,92.5273232 39.4256948,90.6076692 C39.4256948,89.7118307 39.8096256,88.8159921 40.4495103,88.1761075 L53.5031576,75.1224602 C54.2710192,74.4825755 55.2948346,74.4825755 56.0626962,75.1224602 L66.9407356,86.0004996 C67.7085972,86.6403842 67.7085972,87.7921767 66.9407356,88.5600383 C66.6847817,88.8159921 66.4288279,88.9439691 66.0448971,89.071946 L43.5209567,93.9350695 C43.2650028,93.9350695 43.009049,94.0630464 42.7530951,94.0630464 L42.7530951,94.0630464 Z M54.65495,77.5540219 L42.1132104,90.0957615 C41.8572566,90.3517153 41.7292796,90.7356461 41.9852335,91.1195769 C42.1132104,91.5035077 42.4971412,91.6314847 42.881072,91.5035077 L63.9972661,86.8963381 L54.65495,77.5540219 Z" fill="#FF6C37"></path>
            <path d="M152.557304,7.03873136 C144.366781,-0.895838537 131.185156,-0.639884669 123.250587,7.67861603 C115.316017,15.9971167 115.57197,29.050764 123.890471,36.9853339 C130.673249,43.5121575 140.911403,44.6639499 148.97395,39.8008264 L134.38458,25.211456 L152.557304,7.03873136 Z" fill="#FFFFFF"></path>
            <path d="M138.223888,44.0240653 C126.066079,44.0240653 116.211855,34.1698413 116.211855,22.0120326 C116.211855,9.85422391 126.066079,-1.81866161e-14 138.223888,-1.81866161e-14 C143.854873,-1.81866161e-14 149.357881,2.17560788 153.453143,6.14289283 C153.709097,6.39884669 153.837074,6.65480056 153.837074,7.03873136 C153.837074,7.42266217 153.709097,7.67861603 153.453143,7.9345699 L136.176257,25.211456 L149.741812,38.777011 C150.25372,39.2889187 150.25372,40.0567803 149.741812,40.568688 C149.613835,40.696665 149.613835,40.696665 149.485858,40.8246419 C146.158458,42.8722729 142.191173,44.0240653 138.223888,44.0240653 Z M138.223888,2.68751561 C127.473825,2.68751561 118.771394,11.3899471 118.899371,22.1400096 C118.899371,32.890072 127.601802,41.5925035 138.351865,41.4645266 C141.295334,41.4645266 144.238804,40.8246419 146.926319,39.4168956 L133.488741,26.1072945 C133.232787,25.8513406 133.10481,25.5953868 133.10481,25.211456 C133.10481,24.8275252 133.232787,24.5715713 133.488741,24.3156174 L150.63765,7.1667083 C147.182273,4.22323882 142.831057,2.68751561 138.223888,2.68751561 L138.223888,2.68751561 Z" fill="#FF6C37"></path>
            <path d="M152.941235,7.42266217 L152.685281,7.1667083 L134.38458,25.211456 L148.845973,39.6728495 C150.25372,38.777011 151.661466,37.7531955 152.813258,36.6014031 C161.003782,28.5388563 161.003782,15.485209 152.941235,7.42266217 L152.941235,7.42266217 Z" fill="#FFFFFF"></path>
            <path d="M148.97395,41.0805958 C148.590019,41.0805958 148.334066,40.9526188 148.078112,40.696665 L133.488741,26.1072945 C133.232787,25.8513406 133.10481,25.5953868 133.10481,25.211456 C133.10481,24.8275252 133.232787,24.5715713 133.488741,24.3156174 L151.661466,6.14289283 C152.173374,5.63098509 152.941235,5.63098509 153.453143,6.14289283 L153.837074,6.39884669 C162.411528,14.9733013 162.411528,28.7948101 153.965051,37.4972416 C152.685281,38.777011 151.277535,39.9288034 149.741812,40.8246419 C149.357881,40.9526188 149.101927,41.0805958 148.97395,41.0805958 L148.97395,41.0805958 Z M136.176257,25.211456 L149.101927,38.1371263 C150.125743,37.4972416 151.149558,36.6014031 151.91742,35.8335415 C159.212105,28.5388563 159.596036,16.6370014 152.557304,8.95838537 L136.176257,25.211456 Z" fill="#FF6C37"></path>
            <path d="M126.194056,39.2889187 C123.12261,36.2174723 118.131509,36.2174723 115.060063,39.2889187 L66.8127587,87.5362228 L74.8753055,95.5987696 L125.938102,50.8068428 C129.265502,47.9913502 129.521456,43.0002498 126.705964,39.6728495 C126.45001,39.5448726 126.322033,39.4168956 126.194056,39.2889187 L126.194056,39.2889187 Z" fill="#FFFFFF"></path>
            <path d="M74.7473286,96.878539 C74.3633978,96.878539 74.1074439,96.750562 73.85149,96.4946082 L65.7889432,88.4320613 C65.2770355,87.9201536 65.2770355,87.152292 65.7889432,86.6403842 L114.036247,38.3930802 C117.619601,34.809726 123.378563,34.809726 126.961918,38.3930802 C130.545272,41.9764343 130.545272,47.7353963 126.961918,51.3187505 C126.833941,51.4467274 126.705964,51.5747044 126.577987,51.7026813 L75.5151902,96.4946082 C75.3872133,96.750562 75.1312594,96.878539 74.7473286,96.878539 L74.7473286,96.878539 Z M68.6044358,87.5362228 L74.8753055,93.8070925 L125.042264,49.7830273 C127.857756,47.4794425 128.11371,43.2562037 125.810125,40.4407111 C123.50654,37.6252186 119.283302,37.3692647 116.467809,39.6728495 C116.339832,39.8008264 116.211855,39.9288034 115.955901,40.0567803 L68.6044358,87.5362228 Z" fill="#FF6C37"></path>
            <path d="M29.8274248,142.438327 C29.3155171,142.694281 29.0595632,143.206189 29.1875401,143.718097 L31.363148,152.932436 C31.8750557,154.212205 31.1071941,155.747929 29.6994479,156.131859 C28.6756324,156.51579 27.52384,156.131859 26.8839553,155.363998 L12.8064926,141.414512 L58.7502118,95.4707927 L74.6193516,95.7267466 L85.3694141,106.476809 C82.8098754,108.652417 67.3246664,123.625718 29.8274248,142.438327 L29.8274248,142.438327 Z" fill="#FFFFFF"></path>
            <path d="M28.8036093,157.411629 C27.7797938,157.411629 26.7559784,157.027698 26.1160937,156.259836 L12.1666079,142.31035 C11.910654,142.054397 11.7826771,141.798443 11.7826771,141.414512 C11.7826771,141.030581 11.910654,140.774627 12.1666079,140.518673 L58.1103272,94.5749541 C58.366281,94.3190003 58.7502118,94.1910233 59.0061657,94.1910233 L74.8753055,94.4469772 C75.2592363,94.4469772 75.5151902,94.5749541 75.7711441,94.830908 L86.5212065,105.58097 C86.7771604,105.836924 86.9051373,106.220855 86.9051373,106.604786 C86.9051373,106.988717 86.7771604,107.244671 86.3932296,107.500624 L85.497391,108.268486 C71.931836,120.170341 53.5031576,132.072196 30.5952864,143.462143 L32.7708943,152.548505 C33.1548251,154.212205 32.3869635,156.003882 30.8512403,156.899721 C30.0833787,157.283652 29.443494,157.411629 28.8036093,157.411629 Z M14.7261466,141.414512 L27.9077708,154.468159 C28.2917016,155.108044 29.0595632,155.363998 29.6994479,154.980067 C30.3393325,154.596136 30.5952864,153.828275 30.2113556,153.18839 L28.0357477,143.974051 C27.7797938,142.822258 28.2917016,141.798443 29.3155171,141.286535 C51.9674343,129.896588 70.2681359,118.12271 83.705714,106.476809 L74.2354208,97.0065159 L59.5180734,96.750562 L14.7261466,141.414512 Z" fill="#FF6C37"></path>
            <path d="M1.9284532,152.420528 L12.9344695,141.414512 L29.3155171,157.795559 L3.20822254,156.003882 C2.05643013,155.875905 1.28856853,154.85209 1.41654546,153.700298 C1.41654546,153.18839 1.5445224,152.676482 1.9284532,152.420528 L1.9284532,152.420528 Z" fill="#FFFFFF"></path>
            <path d="M29.3155171,158.947352 L3.0802456,157.155675 C1.16059159,157.027698 -0.119177745,155.363998 0.00879918845,153.444344 C0.136776122,152.676482 0.39272999,151.908621 1.03261466,151.396713 L12.038631,140.390696 C12.5505387,139.878789 13.3184003,139.878789 13.830308,140.390696 L30.2113556,156.771744 C30.5952864,157.155675 30.7232633,157.667583 30.4673095,158.17949 C30.2113556,158.691398 29.8274248,158.947352 29.3155171,158.947352 L29.3155171,158.947352 Z M12.9344695,143.206189 L2.82429173,153.316367 C2.44036093,153.572321 2.44036093,154.212205 2.82429173,154.468159 C2.95226867,154.596136 3.0802456,154.724113 3.33619947,154.724113 L25.9881168,156.259836 L12.9344695,143.206189 Z" fill="#FF6C37"></path>
            <path d="M54.2710192,101.357732 C53.5031576,101.357732 52.9912498,100.717847 52.9912498,100.077962 C52.9912498,99.6940315 53.1192268,99.4380776 53.3751806,99.1821238 L65.7889432,86.7683612 C66.3008509,86.2564534 67.0687125,86.2564534 67.5806203,86.7683612 L75.6431671,94.830908 C76.0270979,95.2148388 76.1550749,95.5987696 76.0270979,96.1106774 C75.899121,96.4946082 75.5151902,96.878539 75.0032825,97.0065159 L54.526973,101.357732 C54.3989961,101.357732 54.2710192,101.357732 54.2710192,101.357732 L54.2710192,101.357732 Z M66.6847817,89.4558768 L58.2383041,97.9023544 L72.059813,94.9588849 L66.6847817,89.4558768 Z" fill="#FF6C37"></path>
            <path d="M74.6193516,95.7267466 L60.5418889,98.798193 C59.5180734,99.0541468 58.494258,98.4142622 58.2383041,97.3904467 C58.1103272,96.750562 58.2383041,96.1106774 58.7502118,95.5987696 L66.5568048,87.7921767 L74.6193516,95.7267466 Z" fill="#FFFFFF"></path>
            <path d="M60.2859351,100.077962 C58.494258,100.077962 57.0865117,98.670216 57.0865117,96.878539 C57.0865117,95.9827004 57.4704425,95.2148388 57.9823502,94.5749541 L65.7889432,86.7683612 C66.3008509,86.2564534 67.0687125,86.2564534 67.5806203,86.7683612 L75.6431671,94.830908 C76.0270979,95.2148388 76.1550749,95.5987696 76.0270979,96.1106774 C75.899121,96.4946082 75.5151902,96.878539 75.0032825,97.0065159 L60.9258197,100.077962 C60.6698659,100.077962 60.413912,100.077962 60.2859351,100.077962 L60.2859351,100.077962 Z M66.6847817,89.4558768 L59.7740273,96.3666312 C59.5180734,96.6225851 59.5180734,96.878539 59.6460504,97.1344928 C59.7740273,97.3904467 60.0299812,97.5184236 60.413912,97.5184236 L72.1877899,94.9588849 L66.6847817,89.4558768 Z" fill="#FF6C37"></path>
            <path d="M153.069212,19.7084478 C152.813258,18.9405862 151.91742,18.5566554 151.149558,18.8126093 C150.381697,19.0685632 149.997766,19.9644017 150.25372,20.7322633 C150.25372,20.8602402 150.381697,20.9882172 150.381697,21.1161941 C151.149558,22.6519173 150.893604,24.5715713 149.869789,25.9793176 C149.357881,26.6192023 149.485858,27.5150408 149.997766,28.0269485 C150.63765,28.5388563 151.533489,28.4108793 152.045397,27.7709947 C153.965051,25.3394329 154.348981,22.2679865 153.069212,19.7084478 L153.069212,19.7084478 Z" fill="#FF6C37"></path>
        </g>
    </g>
</svg>
</a>
</p>

[Postman JSON](./postman.json)

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

## Base URL - Invoices 💵

`api/v1/invoices`

## Endpoints 🔗

### 🌐 Obtener todas las facturas

**Endpoint:**

`GET /`

**Descripción:**

Este endpoint devuelve una lista de todas las facturas

**Parámetros de consulta:**

- Ninguno

**Ejemplo de respuesta exitosa:**

```json
[
  {
    "id": 1,
    "clientId": 2,
    "createdAt": "2023-12-10T03:14:50.000+00:00",
    "details": [
      {
        "productId": 1,
        "quantity": 2,
        "unitPrice": 32.5,
        "totalPrice": 65
      },
      {
        "productId": 2,
        "quantity": 5,
        "unitPrice": 87.5,
        "totalPrice": 437.5
      }
    ],
    "total": 502.5
  },
  {
    "id": 2,
    "clientId": 3,
    "createdAt": "2023-12-10T03:14:55.000+00:00",
    "details": [
      {
        "productId": 1,
        "quantity": 5,
        "unitPrice": 32.5,
        "totalPrice": 162.5
      },
      {
        "productId": 2,
        "quantity": 1,
        "unitPrice": 87.5,
        "totalPrice": 87.5
      }
    ],
    "total": 250
  }
]
```

### 🌐 Obtener una factura en específico

**Endpoint:**

`GET /{id}`

**Descripción:**

Este endpoint devuelve información sobre una factura en específico.

**Parámetros de consulta:**

- `{id}`: Identificador único de la factura.

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 2,
  "clientId": 3,
  "createdAt": "2023-12-10T03:14:55.000+00:00",
  "details": [
    {
      "productId": 1,
      "quantity": 5,
      "unitPrice": 32.5,
      "totalPrice": 162.5
    },
    {
      "productId": 2,
      "quantity": 1,
      "unitPrice": 87.5,
      "totalPrice": 87.5
    }
  ],
  "total": 250
}
```

### 🌐 Crear una factura

**Endpoint:**

`POST /`

**Descripción:**

Este endpoint permite generar una nueva factura.

**Parámetros del cuerpo de la solicitud:**

- `clientId` (integer): id del cliente que realiza la compra.
- `details` (list): lista de detalles, donde cada detalle tiene un `productId` (id del producto) y una `quantity` (cantidad del producto seleccionado).

**Ejemplo de cuerpo de solicitud:**

```json
{
  "clientId": 2,
  "details": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 5
    }
  ]
}
```

**Ejemplo de respuesta exitosa:**

```json
{
  "id": 1,
  "clientId": 2,
  "createdAt": "2023-12-10T03:14:50.000+00:00",
  "details": [
    {
      "productId": 1,
      "quantity": 2,
      "unitPrice": 32.5,
      "totalPrice": 65
    },
    {
      "productId": 2,
      "quantity": 5,
      "unitPrice": 87.5,
      "totalPrice": 437.5
    }
  ],
  "total": 502.5
}
```

> [NOTA] el atributo `createdAt` utiliza una api externa [Worldclockapi](http://worldclockapi.com/api/json/utc/now), en el caso de que falle se setea con un `new Date()`