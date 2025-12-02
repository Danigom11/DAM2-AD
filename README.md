# 📚 DAM2 - Acceso a Datos

Repositorio con todos los ejercicios, exámenes y trabajos realizados en **2º de Desarrollo de Aplicaciones Multiplataforma** (DAM) para la asignatura de **Acceso a Datos**.

## 📖 Descripción

Este proyecto contiene prácticas y ejercicios enfocados en el manejo de datos en diferentes formatos y el uso de conectores a bases de datos. Los temas principales incluyen:

- **Manipulación de archivos XML**
- **Manipulación de archivos JSON**
- **Conversión entre formatos (XML ↔ JSON)**
- **Conexión y gestión de bases de datos**
- **Patrón DAO (Data Access Object)**

## 🗂️ Estructura del Proyecto

```
src/main/java/org/example/
├── Tema2/
│   ├── PrimeraConexion.java
│   ├── PracticaVuelosPasajeros/
│   │   └── Main.java
│   ├── SimulacroExamen/
│   │   ├── Ejercicio1/ (XML ↔ JSON)
│   │   ├── Ejercicio2/ (Modificar JSON)
│   │   └── Ejercicio3/ (ProductoDAO)
│   ├── SimulacroExamen2/
│   │   └── GestorDepartamentosXML.java
│   └── SimulacroExamenInvertido/
│       ├── JsonToXml.java
│       └── ModificarXml.java
└── Tema3/
    └── (Próximamente)
```

## 🛠️ Tecnologías Utilizadas

- **Java** (JDK 11+)
- **Maven** - Gestión de dependencias
- **JSON** - Procesamiento de archivos JSON
- **XML** - Procesamiento de archivos XML
- **JDBC** - Conectores de bases de datos
- **IntelliJ IDEA** - IDE de desarrollo

## 📋 Contenido por Temas

### Tema 2: BBDD y Conectores

#### Prácticas
- **PrimeraConexion.java**: Primeros pasos con JDBC
- **PracticaVuelosPasajeros**: Gestión de vuelos y pasajeros

#### Simulacros de Examen
- **Ejercicio 1**: Conversión de XML a JSON con libros
- **Ejercicio 2**: Modificación de archivos JSON de personas
- **Ejercicio 3**: Implementación del patrón DAO para productos
- **GestorDepartamentosXML**: Gestión de departamentos en XML
- **SimulacroExamenInvertido**: Conversión de JSON a XML y modificación

### Tema 3
🚧 _Próximamente_

## 🚀 Cómo Ejecutar

### Requisitos Previos
- Java JDK 11 o superior
- Maven 3.6+

### Compilar el Proyecto
```bash
mvn clean compile
```

### Ejecutar una Clase Específica
```bash
mvn exec:java -Dexec.mainClass="org.example.Tema2.[Clase]"
```

## 📝 Archivos de Datos

El proyecto incluye varios archivos de datos de ejemplo:
- `libros.xml` / `libros.json`
- `persona.json` / `persona_mod.json`
- `productos.xml` / `productos.json`
- `empleados.xml` / `empleados_final.xml`

## 👨‍💻 Autor

**Daniel Gómez**  
2º DAM - Acceso a Datos  
Curso 2024/2025

## 📄 Licencia

Este proyecto es de uso educativo y personal.

---

_Actualizado: Diciembre 2024_

