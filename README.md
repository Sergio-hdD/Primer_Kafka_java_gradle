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
## Lo que se debe reflejar con los cambios del último commit (este commit)
### Por haber creado la clase cunsumer, se puede usar la siguiente forma
###
### A- Correr la clase Producer para producir los mensajes
```bash
En la clase Producer dar a "Rum Producer.main()"

(por cada vez que se haga suma, la cantidad indicada en el for, de mensajes que van a ser consumidos luego por el consumer)
```
A.1- Por cada vuelta del for
***
- En la consola Run del IDE del Producer se deberá ver algo como lo siguiente
```bash
Received record metadata.
Topic: sample-topic, partition: 2, Offset: 6 @ Timestamp: 1663079772903
```
***
A.2- En la consola Run del IDE del Producer, el texto terminará con el siguiente mensaje
```bash
 Execution finished ':Producer.main()'.
```

###
### B- Correr la clase Consumer para consumir los mensajes
```bash
En la clase Consumer dar a "Rum Consumer.main()"
```
B.1- Por cada mensaje que haya creado el Producer y no hayan sido consumidos
***
- En la consola Run del IDE del Consumer se deberá ver algo como lo siguiente
```bash
Received new metadata. 
Key: key_4, Value: Value_texto-mensaje_4, Topic: sample-topic, Partition: 0, Offset: 25
```
***

###
### C- Luego de hacer A y B
- El Consumer no termina (por el while true): Hay dos opciones
    - Pararlo manualmente; en este caso si se lo vuelve ajecutar, no encuentra mensjes a menos que se haga el punto A
    - Dejarlo seguir, ejecucutar de nuevo lo del punto A y ver los resultados como se indica en el punto B.1 