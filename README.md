# Call Center
API de gestión de llamadas


## Puesta en funcionamiento:
mvn clean install
Ejecutar los tests de DispatcherTests.java

## Escenario
Se incluyen una serie de tests de funcionamiento general.
El test que ejecuta el escenario es "testFullScenario" que realiza el siguiente procedimiento:

1. Genera mocks de:
  a. Servicio de CallCenter con 5 operadores, 2 supervisores y un director. Lo que permitirá probar todos los escenarios.
  b. Clientes que realizan las llamadas, 12 clientes en total.
  c. Llamadas que serán iniciadas por esos clientes.
2. Inicializa las llamadas utilizando threads.
    Utilizo Callable y no Runnable para poder obtener un contexto de ejecución)
3. Imprime un resumen de las llamadas con el objetivo de verificar sus diferentes estados de acuerdo a los procesos de negocio definidos.
    No realizo asserts ya que en el listado de contextos, al tener una duración aleatoria las llamadas el orden no se puede determinar.

## Resolución de limitaciones

Tanto para el caso de Dispatcher sobre capacidad y no se encuentran operadores libres para atender la llamada la desición fué reflejar dichas situaciones en el campo "estado" de la llamada. Se pudo haber utilizado excepciones, pero al ser casos definidos dentro del proceso de negocio no se recomienda gestionarlos con esta herramienta.

La solución elegida es entonces informar al cliente de estas situaciones y que quien consuma la API decida si esperar y reintentar en unos segundos o terminar la llamada.    
