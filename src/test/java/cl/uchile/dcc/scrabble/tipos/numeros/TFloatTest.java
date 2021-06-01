package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class TFloatTest {

    private Random rng;

    @BeforeEach
    void setUp(){
        int seed = new Random().nextInt();
        rng = new Random(seed);
    }

    @RepeatedTest(20)
    void hashCode_toString_toTStringTest(){
        double x1 = rng.nextDouble()*rng.nextInt();
        TFloat flx1 = new TFloat(x1);
        TFloat flx12 = new TFloat(x1);
        TString flx2 = new TString(String.valueOf(x1));
        assertEquals(flx1, flx12);
        assertEquals(flx1.hashCode(), flx12.hashCode());
        assertEquals(flx2, flx1.toTString());
        assertEquals(flx1, flx12.toTFloat());
    }

    @RepeatedTest(20)
    void operacionesIntTest(){
        double n1 = rng.nextDouble();
        double n2 = rng.nextDouble();
        int n3 = rng.nextInt(1000);
        double n11;
        double n22;
        int n33;

        // Generate random non-zero values to test division
        do {
            n11 = rng.nextDouble()*1000;
        } while (n11 == 0);
        do {
            n22 = rng.nextDouble()*1000;
        } while (n22 == 0);
        do {
            n33 = rng.nextInt(1000);
        } while (n33 == 0);

        TFloat fn1 = new TFloat(n1);
        TFloat fn2 = new TFloat(n2);
        TInt in3 = new TInt(n3);
        TFloat fn11 = new TFloat(n11);
        TFloat fn22 = new TFloat(n22);
        TInt in33 = new TInt(n33);

        TBinary bn3 = in3.toTBinary();
        TBinary bn33 = in33.toTBinary();

        assertEquals(new TFloat(-n1), fn1.neg());
        assertEquals((new TFloat(n1 + n3)), fn1.suma(bn3));
        assertEquals((new TFloat(n1 + n2)), fn1.suma(fn2));
        assertEquals((new TFloat(n1 + n3)), fn1.suma(in3));
        assertEquals((new TFloat(n3 + n1)), in3.suma(fn1));

        assertEquals((new TFloat(n1 - n3)), fn1.resta(bn3));
        assertEquals((new TFloat(n1 - n2)), fn1.resta(fn2));
        assertEquals((new TFloat(n1 - n3)), fn1.resta(in3));
        assertEquals((new TFloat(n3 - n1)), in3.resta(fn1));

        assertEquals((new TFloat(n1 * n3)), fn1.mult(bn3));
        assertEquals((new TFloat(n1 * n2)), fn1.mult(fn2));
        assertEquals((new TFloat(n1 * n3)), fn1.mult(in3));
        assertEquals((new TFloat(n3 * n1)), in3.mult(fn1));

        assertEquals((new TFloat(n11 / n33)), fn11.div(bn33));
        assertEquals((new TFloat(n11 / n22)), fn11.div(fn22));
        assertEquals((new TFloat(n11 / n33)), fn11.div(in33));
        assertEquals((new TFloat(n33 / n11)), in33.div(fn11));
    }
}
