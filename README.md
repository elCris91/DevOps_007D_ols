# Microservicio de Clientes – Pipeline CI/CD (DOY0101)

Microservicio REST en **Spring Boot 4 / Java 21** que gestiona clientes (`/api/v1/clientes`), contenerizado con Docker, orquestado con Docker Compose (app + MySQL) y automatizado con un pipeline de **GitHub Actions**.

## Cómo ejecutarlo localmente

```bash
cp .env.example .env          # crear variables de entorno (credenciales de la BD)
docker compose up -d --build  # construye la imagen y levanta app + base de datos
docker compose ps             # ambos servicios deben aparecer como "healthy"
curl http://localhost:8080/actuator/health
curl http://localhost:8080/api/v1/clientes
docker compose down -v        # apagar y borrar datos
```

## Estructura del repositorio

| Archivo | Propósito |
|---|---|
| `Dockerfile` | Imagen multi-stage: compila con Maven y ejecuta solo con JRE, usuario no-root |
| `compose.yaml` | Orquesta `app` + `db` (MySQL) con healthchecks, volumen y límites de recursos |
| `.env.example` | Plantilla de variables; el `.env` real no se versiona |
| `.github/workflows/ci.yml` | Pipeline CI/CD |
| `.github/dependabot.yml` | Escaneo y actualización automática de dependencias |
| `src/main/resources/application-prod.yml` | Configuración usada dentro de contenedores (MySQL, actuator) |

## Pipeline CI/CD

Se ejecuta en cada `push` y `pull_request` hacia `develop` y `main`. Los jobs están encadenados con `needs`, así que **si una etapa falla, las siguientes no se ejecutan**.

| Etapa | Qué hace | Qué la hace fallar |
|---|---|---|
| **1. Pruebas (IE2)** | `mvn clean verify`: pruebas JUnit 5 + Mockito y reporte de cobertura JaCoCo (publicado como artefacto) | Cualquier prueba fallida |
| **2. Seguridad (IE3)** | Snyk escanea dependencias Maven; SonarCloud hace análisis estático | Snyk detecta vulnerabilidades **altas o críticas que tengan arreglo disponible** |
| **3. Build y despliegue (IE1, IE4, IE5)** | Construye la imagen etiquetada con el SHA del commit, despliega app + MySQL con Docker Compose, espera los healthchecks y ejecuta un smoke test contra la API | Un contenedor no llega a `healthy` o la API no responde |

Además, **Dependabot** revisa semanalmente las dependencias de Maven, la imagen Docker y las acciones de GitHub, y abre Pull Requests hacia `develop` cuando hay versiones nuevas o vulnerables.

### Seguridad y escalabilidad

- El contenedor de la app corre con un usuario sin privilegios (no root).
- Las credenciales de la base de datos vienen de `.env`, no están escritas en el código.
- La base de datos no expone su puerto hacia fuera: solo la app la alcanza por la red interna de Compose.
- El pipeline usa permisos mínimos (`contents: read`).
- Límites de CPU y memoria por contenedor; la JVM ajusta su memoria al límite (`MaxRAMPercentage`).
- Healthchecks reales (`/actuator/health` y `mysqladmin ping`) y `restart: unless-stopped`.
- Las vulnerabilidades sin parche disponible quedan documentadas en `.snyk` con justificación y fecha de revisión.

### Trazabilidad

- Cada imagen se etiqueta con el **SHA del commit** y lleva la etiqueta OCI `org.opencontainers.image.revision`, por lo que una imagen desplegada siempre se puede rastrear hasta el commit que la generó.
- El reporte de cobertura se guarda como artefacto con el SHA en el nombre.
- Flujo GitFlow con Pull Requests: ningún cambio llega a `main` sin pasar por el pipeline.

## Estrategia de ramificación (GitFlow)

`main` contiene solo código estable; `develop` es la rama de integración. Las nuevas funcionalidades van en `feature/<nombre-breve>` y las correcciones urgentes en `hotfix/<nombre-del-error>`, siempre integradas mediante Pull Request con revisión. Los commits siguen **Conventional Commits** (`feat:`, `fix:`, `ci:`, `docs:`, `chore:`).

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

## Declaración de uso de Inteligencia Artificial

- **Gemini (Google):** apoyo en la configuración inicial del pipeline, corrección de plugins en `pom.xml` y diseño del `Dockerfile` multi-stage.
- **Claude (Anthropic):** revisión del repositorio contra la pauta y propuesta de mejoras (Dependabot, bloqueo por seguridad, despliegue con smoke test, perfil `prod`, pruebas adicionales), diagnóstico de errores del pipeline (Snyk y permisos de `mvnw`) y migración a Spring Boot 4. Los cambios fueron revisados, probados y aplicados por el equipo.

## Reflexiones individuales

### Cristian Retamales Reyes
Personalmente siento que fue un poco más complicado que la primera, pero dicho eso poco a poco estoy aprendiendo un poco más cada vez.


### Diego Silva
la verdad tuve bastantes errores intentando arreglar algunas cosas que estaban mal y no entendia, pero me ayude con ia y siento que ahora entendi varias cosas más.