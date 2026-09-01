# Microservicio de Clientes - Pipeline DevOps

-Estrategia de Ramificación
Para este proyecto implementamos **GitFlow**. Esta elección se basa en la necesidad de mantener la rama `main` estrictamente para código estable y listo para producción, utilizando la rama `develop` como entorno de integración principal para el trabajo diario. Esto facilita la colaboración en la nube al aislar el desarrollo de nuevas características y la resolución de errores críticos.

-Guía de Buenas Prácticas del Equipo
* **Naming de Ramas:** 
  * Nuevas funcionalidades: `feature/<nombre-breve>`
  * Corrección de errores críticos en producción: `hotfix/<nombre-del-error>`
* **Mensajes de Commit:** Se utilizará el estándar Conventional Commits.
  * Ejemplos: `feat: agregar validación de rut`, `fix: corregir error 500 en endpoint clientes`, `docs: actualizar readme`.
* **Estructura de Carpetas:** El repositorio contiene el código fuente del microservicio aislado en su propio directorio para facilitar su contenedorización.
* **Flujos de Merge y Revisiones:** Todo el código desarrollado en ramas `feature/` o `hotfix/` debe integrarse mediante un **Pull Request (PR)**. Se requiere revisión de código antes de hacer el merge hacia `develop` o `main`.


* Hotfix: Puerto por defecto configurado a 8080.
*Módulo Clientes versión 1.0.1*

# Pipeline de Integración Continua (CI/CD)
El proyecto cuenta con un workflow de automatización mediante **GitHub Actions** (`.github/workflows/ci.yml`) para garantizar la integración continua del código:

**Triggers (Desencadenadores):**
  * `push` a la rama `develop`: Ejecución automática de integración continua ante cada cambio.
  * `pull_request` a la rama `main`: Validación previa antes de fusionar código a producción.
* **Etapas del Job (`build`):**
  1. Ejecución sobre entorno virtual `ubuntu-latest`.
  2. Descarga del código fuente mediante `actions/checkout@v3`.
  3. Ejecución y simulación del pipeline de pruebas continuas.
* **Estado:** Validado y funcional con estado exitoso en la pestaña **Actions**.

---
**Declaración de uso de IA:** Para la estructuración de este proyecto, resolución de conflictos de GitFlow y configuración del pipeline de GitHub Actions, se utilizó asistencia de Inteligencia Artificial (Gemini) como herramienta de apoyo y guía técnica.

*reflexion de Cristian Retamales: la verdad estuvo complicado aprender esto pero no estuvo tan difícil, a la larga esta hasta entretenido"

*reflexion de diego silva: sste trabajo me sirvió harto para entender cómo poder automatizar procesos en la práctica usando Github. al principio me costó pillar los errores cuando fallaban los scripts, aprendí lo importante que es mantener el repositorio ordenado.
​Como autocrítica, me falta organizarme mejor desde el inicio y probar todo por partes antes de juntar el proyecto al final. En general, fue una buena experiencia para ver el impacto real de la automatización en devops "
