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
proyecto: `TString`, `TBool`, `TInt`, `TFloat` y `TBinary`. Para corroborar la implementación 
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
interfaz ITree, la cual provee un único método que corresponde a evaluar el árbol (`eval()`) 
que representa dicho nodo (de manera recursiva) y retorna un tipo de Scrabble. El siguiente 
es un ejemplo de la construcción de un árbol:

````java
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
````
Para el almacenamiento de valores de forma eficiente, se utilizó el patrón de diseño Flyweight, 
teniendo 5 clases que se encargan de crear y almacenar los valores para cada tipo de Scrabble. 
Estas fábricas también siguen el patrón Singleton, es decir, solo puede haber una instancia para 
cada fábrica de cada tipo. De esta forma, los objetos deben ser creados de la siguiente forma:

````java
FlyweightTIntFactory intFactory = FlyweightTIntFactory.getInstance();
TInt n = intFactory.getTInt(5);
````
El uso de variables (asignar un valor a una variable) se implementó como otra operación 
de un AST, de la siguiente forma:

````java
Var a = new Var("a", intFactory.getTInt(10));
````
Y para utilizar una variable declarada anteriormente se puede hacer:

````java
Add sum =
  new Add(
    new Var("a"),
    intFactory.getTInt(5)
  );

sum.eval() == intFactory.getTInt(15); //true
````

Para poder comparar valores del mismo y distinto tipo se implementó otra operación de la forma
``Compare(a, b, c)`` donde `a` y `b` son AST y `c` es un entero de Scrabble que puede ser -1,
0 y 1. `Compare(a, b, 1)` es equivalente a `a > b`, `Compare(a, b, 0)` es equivalente 
a `a == b` y `Compare(a, b, -1)` es equivalente a `a < b`, con el valor de retorno siendo un 
boolean de Scrabble. Por ejemplo:

````java
Compare c = new Compare(new TInt(4), new TFloat(2), new TInt(1));
assertEquals(new TBool(true), c.eval()); //true
````

Finalmente, las instrucciones de control de flujo también fueron implementadas como 
operaciones de un AST, esta vez utilizando el Visitor Pattern. En el siguiente ejemplo se 
implementa el algoritmo de Euclides que calcula el máximo común divisor entre dos números 
``a`` y `b`.

````java
Var t1 = new Var("a", intFac.getTInt(60));
Var t2 = new Var("b", intFac.getTInt(36));
While t3 = 
  new While(
    new Negation(
      new Compare(
        new Var("b"),
        new Var("a"),
        intFac.getTInt(0)
      )
    ),
    new If(
      new Compare(
        new Var("a"),
        new Var("b"),
        intFac.getTInt(1)
      ),
      new Var(
        "a",
        new Sub(
          new Var("a"),
          new Var("b")
        )
      ),
      new Var(
        "b",
        new Sub(
          new Var("b"),
          new Var("a")
        )
      )
    )
  );

  List<ITree> l = new ArrayList<>();
  l.add(t1);
  l.add(t2);
  l.add(t3);

  Seq tr = new Seq(l);
  tr.eval();
  assertEquals(new TInt(12), (new Var("a")).eval()); //true
````