package cl.uchile.dcc.scrabble.AST;

import cl.uchile.dcc.scrabble.AST.binary.*;
import cl.uchile.dcc.scrabble.AST.ternary.Compare;
import cl.uchile.dcc.scrabble.AST.ternary.If;
import cl.uchile.dcc.scrabble.AST.unary.Negation;
import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.number.TBinary;
import cl.uchile.dcc.scrabble.types.number.TFloat;
import cl.uchile.dcc.scrabble.types.number.TInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ControlFlowTest {

    private int seed;
    private Random rng;
    private final FlyweightVarFactory varFac = FlyweightVarFactory.getInstance();
    private final FlyweightTIntFactory intFac = FlyweightTIntFactory.getInstance();
    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();
    private final FlyweightTBoolFactory boolFac = FlyweightTBoolFactory.getInstance();

    @BeforeEach
    void setUp(){
        seed = new Random().nextInt();
        rng = new Random(seed);
    }

    // Implements the euclidean algorithm to calculate the greatest common divisor.
    @RepeatedTest(50)
    void MCD_exampleTest() {
        int n1;
        do {
            n1 = rng.nextInt(500);
        } while (n1 == 0);
        int n2;
        do {
            n2 = rng.nextInt(500);
        } while(n2 == 0);
        Var t1 = new Var("a", intFac.getTInt(n1));
        Var t2 = new Var("b", intFac.getTInt(n2));
        While t3 = new While(
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
        assertEquals(0,n1%(((TInt)(varFac.getValue("a"))).getValue()), "seed: "+seed);
        assertEquals(0,n2%(((TInt)(varFac.getValue("a"))).getValue()), "seed: "+seed);
    }

    // Implements an algorithm to calculate the square root.
    @RepeatedTest(20)
    void squareRootExampleTest(){
        int n1 = rng.nextInt(5000);
        double n2 = Math.abs((rng.nextDouble()))*n1;
        Var t1 = new Var("a", intFac.getTInt(n1));
        Var t2 = new Var("b", floatFac.getTFloat(n2));
        While t3 = new While(
          new Or(
            new Compare(
              new Sub(
                new Var("b"),
                floatFac.getTFloat(Math.sqrt(n1))
              ),
              floatFac.getTFloat(0.001),
              intFac.getTInt(1)
            ),
            new Compare(
              new Sub(
                new Var("b"),
                floatFac.getTFloat(Math.sqrt(n1))
              ),
              floatFac.getTFloat(-0.001),
              intFac.getTInt(-1)
            )
          ),
          new Var(
            "b",
            new Div(
              new Add(
                new Var("b"),
                new Div(
                  new Var("a"),
                  new Var("b")
                )
              ),
              intFac.getTInt(2)
            )
          )
        );

        List<ITree> l = new ArrayList<>();
        l.add(t1);
        l.add(t2);
        l.add(t3);

        Seq tr = new Seq(l);
        tr.eval();
        assertTrue((Math.abs(((TFloat)(varFac.getValue("b"))).getValue()) - Math.sqrt(n1)) < 0.001,
          "seed: "+seed);
    }

    // Tests to check the implemented compare method.
    @RepeatedTest(100)
    void compareTest(){
        int n1 = rng.nextInt();
        int n2 = rng.nextInt();
        double n3 = rng.nextDouble() * (n1 + n2) / 2;
        TInt in1 = intFac.getTInt(n1);
        TBinary bn1 = intFac.getTInt(n1).toTBinary();
        TBinary bn2 = intFac.getTInt(n2).toTBinary();
        TFloat fn3 = floatFac.getTFloat(n3);
        if (n1 > n2){
            Compare c = new Compare(in1, bn2, intFac.getTInt(1));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn2, in1, intFac.getTInt(-1));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1, bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        else if (n1 == n2){
            Compare c = new Compare(in1, bn2, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn2, in1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1, bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        else{
            Compare c = new Compare(in1, bn2, intFac.getTInt(-1));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn2, in1, intFac.getTInt(1));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1, bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        if (n3 > n1){
            Compare c = new Compare(fn3, in1, intFac.getTInt(1));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c0 = new Compare(in1, fn3, intFac.getTInt(-1));
            assertEquals(boolFac.getTBool(true), c0.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn1, fn3, intFac.getTInt(-1));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1.toTFloat(), bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        else if(n3 == n1){
            Compare c = new Compare(fn3, in1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c0 = new Compare(in1, fn3, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c0.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn1, fn3, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1.toTFloat(), bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        else{
            Compare c = new Compare(fn3, in1, intFac.getTInt(-1));
            assertEquals(boolFac.getTBool(true), c.eval(), "seed: "+seed);
            Compare c0 = new Compare(in1, fn3, intFac.getTInt(1));
            assertEquals(boolFac.getTBool(true), c0.eval(), "seed: "+seed);
            Compare c1 = new Compare(bn1, fn3, intFac.getTInt(1));
            assertEquals(boolFac.getTBool(true), c1.eval(), "seed: "+seed);
            Compare c2 = new Compare(in1.toTFloat(), bn1, intFac.getTInt(0));
            assertEquals(boolFac.getTBool(true), c2.eval(), "seed: "+seed);
        }
        Compare t = new Compare(in1, boolFac.getTBool(true), intFac.getTInt(1));
        assertEquals(boolFac.getTBool(false), t.eval(), "seed: "+seed);

        Compare t1 = new Compare( bn1, boolFac.getTBool(true), intFac.getTInt(1));
        assertEquals(boolFac.getTBool(false), t1.eval(), "seed: "+seed);

        Compare t2 = new Compare(fn3, boolFac.getTBool(true), intFac.getTInt(1));
        assertEquals(boolFac.getTBool(false), t2.eval(), "seed: "+seed);
    }

}
