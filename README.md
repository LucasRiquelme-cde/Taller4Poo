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
* **Integrante 1:** Matías Collao - 22.060.152-8 - MatiasCollao
* **Integrante 2:** Lukas Riquelme - 21.943.208-9 - LucasRiquelme-cde

---

## Estructura del Proyecto
El código fuente se encuentra bajo el paquete principal `taller4` y se organiza lógicamente separando la lógica de negocio, el modelo de datos y la interfaz gráfica.

### Clases Principales y Lógica
* `App.java`: Punto de entrada (Main). Contiene el Login y toda la lógica del **Menú Administrador**.
* `Sistema.java`: Clase central (**Singleton**) que gestiona las listas de datos y la carga de archivos (`.txt`).
* `FactoryUsuario.java`: Clase encargada de la creación de instancias de usuarios (**Factory**).

### Modelo de Dominio
* `Usuario.java` (Abstracta): Clase padre de los roles del sistema.
* `Estudiante.java`: Contiene información académica, notas y registros.
* `Curso.java`, `Certificacion.java`, `Registro.java`, `Nota.java`: Entidades del dominio académico.

### Patrones y Lógica Adicional
* `Visitor.java` (Interfaz) y `VisitorConsejo.java`: Lógica de consejería académica (**Visitor**).
* `EstrategiaPromedio.java` (Interfaz): Define el comportamiento para cálculos de notas (**Strategy**).
* `PromedioSimple.java` y `PromedioSoloAprobadas.java`: Algoritmos concretos de cálculo.

### Interfaz Gráfica (Java Swing)
* **Coordinador:** `VentanaCoordinador` y sus sub-ventanas de gestión.
* **Estudiante:** `VentanaEstudiante`, `VentanaPerfilEstudiante`, `VentanaMallaCurricular`, `VentanaInscripcionCertificaciones`, `VentanaProgresoEstudiante`.

---

## Patrones de Diseño Implementados

El sistema implementa 4 patrones de diseño obligatorios para cumplir con los requisitos de arquitectura:

1.  **Singleton Pattern (`Sistema.java`)**:
    * **Uso:** Garantiza una única instancia de la clase `Sistema`.
    * **Justificación:** Centraliza el acceso a los datos (listas) para que todas las ventanas operen sobre la misma información en memoria.

2.  **Factory Method Pattern (`FactoryUsuario.java`)**:
    * **Uso:** Crea objetos `Administrador` o `Coordinador` según el rol leído.
    * **Justificación:** Desacopla la creación de objetos de la lógica principal, facilitando la escalabilidad de roles.

3.  **Visitor Pattern (`Visitor.java`, `VisitorConsejo.java`)**:
    * **Uso:** Implementado en `VentanaProgresoEstudiante`.
    * **Justificación:** Permite ejecutar operaciones nuevas (dar consejos) sobre objetos `Certificacion` sin modificar su estructura interna.

4.  **Strategy Pattern (`EstrategiaPromedio.java`, `PromedioSimple`, `PromedioSoloAprobadas`)**:
    * **Uso:** Implementado en `VentanaPerfilEstudiante` para calcular promedios.
    * **Justificación:** Permite intercambiar dinámicamente el algoritmo de cálculo (Promedio General vs. Solo Aprobadas) en tiempo de ejecución según la preferencia del usuario.

---

## Instrucciones de Ejecución

### Requisitos Previos
* Java Development Kit (JDK) instalado.
* IDE compatible (Eclipse recomendado).
* Codificación de archivos: UTF-8.

### Pasos para ejecutar
1.  Clonar el repositorio o importar el proyecto en Eclipse.
2.  Asegurarse de que la carpeta raíz contenga los archivos `.txt` obligatorios (`usuarios.txt`, `estudiantes.txt`, etc.).
3.  Abrir la clase `taller4.App.java`.
4.  Ejecutar como **Java Application**.

### Credenciales de Prueba
* **Admin:** Usuario: `admin` / Pass: `admin123`
* **Coordinador:** Usuario: `coord.sw` / Pass: `software789`
* **Estudiante:** Usuario: `12345678-9` / Pass: `password123`

---
*Universidad Católica del Norte - 2025*
