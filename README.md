##  Trazabilidad, Seguridad y Calidad en CI/CD

### Pipeline de Integración y Entrega Continua (GitHub Actions)
El flujo de CI/CD automatiza las siguientes fases en cada `push` o `pull_request` hacia `develop` y `main`:
1. **Pruebas Automatizadas (IE2):** Ejecución automatizada de JUnit con detención inmediata del pipeline ante fallos en pruebas[cite: 13].
2. **Medición de Cobertura (IE2):** Generación de reportes HTML/XML con JaCoCo (v0.8.13)[cite: 9, 11].
3. **Análisis de Seguridad (IE3):** Escaneo de vulnerabilidades en dependencias mediante **Snyk** y análisis estático de código en **SonarCloud**[cite: 13].
4. **Orquestación de Contenedores (IE5):** Validación y despliegue multi-contenedor con `Docker Compose` (`app` + `db` con `healthcheck` y `volumes`)[cite: 8, 13].

---

##  Declaración de Uso de Inteligencia Artificial
De acuerdo con la pauta de evaluación[cite: 7]:
* **Herramientas utilizadas:** Asistencia interactiva mediante LLM para optimización de scripts YAML, corrección de plugins en `pom.xml` y diseño de `Dockerfile` multi-stage[cite: 7].
* **Aplicación:** Guía paso a paso, depuración de errores de pipeline y formateo de documentación en Markdown[cite: 7].

---

## Reflexiones Individuales (Obligatorias)

### : [Cristian Retamales Reyes]
### personalmente siento que fue un poco mas complicado que la primera, pero dicho eso poco a poco estoy aprendiendo un poco mas cada vez

### : [Diego Silva]
### 