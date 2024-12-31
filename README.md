# ForoHub Backend

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![SpringDoc](https://img.shields.io/badge/SpringDoc-6DB33F?style=for-the-badge&logo=spring&logoColor=white)


## Descripción
ForoHub Backend es una API RESTful desarrollada con Spring Boot que actúa como núcleo para una aplicación de foros. Este sistema implementa autenticación mediante JWT y permite gestionar usuarios, temas y respuestas de manera eficiente y segura.
El desarrollo de esta aplicacion es como parte del ultimo challenge de la formacion en Backend del programa Oracle Next Education, con algunas funciones extras agregadas por mi parte.

---

## Características Principales

1. **Autenticación**:
   - Login Stateless con generación de tokens JWT.
   - Seguridad implementada con filtros personalizados y BCrypt para las contraseñas.

2. **Gestión de Temas**:
   - Crear, listar, actualizar y eliminar temas.
   - Ordenación y paginación por fecha de creación.

3. **Gestión de Respuestas**:
   - Crear respuestas asociadas a un tema específico, consultable desde cualquier tema.
   - Uso de asociacion de id a respuestas.

4. **Seguridad**:
   - Filtro personalizado para validar el JWT y asociar al usuario autenticado en cada request.
   - Configuración de endpoints seguros, pero con libertad para acceder al /login.
   - Se encuentran habilitados end points para acceder a SpringDoc API, y Swagger.

---

## Endpoints Principales

### Autenticación

| Método | Endpoint  | Descripción            |
|--------|-----------|------------------------|
| POST   | `/login`  | Iniciar sesión y obtener el token JWT. |

### Gestión de Temas

| Método | Endpoint  | Descripción                                 |
|--------|-----------|---------------------------------------------|
| GET    | `/tema`   | Listar temas con paginación.               |
| POST   | `/tema`   | Crear un nuevo tema.                       |
| PUT    | `/tema`   | Actualizar un tema existente.              |
| DELETE | `/tema/{id}` | Eliminar (desactivar) un tema por su ID. |
| GET    | `/tema/{id}` | Obtener un tema específico con sus respuestas. |

### Gestión de Respuestas

| Método | Endpoint                      | Descripción                          |
|--------|-------------------------------|--------------------------------------|
| POST   | `/tema/{id}/respuestas`       | Crear una respuesta para un tema.   |

---

## Instalación y Configuración

### Requisitos
- Java 17 o superior
- Maven 3.8.1 o superior
- Base de datos MySQL

### Configuración Inicial
1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/ForoHub-BackendCRUD.git
   ```
2. Configura las credenciales de la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```
3. Instala las dependencias:
   ```bash
   mvn clean install
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---

## Tecnologías Utilizadas

- **Framework**: Spring Boot (3.3.0)
- **Base de Datos**: PostgreSQL
- **Seguridad**: JWT, Spring Security
- **Lenguaje**: Java 17

---

## Estructura del Proyecto

```plaintext
src/main/java/com/proyectofinal/forohubbackend
├── Controller
│   ├── AutenticacionController.java
│   ├── RespuestasController.java
│   └── TemaController.java
├── Domain
│   ├── Usuario
│   ├── Tema
│   └── Respuesta
├── Infra
│   └── Security
│       ├── SecurityConfiguration.java
│       ├── SecurityFilter.java
│       ├── TokenService.java
│       └── AutenticacionService.java
└── Application.properties
```

---

## Mejoras Futuras
-End Point para borrar respuestas.
-Filtro para no poder borrar posts de temas, ni de respuestas si no eres el usuario que creo el post o un rol de ADMIN.
- Integrar funcionalidades avanzadas de búsqueda por tema o palabra clave.


---

## Contribuciones

Si deseas contribuir al proyecto, por favor:
1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad (`git checkout -b feature/mi-funcionalidad`).
3. Realiza los cambios y asegúrate de que todo funciona correctamente.
4. Envía un pull request describiendo los cambios realizados.

---

## Licencia
ForoHub-BackendCRUD está bajo la Licencia MIT. Para más detalles, revisa el archivo [LICENSE](./LICENSE).
