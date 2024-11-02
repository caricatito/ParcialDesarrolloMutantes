## Ejercicio
El ejercicio consiste en pasarle una matriz (Que en realidad es un array) y dependiendo de como estes conformadas 
las estructuras de ADN devolvera si es o no es mutante el ADN de la persona analizada. Esto ya que magneto quiere 
reclutar mutantes para su ejercito segun el enunciado brindado por la catedra.
## Funcionamiento
Para este ejercicio hay que tener en cuenta muchos puntos:
* Al analizar las matrices solo podran ser de nxn es decir, no pueden ser matrices de 4x2 o 4x3 tienen que ser 
  necesariamente de 4x4, 2x2,etc.
* Los parametros a analizar dentro de las matrices seran letras las cuales representan las 4 bases nitrogenadas del 
  ADN, por lo tanto si dentro de este array incorporamos alguna otra letra u expresion, no sera analizada directamente.
* Se sabra si es mutante ya que si en las cadenas de ADN, hay mas de una secuencia de cuatro letras iguales de 
  manera vertical, horizontal u oblicua.

## Ejecución
Puede Ejecutarse de 2 maneras distintas:

1. ### **_De manera remota sin descargar los archivos:_**

Al estar deployado en render pero no tener un front para mostrar algo, tirara error si se clickea el link que sera. 
Ademas al ser la version gratuita render dejara de estar vigente por lo tanto, si desea ver el sistema con render 
comuniquese directamente con el dueño de este repositorio.

https://parcialdesarrollomutantes.onrender.com

Luego hay que abrir la aplicacion de POSTMAN

https://web.postman.co

Logearse, crear una nueva coleccion y dentro de la misma creamos 2 servicios y seleccionamos lo siguiente
* GET: https://parcialdesarrollomutantes.onrender.com/stats
* POST: https://parcialdesarrollomutantes.onrender.com/mutant

Dentro del POST seleccionar "body" y dentro  del body el "raw" e ingresar algo con este formato y se ejecutara el 
servicio para saber si es mutante

```JSON
"dna":[
"ATGCGA",
"CAGTGC",
"TTATGT",
"AGAAGG",
"CCCCTA",
"TCACTG"
]
```
Mientras que el GET devolvera algo como esto lo cual uno lleva la cuenta de humanos otro la de mutantes y la otra es 
el ratio de mutantes por persona

```JSON
"ratio": 0.0,
"contador_mutante": 0,
"contador_humano": 0
```
2. ### **_De manera manual, descargando archivos:_**

Para ello, habra que descargarse las carpeta en formato .ZIP de este repositorio, luego habra que extraerla y 
abrirla con InteligentIdea y configurar la base de Datos H2, ya que en render esta ya deployada en memoria mientras 
que nosotros tenemos que acceder a ella (en caso de necesitarlo, aunque realmente con el metodo GET de POSTMAN ya 
seria suficiente para ver los datos), mediante esta URL `jdbc:h2:mem:~/testdb;`

Se ejecuta el proyecto dentro del InteligentIdea y de manera similar hay que cumplir todos los pasos mencionados 
anteriormente en el POSTMAN solo que cambiando las direcciones por estas

* GET: http://localhost:8080/stats
* POST: http://localhost:8080/mutant

Devolvera en cada caso el mismo resultado, todo depende de las cadenas a ingresar


### EJEMPLOS DE CADENAS PARA TESTEAR
```JSON
"dna":[
"ATGCGA",
"CAGTGC",
"TTATGT",
"AGAAGG",
"CCCCTA",
"TCACTG"
]
```
```JSON
"dna": [
"ATGCGA",
"CAGTGC",
"TTATGT",
"AGAAGG",
"CCCCTA",
"TCACTA"
]
```

```JSON
"dna": [
"ATGCGA",
"CAGTGC",
"TTATGT",
"AGAAGG",
"CCCTTA",
"TCACTG"
]
```

