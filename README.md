# AcademiCore - Sistema de Gestión de Certificaciones Profesionales

## Descripción del Proyecto
**AcademiCore** es un sistema académico integral desarrollado en Java diseñado para digitalizar y automatizar la gestión de certificaciones profesionales complementarias a la formación de grado.

El sistema resuelve la problemática de la gestión manual de inscripciones y seguimiento, proporcionando una plataforma unificada para tres tipos de usuarios:
* **Administradores:** Gestionan cuentas de usuarios y seguridad.
* **Coordinadores:** Administran líneas de certificación, generan documentos oficiales y analizan métricas de rendimiento.
* **Estudiantes:** Visualizan su malla curricular, se inscriben en certificaciones y monitorean su progreso académico en tiempo real.

Este proyecto fue desarrollado para la asignatura **Programación Orientada a Objetos** (Taller 4), implementando una arquitectura en capas y patrones de diseño avanzados.

---

## Integrantes
* **Integrante 1:** Matías Collao - 22060152-8 - MatiasCollao
* **Integrante 2:** Lukas Riquelme - [RUT] - LucasRiquelme-cde

---

## Estructura del Proyecto
El código fuente se encuentra bajo el paquete principal `taller4` y se organiza lógicamente separando la lógica de negocio, el modelo de datos y la interfaz gráfica.

### Clases Principales y Lógica
* `App.java`: Punto de entrada de la aplicación (Main). Inicia el login y la interfaz gráfica.
* `Sistema.java`: Clase central (**Singleton**) que gestiona las listas de datos y la carga de archivos (`.txt`).
* `FactoryUsuario.java`: Clase encargada de la creación de instancias de usuarios (**Factory**).

### Modelo de Dominio
* `Usuario.java` (Clase Abstracta): Padre de `Administrador`, `Coordinador` y `Estudiante` (aunque Estudiante tiene su propia estructura compleja).
* `Estudiante.java`: Contiene la información académica, notas y registros del alumno.
* `Curso.java`: Representa las asignaturas con sus créditos, semestre y prerrequisitos.
* `Certificacion.java`: Define las líneas de certificación disponibles.
* `Registro.java`: Vincula a un estudiante con una certificación y su estado.
* `Nota.java`: Almacena el historial académico y calificaciones.

### Patrones y Lógica Adicional
* `Visitor.java` (Interfaz) y `VisitorConsejo.java`: Implementación del patrón Visitor para lógica de consejería.

### Interfaz Gráfica (Java Swing)
* **Login:** Gestionado en `App.java`.
* **Coordinador:** `VentanaCoordinador`, `VentanaModificarCertificacion`, `VentanaGenerarCertificados`, `VentanaEstadisticasInscripciones`, `VentanaAsignaturasCriticas`, `VentanaConsultarEstudiante`, `VentanaValidarAvance`.
* **Estudiante:** `VentanaEstudiante`, `VentanaPerfilEstudiante`, `VentanaMallaCurricular`, `VentanaInscripcionCertificaciones`, `VentanaProgresoEstudiante`.
* **Administrador:** `VentanaAdministrador`.

---

## Patrones de Diseño Implementados

El sistema implementa los siguientes patrones de diseño para cumplir con los requisitos de arquitectura y buenas prácticas:

1.  **Singleton Pattern (`Sistema.java`)**:
    * **Uso:** Garantiza que exista una única instancia de la clase `Sistema` durante toda la ejecución.
    * **Justificación:** Centraliza el acceso a las listas de datos (Estudiantes, Cursos, Certificaciones) y asegura que todos las ventanas accedan y modifiquen la misma información en memoria.

2.  **Factory Method Pattern (`FactoryUsuario.java`)**:
    * **Uso:** Centraliza la creación de objetos `Usuario` (`Administrador` o `Coordinador`) basándose en el rol leído desde el archivo de texto.
    * **Justificación:** Desacopla la lógica de creación de objetos de la lógica de negocio principal, facilitando la escalabilidad si se añaden nuevos roles.

3.  **Visitor Pattern (`Visitor.java`, `VisitorConsejo.java`)**:
    * **Uso:** Implementado en el menú de Estudiante (`VentanaProgresoEstudiante`).
    * **Justificación:** Permite agregar nuevas operaciones (como "Dar consejo personalizado") a las estructuras de objetos (`Certificacion`) sin modificar las clases sobre las que opera. Separa el algoritmo de los objetos.

---

## Instrucciones de Ejecución

### Requisitos Previos
* Java Development Kit (JDK) instalado.
* IDE compatible (Eclipse recomendado).
* Codificación de archivos: UTF-8.

### Pasos para ejecutar
1.  Clonar el repositorio o importar el proyecto en Eclipse.
2.  Asegurarse de que la carpeta del proyecto contenga en la raíz los archivos de datos obligatorios:
    * `usuarios.txt`
    * `estudiantes.txt`
    * `cursos.txt`
    * `Certificaciones.txt`
    * `Registros.txt`
    * `Notas.txt`
    * `Asignaturas_certificaciones.txt`
3.  Abrir la clase `taller4.App.java`.
4.  Ejecutar como **Java Application**.

### Credenciales de Prueba (Ejemplos)
* **Admin:** Usuario: `admin` / Pass: `admin123`
* **Coordinador:** Usuario: `coord.sw` / Pass: `software789`
* **Estudiante:** Usuario: `12345678-9` / Pass: `password123`

---
*Universidad Católica del Norte*
