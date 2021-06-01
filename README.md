# Scrabble

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

Interactive graphic programming language heavily inspired by 
[Scratch](https://scratch.mit.edu).
This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/), 
and aims purely to be used with the purpose of teaching in the context of the course 
_CC3002 Metodologías de Diseño y programación_ of the 
[_Computer Sciences Department (DCC)_](https://www.dcc.uchile.cl) of the 
_University of Chile_.

---

## Instrucciones

Para comprobar el funcionamiento del programa se deben ejecutar los test de JUnit ubicados en la carpeta test,
los cuales están divididos en las 5 clases principales del proyecto: TString, TBool, TInt, TFloat y TBinary.

## Funcionamiento

Todas las funcionalidades requeridas, ya sea transformaciones u operaciones, son implementadas a través de
interfaces para dejar de forma más explícita los métodos que cada tipo debe implementar, además del orden en
que estos se pueden realizar (en el caso de las operaciones entre dos tipos). 

Para implementar las distintas operaciones entre dos tipos se utilizó el método de double dispatch, 
salvo en el caso de la suma de un string, ya que solo se permite sumar un string a la derecha con 
cualquier otro tipo, pero no al revés. 

En el caso de las operaciones entre números (suma, resta, multiplicación y división) se utilizó de igual
manera double dispatch pero implementando varias interfaces y un diseño un poco más enredado para poder
restringir que un binario a la izquierda no se pueda operar con un float a la derecha. Debido a esto incluso
se implementan interfaces vacías para poder restringir o permitir las operaciones entre distintos tipos.

**Importante:** Hay que tomar en cuenta que todas las operaciones están pensadas para ocuparse con su nombre
general, por ejemplo, `objeto.suma(otroObjeto)` o `objeto.resta(otroObjeto)`, ya que al utilizar
double dispatch se implementan métodos específicos como `objeto.sumaFloat(TFloat)`, lo cual 
permitiría operar un binario a la izquierda con un float a la derecha de la forma
`binario.sumaFloat(float)`. Estos métodos deben ser implementados debido a la naturaleza del
funcionamiento de double dispatch pero no están pensados para usarse. 

Esto último también aplica para las operaciones lógicas, que deben ser llamadas como, por ejemplo, 
`booleano.or(binario)` y no como `booleano.orBin(binario)`.

