# Primer proyecto Kafka con gradle

## Correr aplicación

- Aclaración: para los pasos que implican ejecutar comando/s en terminal, previamente se debe acceder hasta la carpeta principal de Kafka

###
1- En una terminal levantar el zookeeper con
```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
- Aclaración: con ".\bin\windows\zookeeper-server-start.bat" apuntamos al server start y le pasamos como parámetro la configuración que es lo que está en ".\config\zookeeper.properties"
###
2- En otra terminal levantar el kafka con
```bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
###
3- SOLO SI NUNCA SE HA CREADO ALGÚN TOPIC, crear uno (con CUALQUIER NOMBRE)
   --> En otra terminal crear un topic con
```bash
.\bin\windows\kafka-topics.bat --create --bootstrap-server 127.0.0.1:9092 --partitions 3 --topic CUALQUIER_NOMBRE 
```
- Aclaración: CUALQUIER_NOMBRE es el nombre que le doy al nuevo topic
###
4- Producir los mensajes
```bash
Clic secundario en la clase Test y dar a "Rum Test.main()" (esta clase llama y usa el Producer)

  (Por cada vez que se haga suma un mensaje que va a ser consumido luego por el consumer)
  (Se va a crear un topic con el nombre que le pasamos desde la clase "Test", en este ejemplo el nombre es "1")
```
###
5- En otra terminal (o en la que hice el paso 3) pongo el consumidor a consumir con
```bash
.\bin\windows\kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9092 --topic 1 --group java
```
- Aclaración: En "--topic 1", el "1" es el nombre del topic que se crea en el paso 4
###
6- Ver resultados
```bash
En la terminal del Consumer (que levanté en el punto 5) se va a ver reflejado el o los mensajes que se produjeron 
desde Producer (los datos se los paso desde la clase Test) 
```