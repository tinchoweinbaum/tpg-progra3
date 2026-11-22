
# Proyecto Nave Asistida

Realizado por *Martin Weinbaum , Juan Pablo Gueli , Lisandro Mazzucco , y Gonzalo Bounzerio* <br>
Proyecto final de la materia **Programacion C** de la Universidad Nacional de Mar del Plata, dictada por el profesor *Leonel Guccione*.


## Introducción

El **Asistente de Navegación y Comando** es un software embarcado diseñado para ayudar a la tripulación de naves interestelares a **administrar subsistemas, controlar recursos (combustible/energía/desgaste) y registrar eventos** en misiones de largo alcance. El sistema opera bajo reglas y autorizaciones preestablecidas sin reemplazar la autoridad del capitán, encargándose de **automatizar acciones permitidas,  y validar que la nave cuente con los recursos y el mantenimiento adecuados** antes de cada operación en entornos imprevistos e incomunicados.

## Primeros pasos

### 1. Ejecución de la aplicación
Para iniciar el sistema, se debe ejecutar la clase principal desde el entorno (o mediante Maven):
* **Clase principal:** `main.Main` (en `src/main/java/main/Main.java`).
* **Requisitos:** JDK 17 o superior y Maven.
  
Al ejecutarla, se desplegará la interfaz gráfica en la pantalla de bienvenida y acceso.
### 2. Autenticación (Registro e Inicio de Sesión)
Para poder acceder a los controles de la nave, es obligatorio autenticarse:
* **Registro:** Si es la primera vez que se utiliza el sistema, o se desea tener un perfil separado, se debe crear un nuevo usuario desde la opción de registro.
* **Inicio de sesión:** Ingresar con las credenciales registradas.
* **Persistencia:** Las cuentas quedan guardadas en una base de datos local SQLite (`usuarios.db`).
  Una vez iniciada la sesión, el sistema dará paso a la creación y vinculación de la nave.
---
## 1. Creación de la tupla Asistente-Nave
> Un Asistente solo existe con su nave, y una nave no puede existir sin un asistente.
Una vez dentro del sistema, se accede a la configuración de la nave y su asistente vinculado. Los tipos de nave disponibles para armar son:
* **Carguero**
* **Explorador**
* **Combate**
### Subsistemas y Gestión del Asistente
Desde el panel de control de la nave, el asistente permite supervisar y accionar:
* **Recursos:** Carga y consumo de combustible y energía, y reparación del desgaste del casco, estos recursos se pueden obtener por eventos del universo como las estaciones de carga, o realizando una misión de vuelta al origen.
* **Motor Warp:** Control de estados del motor (*Disponible*, *Preparando Salto*, *Salto Warp*, *Enfriamiento*, y un caso especial, *Destruido*) y ejecución de saltos espaciales.
* **Bitácora de A bordo:** Registro y consulta clasificada de eventos (misiones, combates, recursos y errores).
* **Mapa:** Permite ver el tablero donde ocurren los eventos del universo y funciona como una especie de escáner para el usuario
* **Visor:** Una sección paralela donde se registra mediante un panel los eventos concurrentes de naves generadoras y consumidoras de baterías
* **Tripulación:** Gestion de empleados, se permite elegir los atributos deseados al contrataros y también se pueden despedir
---
## 2. Creación de la Tripulación
Para operar la nave se debe conformar la tripulación asignando cargos y especies:
* **Cargos (Roles):**
    * *Capitán* (se crea junto con la nave, único por nave y si se despide se acaba la partida)
    * *Teniente*
    * *Alférez*
    * *Consejero*
* **Especies (difieren en los subsidios a la hora de calcular su sueldo):**
    * *Terrícola*
    * *Vulcano*
    * *Marciano*
---
## 3. Misiones y Mapa Galáctico
Para ejecutar operaciones espaciales, el asistente realiza un chequeo previo de recursos y disponibilidad de la nave:
* **Tipos de Misiones:**
    * **1 - Intercepción y asistencia**
    * **2 - Recolección**
    * **3 - Retorno seguro** (esta última no se puede ejecutar si la ya nave se encuentra en el origen)
* **Mapa Galáctico:** Tablero interactivo donde se visualiza la posición de la nave y los diferentes elementos del universo (asteroides, naves aliadas/hostiles y estaciones de recarga espacial).
---
## 4. Visor Paralelo (Concurrencia)
Simulación en tiempo real de un entorno paralelo donde múltiples hilos (`Threads`) representan:
* **Naves Generadoras:** Producen energía.
* **Naves Consumidoras:** Extraen energía.
* **Plataforma de Carga:** Recurso compartido regulado concurrentemente.
---
## 5. Arquitectura y Patrones de Diseño
El sistema fue diseñado bajo el patrón **MVC** (Modelo-Vista-Controlador), dividiéndose en `CapaPresentacion` (Java Swing), `CapaNegocio` y `CapaDatos`.
| Patrón | Dónde se aplica |
| :--- | :--- |
| **State** | Control de estados del `MotorWarp` (Disponible, Salto, Enfriamiento). |
| **Decorator** | `EspecieDecorator` para asignar habilidades dinámicas a los `Tripulante`. |
| **Observer** | Actualización en tiempo real del `Tablero`, `Visor`, y demás componentes. |
| **Factory** | Creación de instancias en `FactoryNaves` y `FactoryPeople`. |
| **Facade (Fachada)** | `Enlazador`: unifica y simplifica el acceso al Asistente, al Visor y a la persistencia. |
| **DAO** | `UsuariosDAO` / `UsuariosSqlDao` para persistencia en SQLite. |
| **Double Dispatch** | Utilizado para decidir el resultado de los combates entre naves. |