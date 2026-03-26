# Biblio-UNTEC

Una aplicación web dinámica basada en Java EE para la gestión completa de una biblioteca digital. Permite administrar libros, usuarios y préstamos de manera eficiente a través de una interfaz web moderna y funcional.

## 📋 Descripción

Biblio-UNTEC es un sistema de gestión bibliotecaria desarrollado con Java EE que ofrece:

- **Gestión de Libros**: Registro, consulta y actualización del catálogo bibliográfico
- **Gestión de Usuarios**: Administración de usuarios con autenticación segura
- **Gestión de Préstamos**: Control completo del ciclo de préstamo y devolución de libros
- **Base de Datos MySQL**: Almacenamiento persistente y estructurado de datos

## 🏗️ Arquitectura Tecnológica

### Backend
- **Java 8** con **Java EE 7**
- **Servlets 3.0** para manejo de peticiones HTTP
- **CDI (Contexts and Dependency Injection)** para inyección de dependencias
- **Weld** como implementación CDI
- **JDBC** para conexión a base de datos
- **JSTL** para vistas JSP

### Base de Datos
- **MySQL 8.0** como motor de base de datos
- **MySQL Connector Java** para conectividad

### Herramientas de Desarrollo
- **Apache Maven** para gestión de dependencias y construcción
- **Tomcat 7** como servidor de aplicaciones
- **JUnit** y **Mockito** para pruebas unitarias

## 🚀 Requisitos Previos

### Software Necesario
- **Java Development Kit (JDK) 8** o superior
- **Apache Maven 3.6+**
- **MySQL Server 8.0+**
- **Git** para clonar el repositorio

### Configuración de Base de Datos

1. **Instalar y configurar MySQL Server**
2. **Crear la base de datos** ejecutando el script SQL:
   ```bash
   mysql -u root -p < biblio/untec_database.sql
   ```

   El script `untec_database.sql` incluye:
   - Creación de la base de datos `untec`
   - Estructura de tablas: `libros`, `usuarios`, `prestamos`
   - Datos de ejemplo para pruebas iniciales

## 📦 Instalación y Ejecución

### 1. Clonar el Repositorio
```bash
git clone https://github.com/MarceloBravo/Biblio-UNTEC.git
cd Biblio-UNTEC
```

### 2. Configurar Base de Datos
```bash
# Acceder a MySQL y ejecutar el script
mysql -u root -p
mysql> source biblio/untec_database.sql;
```

### 3. Compilar y Ejecutar la Aplicación
```bash
# Navegar al directorio del proyecto
cd biblio

# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación en Tomcat embebido
mvn tomcat7:run
```

### 4. Acceder a la Aplicación
Una vez iniciado el servidor, la aplicación estará disponible en:
```
http://localhost:8080/
```

## 🗂️ Estructura del Proyecto

```
biblio/
├── src/main/java/
│   ├── controllers/     # Servlets controladores
│   ├── dao/            # Data Access Objects
│   ├── dto/            # Data Transfer Objects
│   ├── entities/       # Entidades de negocio
│   ├── services/       # Lógica de negocio
│   ├── utils/          # Utilidades varias
│   └── filters/        # Filtros de aplicación
├── src/main/webapp/
│   ├── WEB-INF/        # Configuración web
│   ├── static/         # Recursos estáticos (CSS, JS, imágenes)
│   ├── home.jsp        # Página principal
│   └── index.jsp       # Página de inicio
├── pom.xml             # Configuración Maven
├── untec_database.sql  # Script de base de datos
└── README.md           # Este archivo
```

## 🎯 Funcionalidades Principales

### Gestión de Libros
- **Alta de libros**: Registro con información completa (ISBN, título, autor, editorial, etc.)
- **Consulta**: Búsqueda y visualización del catálogo
- **Actualización**: Modificación de datos existentes
- **Baja**: Eliminación de libros del sistema

### Gestión de Usuarios
- **Registro**: Creación de cuentas de usuario
- **Autenticación**: Login seguro con encriptación de contraseñas
- **Gestión**: Administración de datos de usuarios

### Gestión de Préstamos
- **Solicitud**: Generación de préstamos con fechas controladas
- **Seguimiento**: Control de fechas de devolución
- **Devolución**: Registro de devolución de libros
- **Historial**: Consulta de préstamos realizados

## 🔧 Configuración Adicional

### Variables de Entorno para Debug
Para habilitar el modo debug:
```bash
# Windows (PowerShell)
$env:MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

# Linux/Mac
export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

### Configuración de Base de Datos
La aplicación se conecta a MySQL utilizando JDBC. Asegúrate de que:
- MySQL Server esté en ejecución
- La base de datos `untec` exista
- Las credenciales de acceso sean correctas

## 🧪 Pruebas

El proyecto incluye pruebas unitarias utilizando JUnit y Mockito:

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas específicas
mvn test -Dtest=NombreDeLaClaseTest
```

## 📊 Esquema de Base de Datos

### Tablas Principales

**libros**
- `id`: Identificador único
- `isbn`: ISBN del libro (único)
- `nombre`: Título del libro
- `autor`: Autor(es)
- `editorial`: Editorial(es)
- `resumen`: Descripción del contenido
- `fecha_publicacion`: Fecha de publicación
- `idioma`: Idioma del libro
- `edicion`: Número de edición

**usuarios**
- `id`: Identificador único
- `nombre`: Nombre del usuario
- `apellidos`: Apellidos
- `email`: Correo electrónico (único)
- `password`: Contraseña encriptada

**prestamos**
- `id`: Identificador único
- `usuario_id`: Referencia al usuario
- `libro_id`: Referencia al libro
- `fecha_prestamo`: Fecha del préstamo
- `fecha_devolucion`: Fecha prevista de devolución
- `fecha_retorno`: Fecha real de devolución

## 🤝 Contribución

Este es un proyecto educativo desarrollado como parte del bootcamp de Java. Las contribuciones son bienvenidas siguiendo estos pasos:

1. Fork del repositorio
2. Crear una rama de características (`git checkout -b feature/nueva-funcionalidad`)
3. Commit de los cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📝 Licencia

Este proyecto es para fines educativos. Consulta los términos de uso del bootcamp para más información.

## 🐛 Issues y Soporte

Si encuentras algún problema o tienes sugerencias:

1. Revisa los issues existentes en el repositorio
2. Crea un nuevo issue describiendo el problema
3. Proporciona detalles sobre el entorno y pasos para reproducir

## 📚 Tecnologías Utilizadas

- **Java 8**: Lenguaje de programación principal
- **Java EE 7**: Plataforma empresarial
- **MySQL**: Sistema de gestión de base de datos
- **Maven**: Herramienta de automatización de construcción
- **Tomcat**: Servidor de aplicaciones
- **JSP/JSTL**: Tecnologías de vista
- **CDI/Weld**: Inyección de dependencias
- **JUnit/Mockito**: Framework de pruebas

---

**Desarrollado como parte del Bootcamp Java 2025**  
📧 Contacto: [Información de contacto del bootcamp]
