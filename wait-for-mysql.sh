#!/bin/sh
echo "Esperando a que MySQL esté listo..."
until nc -z -v -w30 mysql 3306
do
  echo "Esperando a la base de datos MySQL..."
  sleep 2
done
echo "MySQL está listo, iniciando la app Spring Boot..."

# 👇 Agrega esto para ver errores si el .jar falla
ls -l
echo "Ejecutando: java -jar app.jar"
exec java -jar app.jar
