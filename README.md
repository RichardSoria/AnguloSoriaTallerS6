# 📈 Taller de Métodos de Búsqueda: Gestión de Ventas

Este proyecto es una aplicación de escritorio desarrollada en Java Swing que implementa un sistema de gestión de ventas. El objetivo principal del taller es aplicar los conceptos de métodos de búsqueda (específicamente la **Búsqueda Binaria**) en un escenario práctico.

Este repositorio corresponde a la entrega de **Programación III** (Semana 6).

## 🏛️ Información Académica

* **Universidad:** Universidad de las Américas
* **Facultad:** Facultad de Ingeniería y Ciencias Aplicadas
* **Carrera:** Ingeniería en Software
* **Materia:** Programación III
* **Docente:** Ing. Paulo Guerra
* **Estudiantes:** Carlos Angulo & Richard Soria

---

## 🚀 Características Principales

La aplicación permite la gestión completa del ciclo de vida de las ventas de 3 productos de una tienda en línea.

### 1. Operaciones CRUD
* **Crear (Registrar):** Permite registrar nuevas ventas.
* **Leer (Visualizar):** Muestra todas las ventas registradas en una lista.
* **Actualizar (Editar):** Permite modificar la información de una venta existente.
* **Eliminar:** Permite eliminar un registro de venta.

### 2. Algoritmos de Búsqueda (Búsqueda Binaria)
El núcleo del proyecto es la implementación de la búsqueda binaria para localizar registros de forma eficiente:

* **Búsqueda por ID:** Se implementa una búsqueda binaria O(log n) sobre la lista principal, la cual se mantiene ordenada ascendentemente por ID gracias a una lógica de "centinela".
* **Búsqueda por Nombre:** Para no corromper el orden de la lista principal, este método crea una **copia temporal** del `ArrayList`, la ordena alfabéticamente (O(n log n)) y luego ejecuta la búsqueda binaria O(log n) sobre esa copia.

### 3. Validaciones de Interfaz (Capa de Vista)
La clase `Ventana` (Vista) es responsable de filtrar todas las entradas del usuario antes de pasarlas al controlador:
* Validación de campos vacíos.
* Validación de formato numérico (ID y Precio).
* Validación de reglas de negocio (Precio > 0, ID no repetido).
* Validación de rango de fecha (solo ventas de los últimos 3 meses).

---

## 🏛️ Arquitectura: Modelo-Vista-Controlador (MVC)

El proyecto está estructurado siguiendo el patrón de diseño **MVC** para una clara separación de responsabilidades.

### 1. Modelo (`Producto.java`)
Es el "contenedor" o plantilla de datos. Su única responsabilidad es definir la estructura de un producto (id, nombre, fecha, precio) y almacenar esta información.

### 2. Vista (`Ventana.java`)
Es la Interfaz Gráfica de Usuario (GUI). Se encarga de mostrar los componentes, capturar las entradas del usuario y ejecutar la **primera capa de validación**. No contiene lógica de negocio.

### 3. Controlador (`Venta.java`)
Es el "cerebro" de la aplicación. Se encarga de:
* Gestionar el `ArrayList` que sirve como base de datos.
* Ejecutar las operaciones CRUD (agregar, editar, eliminar).
* Implementar los algoritmos de búsqueda binaria (`buscarPorId`, `buscarPorNombre`).

---

## ⚙️ Cómo Ejecutar

1.  Clona este repositorio en tu máquina local.
2.  Abre el proyecto con **IntelliJ IDEA**.
3.  Asegúrate de tener un **JDK 25** o superior configurado.
4.  Localiza el archivo `Ventana.java` en el paquete `src`.
5.  Haz clic derecho en `Ventana.java` y selecciona `Run 'Ventana.main()'`.
