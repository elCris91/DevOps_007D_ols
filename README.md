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

### Seguridad y