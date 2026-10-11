
# Proyecto Nave Asistida

Realizado por *Martin Weinbaum , Juan Pablo Gueli , Lisandro Mazzucco , y Gonzalo Bounzerio* <br>
Proyecto final de la materia **Programacion C** de la Universidad Nacional de Mar del Plata, dictada por el profesor *Leonel Guccione*.


## Introducción

El **Asistente de Navegación y Comando** es un software embarcado diseñado para ayudar a la tripulación de naves interestelares a **administrar subsistemas, controlar recursos (combustible/energía/desgaste) y registrar eventos** en misiones de largo alcance. El sistema opera bajo reglas y autorizaciones preestablecidas sin reemplazar la autoridad del capitán, encargándose de **automatizar acciones permitidas,  y validar que la nave cuente con los recursos y el mantenimiento adecuados** antes de cada operación en entornos imprevistos e incomunicados.

## Primeros pasos

>Para esta primer entrega se ejecutaran comandos desde el main.java

Se debe crear una primer instancia del **Sistema** donde se desarrollara el programa, implementando el siguiente comando:
```java
	Sistema system = Sistema.getInstance();
```
Una vez hecho esto, ya podremos acceder a los elementos y métodos que hemos creado

## 1. Creación de la tupla Asistente-Nave

>Un Asistente solo existe con su nave, y una nave no puede existir sin un asistente

Para usar las naves, completar misiones e interactuar con una tripulación debemos ejecutar el armado de la **Nave** y su **Asistente**
```java
	Asistente asist = Sistema.inicio(tipoNave);
```
*tipoNave* será un parámetro de tipo **String** que indicará que tipo se creara, los valores permitidos son:
* Carguero
* Explorador
* Combate

>De elegirse un tipo alternativo el programa indicara Error

### Métodos principales del Asistente
| Descripción| Método|
| --- | --- |
|Obtiene la referencia a la nave vinculada|asistente.getNave()  |
|Obtiene la referencia al motor de la nave |asistente.getMotor()|
|Intenta llevar el motor a un estado|asistente.setEstadoMotor(int estado)|
|Realiza el *Salto Warp* con el motor, luego retoma el estado *Disponible*|asistente.saltar()  |
|Obtiene las bitacoras de la nave|asistente.getBitacorasNave()|
|Cargar combustible|asistente.cargaCombustibleNave(float carga) |
|Llena el tanque de combustible|asistente.cargaCombustibleNaveFull()|
|Realiza el mantenimiento de la nave, llevando el desgaste a cero|asistente.reparaDesgasteNaveFull()|
|Carga energía a la nave|asistente.cargaEnergiaNave(float carga)|
|Carga al 100% la energía de la nave|asistente.cargaEnergianaveFull()|
|Muestra toda la bitácora de la nave|asistente.muestraBitacora()|
|Muestra los últimos N registros de la bitácora|asistente.muestraNBitacoras(int N)|
|Agregar uno o varios tripulantes.Puede pasarse como parámetro un solo objeto o un ArrayList de varios objetos *Tripulante*|asistente.agregaTripulante(ArrayList< Tripulante > / Tripulante tripulante)|
|Despide y borra de la nave a un tripulante|asistente.eliminaTripulante(Tripulante tripulante|
|Muestra las caracteristicas del asistente y de su nave|asistente.toString()|

### Métodos principales de la Nave
| Descripción| Método|
| --- | --- |
|Obtiene la cantidad de combustible|asistente.getNave().getCombustible()|
|Obtiene la cantidad de energía|asistente.getNave().getEnergia()|
|Obtiene el desgaste|asistente.getNave().getDesgaste()|
|Obtiene la tripulacion en un ArrayList|asistente.getNave().getTripulacion()|
|Obtiene la referencia al *Capitán* de la nave|asistente.getNave().getCapitan()|


## 2. Creación de las Misiones

>Para esta primer entrega solo realizaran ciertos pasos e imprimirán carteles en la consola. Se espera implementar más funcionalidades y características para la 2da entrega

Para crear una misión se debe llamar a la función que las crea, indicándole un tipo válido
```java
	Mision m1 = Sistema.creaMision(tipoMision);
```
*tipoMision* será un parámetro de tipo **int** que indicará que tipo se creara, los valores permitidos son:
* 1 - Intercepción y asistencia
* 2 - Recolección
* 3 - Retorno seguro

>De elegirse un tipo alternativo el programa indicara Error

Para poder realizar la misión se deberá llamar a un asistente y así realizar el chequeo de recursos y disponibilidad de la nave
```java
	m1.ejecutarMision(asist1);
```
En consola se mostrará el avance, si es que el asistente indica que puede realizar la misión, o mostrará el motivo por el que la rechazó

## 3. Creación de la Tripulación
Para crear un tripulante se debe llamar a la función que las crea, indicándole un cargo y un origen válido
```java
	Tripulante t1 = sistema.creaTripulante(tipoCargo,tipoOrigen,nombre,añosAntiguedad);
```
*tipoCargo* será un parámetro de tipo **String** que indicará el Rol que tendrá el tripulante en la nave, los valores permitidos son:
* Capitán , puede existir uno solo por nave
* Teniente
* Alferez
* Consejero

*tipoOrigen* será un parámetro de tipo **String** que indicará la especie que tendrá el tripulante en la nave, los valores permitidos son:
* Terrícola
* Vulcano
* Marciano
>De elegirse un tipo alternativo el programa indicara Error
