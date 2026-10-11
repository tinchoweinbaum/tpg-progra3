# Proyecto Nave Asistida

Realizado por *Martin Weinbaum , Juan Pablo Gueli , Lisandor Mazzucco , y Gonzalo Bounzerio*
Proyecto final de la materia **Programacion C** de la Universidad Nacional de Mar del Plata, dictada por el profesor *Leonel Guccione*.


## Introducción

El **Asistente de Navegación y Comando** es un software embarcado diseñado para ayudar a la tripulación de naves interestelares a **administrar subsistemas, controlar recursos (combustible/energía/desgaste) y registrar eventos** en misiones de largo alcance. El sistema opera bajo reglas y autorizaciones preestablecidas sin reemplazar la autoridad del capitán, encargándose de **automatizar acciones permitidas,  y validar que la nave cuente con los recursos y el mantenimiento adecuados** antes de cada operación en entornos imprevistos e incomunicados.

## Primeros pasos

>Para esta primer entrega se ejecutaran comandos desde el main.java

Se debe crear una primer instancia del **Sistema** donde se desarrollara el programa, implementando el siguiente comando:
```java
	Sistema system = Sistema.getInstance();
```
Una vez hecho esto, ya podremos acceder a los elementos y metodos que hemos creado

## 1. Creación de la tupla Asistente-Nave

>Un Asistente solo existe con su nave, y una nave no puede existir sin un asistente

Para usar las naves, completar misiones e interactuar con una tripulacion debemos ejecutar el armado de la **Nave** y su **Asistente**
```java
	Asistente asist = Sistema.inicio(tipoNave);
```
*tipoNave* sera un parametro de tipo **String** que indicará que tipo se creara, los valores permitidos son:
* Carguero
* Explorador
* Combate

>De elegirse un tipo alternativo el programa indicara Error

| Descripción| Método|
| :--- | :---: |
|  |  |
|  |  |
|  |  |
|  |  |
|  |  |
|  |  |

## 2. Creación de las Misiones

>Para esta primer entrega solo realizaran ciertos pasos e imprimirán carteles en la consola. Se espera implementar más funcionalidades y características para la 2da entrega

Para crear una mision se debe llamar a la funcion que las crea, indicandole un tipo válido
```java
	Mision m1 = Sistema.creaMision(tipoMision);
```
*tipoMision* sera un parametro de tipo **int** que indicará que tipo se creara, los valores permitidos son:
* 1 - Intercepción y asistencia
* 2 - Recolección
* 3 - Retorno seguro

>De elegirse un tipo alternativo el programa indicara Error

Para poder realizar la mision se deberá llamar a un asistente y así realizar el chequeo de recursos y disponibilidad de la nave
```java
	m1.ejecutarMision(asist1);
```
En consola se mostrará el avance, si es que el asistente indica que puede realizar la misión, o mostrará el motivo por el que la rechazó

## 3. Creación de la Tripulación
Para crear un tripulante se dede llamar a la funcion que las crea, indicandole un cargo y un orígen válido
```java
	Tripulante t1 = sistema.creaTripulante(tipoCargo,tipoOrigen,nombre,añosAntiguedad);
```
*tipoCargo* sera un parametro de tipo **String** que indicará el Rol que tendrá el tripulante en la nave, los valores permitidos son:
* Capitan , puede existir uno solo por nave
* Teniente
* Alferez
* Consejero

*tipoOrigen* sera un parametro de tipo **String** que indicará la especie que tendrá el tripulante en la nave, los valores permitidos son:
* Terrícola
* Vulcano
* Marciano
>De elegirse un tipo alternativo el programa indicara Error
