# 🚀 API Gestor de Tareas - Spring Boot

[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](...)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](...)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](...)
[![Docker](https://img.shields.io/badge/Docker-✓-blue.svg)](...)

Una API REST para gestionar tareas personales, desarrollada con Spring.

# 📌 ¿Qué hace este proyecto?
Es el backend de una aplicación para crear, ver, actualizar y eliminar tareas. Está diseñada para ser fácil de usar 
y desplegar. Lo puedes usar como base para tu propio gestor de tareas o para aprender sobre desarrollo de APIs.

# ✅ Características implementadas
CRUD completo: Crear, Leer, Actualizar y Eliminar tareas.

Validación: Se comprueban los datos enviados (ej: título obligatorio).

Manejo de errores: Respuestas claras si algo sale mal (ej: "Tarea no encontrada").

Docker: Se puede ejecutar en un contenedor con todas sus dependencias.

Base de datos MySQL: Para guardar los datos de forma persistente.

Perfiles: Funciona con una base de datos en memoria (H2) para desarrollo y MySQL para producción.

# 🛠️ Tecnologías utilizadas
**Backend**:Java 21, Spring Boot 3

**Base de Datos**: MySQL 8 (producción), H2 (desarrollo)

**Herramientas**: Maven, Docker, Docker Compose

**Control de Versiones**: Git, GitHub

# 📡 Cómo usar la API
Puedes usar herramientas como Postman, Insomnia o curl desde la terminal para probar los siguientes endpoints:

|Qué quieres hacer        |	Método |	Endpoint  | Ejemplo de body (JSON)                                                         |
|-------------------------|--------|--------------|--------------------------------------------------------------------------------|
|Ver todas las tareas     |	GET    |	/tareas	  | -                                                                              |
|Ver una tarea específica |	GET	   |    /tareas/1 | 	-                                                                             |
|Crear una nueva tarea	  | POST   |	/tareas	  | {"titulo": "Comprar pan", "descripcion": "Ir a la panadería", "estado": false} |
|Actualizar una tarea	  | PATCH  |    /tareas/1 | {"titulo": "Comprar pan y leche", "estado": true}                              |
|Eliminar una tarea	      | DELETE |    /tareas/1 | -                                                                              | 

Ejemplo con curl para crear una tarea:
curl -X POST http://localhost:8080/tareas \
-H "Content-Type: application/json" \
-d '{"titulo": "Mi primera tarea", "descripcion": "Descripción de prueba", "estado": false}'