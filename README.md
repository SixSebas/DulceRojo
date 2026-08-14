# 🍪 DulceRojo — Sistema de Pedidos en Línea

Sistema web transaccional desarrollado con Spring Boot para gestionar el catálogo y pedidos del emprendimiento de repostería artesanal *Dulce Rojo*.

---

## 📋 Descripción

DulceRojo es un emprendimiento costarricense dedicado a la elaboración y venta de galletas artesanales estilo New York. Este sistema centraliza la gestión de productos, usuarios y pedidos que anteriormente se manejaban por Instagram y WhatsApp.

---

## 🌐 Sistema en producción

*URL pública:* https://dulcerojo.onrender.com

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java JDK | 25 | Lenguaje principal del backend |
| Spring Boot | 4.0.7 | Framework principal |
| Thymeleaf | 3.x | Motor de plantillas para vistas |
| Bootstrap | 5.3.3 | Diseño responsivo |
| Spring Security | 6.x | Autenticación y autorización |
| Hibernate / JPA | 7.x | Persistencia de datos |
| MySQL | 9.6 | Base de datos relacional |
| Bean Validation | 3.x | Validaciones de formularios |
| i18n | — | Internacionalización español/inglés |
| Docker | — | Contenedor para despliegue |
| Git & GitHub | — | Control de versiones |
| Render.com | — | Despliegue en producción |
| Aiven | — | Base de datos MySQL en la nube |

---

## 👥 Equipo de desarrollo

| Nombre | GitHub |
|---|---|
| Sebastián Martínez | @SixSebas |
| Andrey Obando | @aobando-cr |
| Rafael Benavides | @KenRamirez97 |
| Josué Espino | @Josue53 |

---

## ✅ Funcionalidades implementadas

- 🔐 Login y registro con Spring Security + BCrypt
- 👥 Roles ADMIN y USER con rutas protegidas
- 🍪 CRUD completo de productos con categorías
- 🛒 Carrito de compras y módulo de pedidos
- 📦 Control de stock al confirmar pedidos
- 🌐 Internacionalización español/inglés
- 🔌 API REST (/api/productos)
- 📱 Diseño responsivo con Bootstrap 5

---

## 🚀 Cómo correr el proyecto

### Pre-requisitos
- Java JDK 25
- MySQL 8+
- Maven (incluido en el proyecto con mvnw)

### 1. Clonar el repositorio
bash
git clone https://github.com/SixSebas/DulceRojo.git
cd DulceRojo/dulcerojo


### 2. Crear la base de datos
sql
CREATE DATABASE dulcerojodb CHARACTER SET utf8mb4;


### 3. Configurar la contraseña
powershell
setx DB_PASSWORD "tu_password_de_mysql"

Cerrá y abrí una terminal nueva para que tome efecto.

### 4. Arrancar la app
powershell
.\mvnw.cmd spring-boot:run


### 5. Cargar datos de prueba
Ejecutar seed-data.sql en MySQL Workbench.

### 6. Crear usuario administrador
Registrarse en /registro y luego ejecutar:
sql
UPDATE usuarios SET rol = 'ADMIN' WHERE correo = 'tu@correo.com';


### 7. Verificar
- http://localhost:8080 — Página principal
- http://localhost:8080/productos — Catálogo de galletas
- http://localhost:8080/login — Iniciar sesión
- http://localhost:8080/api/productos — API REST

---

## 🐳 Correr con Docker

bash
docker-compose up --build


Esto levanta MySQL y la app automáticamente sin configurar nada.

---

## 📁 Estructura del proyecto

El proyecto sigue la arquitectura MVC en capas estándar de Spring Boot:
- *controller/* — manejo de peticiones HTTP
- *entity/* — modelos de datos con JPA
- *repository/* — acceso a la base de datos
- *service/* — lógica de negocio
- *templates/* — vistas Thymeleaf con Bootstrap
- *config/* — configuración de Spring Security e i18n

---

## 🔌 API REST

| Método | Endpoint | Descripción |
|---|---|---|
| GET | /api/productos | Listar todos los productos |
| GET | /api/productos/{id} | Ver detalle de un producto |
| POST | /api/productos | Crear producto |
| PUT | /api/productos/{id} | Actualizar producto |
| DELETE | /api/productos/{id} | Eliminar producto |

Colección Postman disponible en postman-collection.json.

---

## 📦 Entregables

| Entregable | Semana | Estado |
|---|---|---|
| Avance 1 — Historias de Usuario y Prototipo | Semana 5 | ✅ Entregado |
| Avance 2 — 50% del Proy…