# etapa 1: compilar el proyecto con maven
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
# copiamos el pom primero para que docker guarde en cache las dependencias
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
# los tests se corren en el pipeline, aca no
RUN mvn -B clean package -DskipTests

# etapa 2: imagen final, solo con java (mas liviana)
FROM eclipse-temurin:21-jre-alpine

# guardamos el commit con el que se armo la imagen
ARG GIT_SHA=local
LABEL org.opencontainers.image.title="microservicio-clientes" \
      org.opencontainers.image.revision="${GIT_SHA}"

WORKDIR /app

# usuario sin permisos de root
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build /app/target/*.jar app.jar
USER spring

# java usa como max el 75% de la ram del contenedor
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
