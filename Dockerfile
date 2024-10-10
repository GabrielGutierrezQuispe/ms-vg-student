# Usa una imagen base que incluya Java y otras dependencias necesarias
FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia todo el contenido de la carpeta target al directorio /app dentro del contenedor
COPY target/vg-ms-student-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación Spring Boot se ejecutará dentro del contenedor
EXPOSE 8082

# Define el comando para ejecutar la aplicación Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]