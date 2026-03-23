# Proyecto Final: Hombres Lobo de Castronegro

**Integrantes del equipo:**
* Cántero Zavaleta, Héctor
* Sánchez Girón, Yael Adrian

---

## 1. Descripción del Proyecto

El presente proyecto constituye una implementación en el lenguaje de programación Java del juego de mesa "Hombres Lobo de Castronegro". El sistema fue diseñado con el propósito principal de servir como una herramienta de asistencia para el Narrador, optimizando el control y el seguimiento del estado de la partida, la gestión de los turnos (nocturnos y diurnos) y la validación de las acciones correspondientes a los diferentes roles involucrados.

Las mecánicas operativas y las reglas del juego integradas en el código se apegan estrictamente a las especificaciones detalladas en la documentación oficial provista para esta evaluación. Es importante mencionar que esta aplicación fue desarrollada para ser exclusiva de la terminal, por lo que carece de una UI.

## 2. Desarrollo y Gestión del Código

Durante el ciclo de desarrollo de este proyecto, el principal desafío técnico consistió en la integración y adopción de Git como sistema de control de versiones. Al ser nuestra primera vez con dicha tecnología, hubo una gran curva de aprendizaje. Sin embargo, el dominio del workflow permitió optimizar sustancialmente la coordinación del equipo, el historial de los cambios y la administración integral del código fuente.

## 3. Requisitos del Sistema

Para garantizar la correcta compilación y ejecución del sistema, el entorno debe cumplir con el siguiente requisito:
* **Java Development Kit (JDK)**,  en su versión **25.0.1**.

## 4. Instrucciones de Compilación y Ejecución

Para inicializar el sistema, es necesario ejecutar las siguientes instrucciones desde la interfaz de línea de comandos asegurándose de estar ubicado en el directorio raíz del código fuente (`src`).

Ejecute el siguiente comando para compilar la clase principal junto con la totalidad de sus paquetes dependientes:

```bash
javac Main.java acciones/*.java estructuras/*.java jugadores/*.java