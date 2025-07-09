# 🛠️ API Examen - Gestión de Órdenes y Conductores

Este proyecto es una API REST desarrollada en **Spring Boot** con autenticación JWT. Permite gestionar **órdenes de transporte**, **conductores** y **asignaciones con archivos adjuntos**.

---

## 🔧 Requisitos

- Java 17 o superior
- Maven 3.8+
- Docker y Docker Compose (opcional para ejecución)

---

## ⚙️ Configuración

La configuración por defecto se encuentra en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/apiexamen
spring.datasource.username=root
spring.datasource.password=tu_contraseña
jwt.secret=secreto123
```

> 💡 **Importante:** Al arrancar la aplicación se crea automáticamente un usuario administrador por medio del seeder:

- **Usuario:** `admin`
- **Contraseña:** `admin123`

---

## 🚀 Ejecución del Proyecto

### Opción 1: Ejecutar Localmente

```bash
./mvnw spring-boot:run
```

La API quedará disponible en:  
📍 `http://localhost:8080`

---

### Opción 2: Ejecutar con Docker (Recomendado)

```bash
docker-compose up --build
```

Esto levantará automáticamente:

- La aplicación en `localhost:8080`
- La base de datos MySQL

> Asegúrate de que el archivo `docker-compose.yml` esté en la raíz del proyecto y configurado correctamente.

---

## ✅ Pruebas Unitarias

Para ejecutar las pruebas:

```bash
./mvnw test -Dtest=*Test
```

Incluye pruebas para servicios como creación de órdenes y conductores usando **JUnit 5** y **Mockito**.

---

## 📂 Endpoints Principales

| Método | Endpoint                    | Descripción                              |
|--------|-----------------------------|------------------------------------------|
| POST   | `/auth/login`               | Autenticación JWT                        |
| POST   | `/orders`                   | Crear orden de transporte                |
| GET    | `/orders`                   | Buscar órdenes con filtros               |
| PUT    | `/orders/{id}/status`       | Actualizar estado de la orden            |
| POST   | `/orders/{id}/assign`       | Asignar conductor + adjuntos             |
| POST   | `/drivers`                  | Crear conductor                          |
| GET    | `/drivers`                  | Listar conductores activos               |

---

## 🛡️ Seguridad

Todos los endpoints (excepto `/auth/login`) requieren **Bearer Token JWT** en el header:

```http
Authorization: Bearer <tu-token>
```

---

## 📚 Documentación Swagger

La API cuenta con documentación interactiva generada automáticamente con **SpringDoc OpenAPI**.

Puedes acceder a ella desde tu navegador en:

🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## ✍️ Autor

Luis Enrique Guzmán Ramírez  
🗓️ Julio 2025
