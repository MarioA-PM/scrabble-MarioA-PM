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

Para comprobar el funcionamiento del programa se deben ejecutar los test de JUnit ubicados
en la carpeta test, los cuales están divididos en tests para las 5 clases principales del 
proyecto: `TString`, `TBool`, `TInt`, `TFloat` y `TBinary`. Para corroborar  la implementación 
de los árboles de sintaxis abstracta (AST) se creó una clase aparte con tests independientes.

## Funcionamiento

Todas las operaciones (suma, resta, conjunción, etc) y transformaciones (a binario, a string,
etc) son comunes a todos los tipos, es decir, a cada tipo se le puede aplicar cualquier 
transformación y se puede operar con cualquier tipo. Estas operaciones fueron implementadas 
mediante Double Dispatch.

En el caso de las operaciones y transformaciones que no están permitidas por los requerimientos 
del proyecto como binario + float (`aBinary.suma(aFloat)`) o convertir un float a binario
(`aFloat.toTBinary()`), se retorna un objeto que representa el valor nulo. Este último objeto 
implementa todas las operaciones y transformaciones pero retornando siempre el mismo valor. 
La clase en cuestión es NullObject y, como su nombre sugiere, sigue el Null Object Pattern, 
que a su vez está implementado como un Singleton. 

Para implementar los AST se utilizó el patrón Composite, que permite crear un árbol
donde los nodos con dos hijos corresponden a una operación binaria y los nodos con
solo un hijo a operaciones unarias (como la negación o las transformaciones). Los 
nodos internos siempre son operaciones o transformaciones, y los nodos hoja corresponden 
a los tipos creados de Scrabble. Las clases que representan nodos implementa la 
interfaz ITree, la cual provee un único método que corresponde a evaluar el arból (`eval()`) 
que representa dicho nodo (de manera recursiva) y retorna un tipo de Scrabble. El siguiente 
es un ejemplo de la construcción de un árbol:

```java
IType tree =
  new Add(
    new TFloat(6.9),
    new Or(
      new TBinary("1000"),
      new ToBinary(
        new Sub(
          new TInt(25),
          new TBinary("0101")
        )
      )
    )
  );

assertEquals(new TFloat(34.9), tree.eval()); //true
```
Para el almacenamiento de variables de forma eficiente, se utilizó el patrón de diseño Flyweight, 
teniendo 5 clases que se encargan de crear y almacenar las variables para cada tipo de Scrabble. 
Esta fábricas también siguen el patrón Singleton, es decir, solo puede haber una instancia para 
cada fábrica de cada tipo. De esta forma, los objetos deben ser creados de la siguiente forma:

```java
FlyweightTIntFactory intFactory = FlyweightTIntFactory.getInstance();
TInt n = intFactory.getTInt(5);
```
