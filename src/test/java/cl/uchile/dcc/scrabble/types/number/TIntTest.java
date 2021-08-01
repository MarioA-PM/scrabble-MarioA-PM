package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBinaryFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTFloatFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTIntFactory;
import cl.uchile.dcc.scrabble.types.TString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TIntTest {

    private int seed;
    private Random rng;

    private final FlyweightTBinaryFactory binFac = FlyweightTBinaryFactory.getInstance();
    private final FlyweightTIntFactory intFac = FlyweightTIntFactory.getInstance();
    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();

    @BeforeEach
    void setUp(){
        seed = new Random().nextInt();
        rng = new Random(seed);
    }

    @RepeatedTest(10)
    void equalsAndHashTest(){
        int n1 = rng.nextInt();
        int n2;
        do {
            n2 = new Random().nextInt();
        } while (n2 == n1);
        TInt tint1 = new TInt(n1);
        TInt tint11 = new TInt(n1);
        TInt tint2 = new TInt(n2);
        assertEquals(tint1, tint11);
        assertEquals(tint1.hashCode(), tint11.hashCode());
        assertNotEquals(tint1, tint2);
        assertEquals(tint1.toTInt(), tint1);
    }

    @RepeatedTest(10)
    void toTFloatTest(){
        int n = rng.nextInt();
        TInt res = new TInt(n);
        TFloat expected = new TFloat(n);
        assertEquals(expected, res.toTFloat(), "Objects don't match. seed: " + seed);
    }

    @RepeatedTest(10)
    void toTStringTest(){
        int n = rng.nextInt();
        TInt res = new TInt(n);
        TString expected = new TString(String.valueOf(n));
        assertEquals(expected, res.toTString(), "TString objects don't match. seed: " + seed);
    }

    @RepeatedTest(10)
    void toStringTest(){
        int n = rng.nextInt();
        String expected = String.valueOf(n);
        assertEquals(expected, new TInt(n).toString(), "Strings don't match. Seed: " + seed);
    }

    @Test
    void toTBinaryTest(){
        int l = 16;
        int[] integers = {0, 1, 2, 4, 11, 15, 1568, 8000002, -1, -2, -4, -7, -10, -12, -134135324, -28};
        TInt[] num = new TInt[l];
        for (int i = 0; i < l; i++){
            num[i] = new TInt(integers[i]);
        }

        String[] expected = {"00000000000000000000000000000000"
                ,"00000000000000000000000000000001"
                ,"00000000000000000000000000000010"
                ,"00000000000000000000000000000100"
                ,"00000000000000000000000000001011"
                ,"00000000000000000000000000001111"
                ,"00000000000000000000011000100000"
                ,"00000000011110100001001000000010"
                ,"11111111111111111111111111111111"
                ,"11111111111111111111111111111110"
                ,"11111111111111111111111111111100"
                ,"11111111111111111111111111111001"
                ,"11111111111111111111111111110110"
                ,"11111111111111111111111111110100"
                ,"11111000000000010100000111100100"
                ,"11111111111111111111111111100100"};
        for (int i = 0; i < l; i++){
            TBinary a = num[i].toTBinary();
            TBinary a1 = binFac.getTBinary(expected[i]);
            assertEquals(expected[i], a.toString());
            assertSame(a1, a);
            assertEquals(a1.hashCode(), a.hashCode());
        }

    }

    @RepeatedTest(20)
    void operacionesIntTest(){
        int n1 = rng.nextInt(1000);
        int n2 = rng.nextInt(1000);
        double n3 = rng.nextDouble();
        int n11;
        int n22;
        double n33;

        // Generate random non-zero values to test division
        do {
            n11 = rng.nextInt(1000);
        } while (n11 == 0);
        do {
            n22 = rng.nextInt(1000);
        } while (n22 == 0);
        do {
            n33 = rng.nextDouble()*1000;
        } while (n33 == 0);

        TInt in1 = new TInt(n1);
        TInt in2 = new TInt(n2);
        TInt in11 = new TInt(n11);
        TInt in22 = new TInt(n22);

        TBinary bn1 = in1.toTBinary();
        TBinary bn2 = in2.toTBinary();
        TBinary bn11 = in11.toTBinary();
        TBinary bn22 = in22.toTBinary();

        TFloat fn3 = new TFloat(n3);
        TFloat fn33 = new TFloat(n33);

        assertEquals(new TInt(-n1), in1.neg());
        assertEquals((new TInt(n1 + n2)).toTBinary(), bn1.suma(in2));
        assertEquals((new TInt(n1 + n2)), in1.suma(bn2));
        assertEquals((new TInt(n1 + n2)), in1.suma(in2));
        assertEquals((new TFloat(n1 + n3)), in1.suma(fn3));
        assertEquals((new TFloat(n1 + n3)), fn3.suma(in1));

        assertEquals((new TInt(n1 - n2)).toTBinary(), bn1.resta(in2));
        assertEquals((new TInt(n1 - n2)), in1.resta(bn2));
        assertEquals((new TInt(n1 - n2)), in1.resta(in2));
        assertEquals((new TFloat(n1 - n3)), in1.resta(fn3));
        assertEquals((new TFloat(n3 - n1)), fn3.resta(in1));

        assertEquals((new TInt(n1 * n2)).toTBinary(), bn1.mult(in2));
        assertEquals((new TInt(n1 * n2)), in1.mult(bn2));
        assertEquals((new TInt(n1 * n2)), in1.mult(in2));
        assertEquals((new TFloat(n1 * n3)), in1.mult(fn3));
        assertEquals((new TFloat(n3 * n1)), fn3.mult(in1));

        assertEquals((new TInt(n11 / n22)).toTBinary(), bn11.div(in22));
        assertEquals((new TInt(n11 / n22)), in11.div(bn22));
        assertEquals((new TInt(n11 / n22)), in11.div(in22));
        assertEquals((new TFloat(n11 / n33)), in11.div(fn33));
        assertEquals((new TFloat(n33 / n11)), fn33.div(in11));
    }

    @RepeatedTest(20)
    void flyweightOperationTest(){
        int n1 = rng.nextInt(1000);
        int n2 = rng.nextInt(1000);
        double n3 = rng.nextDouble();
        int n11;
        int n22;
        double n33;

        // Generate random non-zero values to test division
        do {
            n11 = rng.nextInt(1000);
        } while (n11 == 0);
        do {
            n22 = rng.nextInt(1000);
        } while (n22 == 0);
        do {
            n33 = rng.nextDouble()*1000;
        } while (n33 == 0);

        TInt in1 = intFac.getTInt(n1);
        TInt in2 = intFac.getTInt(n2);
        TInt in11 = intFac.getTInt(n11);
        TInt in22 = intFac.getTInt(n22);

        TBinary bn1 = in1.toTBinary();
        TBinary bn2 = in2.toTBinary();
        TBinary bn11 = in11.toTBinary();
        TBinary bn22 = in22.toTBinary();

        TFloat fn3 = floatFac.getTFloat(n3);
        TFloat fn33 = floatFac.getTFloat(n33);

        assertSame(intFac.getTInt(-n1), in1.neg());
        assertSame((intFac.getTInt(n1 + n2)).toTBinary(), bn1.suma(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 + n2)), in1.suma(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 + n2)), in1.suma(in2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 + n3)), in1.suma(fn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 + n3)), fn3.suma(in1), "seed: " + seed);

        assertSame((intFac.getTInt(n1 - n2)).toTBinary(), bn1.resta(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 - n2)), in1.resta(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 - n2)), in1.resta(in2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 - n3)), in1.resta(fn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 - n1)), fn3.resta(in1), "seed: " + seed);

        assertSame((intFac.getTInt(n1 * n2)).toTBinary(), bn1.mult(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 * n2)), in1.mult(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 * n2)), in1.mult(in2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n1 * n3)), in1.mult(fn3), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 * n1)), fn3.mult(in1), "seed: " + seed);

        assertSame((intFac.getTInt(n11 / n22)).toTBinary(), bn11.div(in22), "seed: " + seed);
        assertSame((intFac.getTInt(n11 / n22)), in11.div(bn22), "seed: " + seed);
        assertSame((intFac.getTInt(n11 / n22)), in11.div(in22), "seed: " + seed);
        assertSame((floatFac.getTFloat(n11 / n33)), in11.div(fn33), "seed: " + seed);
        assertSame((floatFac.getTFloat(n33 / n11)), fn33.div(in11), "seed: " + seed);
    }

    @RepeatedTest(20)
    void flyweightTest(){
        int n1 = rng.nextInt(100000);
        TInt i1 = intFac.getTInt(n1);
        assertSame(i1, intFac.getTInt(n1), "seed: " + seed);
    }
}
