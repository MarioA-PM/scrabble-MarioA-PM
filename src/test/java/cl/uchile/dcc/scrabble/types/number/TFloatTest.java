package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBinaryFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTFloatFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTIntFactory;
import cl.uchile.dcc.scrabble.types.TString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class TFloatTest {

    private Random rng;
    private int seed;

    private final FlyweightTIntFactory intFac = FlyweightTIntFactory.getInstance();
    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();

    @BeforeEach
    void setUp(){
        seed = new Random().nextInt();
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
    void operacionesFloatTest(){
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

    @RepeatedTest(20)
    void operacionesFlyweightFloatTest(){
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

        TFloat fn1 = floatFac.getTFloat(n1);
        TFloat fn2 = floatFac.getTFloat(n2);
        TInt in3 = intFac.getTInt(n3);
        TFloat fn11 = floatFac.getTFloat(n11);
        TFloat fn22 = floatFac.getTFloat(n22);
        TInt in33 = intFac.getTInt(n33);

        TBinary bn3 = in3.toTBinary();
        TBinary bn33 = in33.toTBinary();

        assertSame(floatFac.getTFloat(-n1), fn1.neg());
        assertSame((floatFac.getTFloat(n1 + n3)), fn1.suma(bn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 + n2)), fn1.suma(fn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 + n3)), fn1.suma(in3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 + n1)), in3.suma(fn1), "seed: " + seed);

        assertSame((floatFac.getTFloat(n1 - n3)), fn1.resta(bn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 - n2)), fn1.resta(fn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 - n3)), fn1.resta(in3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 - n1)), in3.resta(fn1), "seed: " + seed);

        assertSame((floatFac.getTFloat(n1 * n3)), fn1.mult(bn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 * n2)), fn1.mult(fn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 * n3)), fn1.mult(in3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 * n1)), in3.mult(fn1), "seed: " + seed);

        assertSame((floatFac.getTFloat(n11 / n33)), fn11.div(bn33), "seed: " + seed);
        assertSame((floatFac.getTFloat(n11 / n22)), fn11.div(fn22), "seed: " + seed);
        assertSame((floatFac.getTFloat(n11 / n33)), fn11.div(in33), "seed: " + seed);
        assertSame((floatFac.getTFloat(n33 / n11)), in33.div(fn11), "seed: " + seed);
    }

    @RepeatedTest(20)
    void flyweightTest(){
        double n1 = rng.nextDouble();
        TFloat i1 = floatFac.getTFloat(n1);
        assertSame(i1, floatFac.getTFloat(n1), "seed: " + seed);
    }
}
