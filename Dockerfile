#Imagen base (java 21, Alpine Linux)
FROM eclipse-temurin:21-jre-alpine

#Directorio dentro del contenedor
WORKDIR /app

#Copia de jar de la app al contenedor
COPY target/*.jar app.jar
EXPOSE 8080
#Comando que se ejecuta al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]