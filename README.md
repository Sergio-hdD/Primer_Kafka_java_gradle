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
3- En otra terminal crear un topic con
```bash
.\bin\windows\kafka-topics.bat --create --bootstrap-server 127.0.0.1:9092 --partitions 3 --topic sample-topic
```
- Aclaración: "sample-topic" es el nombre que le doy al nuevo topic
###
4- En otra terminal (o en la que hice el paso 3) pongo el consumidor a consumir con
```bash
.\bin\windows\kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9092 --topic sample-topic --group java
```
###
5- Correr la clase Producer
```bash
En la clase Producer dar a "Rum Producer.main()"
```
###
## Lo que se debe reflejar con los cambios del último commit (este commit)
- En la consola del consumidor (levantada en el paso 4)
```bash
Se va a ver el texto que puse (en la clase Producer) como tercer parámetro en 'new ProducerRecord<>("sample-topic", "key1", "Value_texto-mensaje1");'
```
- En la consola Run del IDE se deberá ver algo como lo siguiente
```bash
Received record metadata.
Topic: sample-topic, partition: 2, Offset: 6 @ Timestamp: 1663079772903
```
- En la consola Run del IDE, el texto terminará con el siguiente mensaje
```bash
 Execution finished ':Producer.main()'.
```