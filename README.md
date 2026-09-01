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

*test test*