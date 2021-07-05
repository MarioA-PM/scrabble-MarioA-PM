package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.TString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TBinaryTest {

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

    /**
     * Verifies the equals and hashcode methods.
     * Verifies the transformations and operations between the different types, which has to be done with
     * specific examples.
     */
    @Test
    void trans_equals_hash_LogicOp_Test(){
        // Number of examples
        int l = 16;
        // Values to test
        int[] expected = {0, 1, 2, 4, 11, 15, 1568, 8000002, -1, -2, -4, -7, -10, -12, -134135324, -28};

        // Correct representation of the int numbers in 4 bytes
        String[] values = {"00000000000000000000000000000000"
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
        TBinary[] num = new TBinary[l];
        for (int i = 0; i < l; i++){
            num[i] = new TBinary(values[i]);
        }

        // Verifies all examples and transformations
        for (int i = 0; i < l; i++){
            TInt act = num[i].toTInt();
            TInt dir = new TInt(expected[i]);
            TString actTString = num[i].toTString();
            TString exp = new TString(values[i]);
            TFloat expObj = new TFloat((double) expected[i]);
            TFloat actObj = num[i].toTFloat();
            assertEquals(expected[i], act.getValue(), "Binary and integer value don't match");
            assertEquals(dir, act, "Objects don't match");
            assertEquals(dir.hashCode(), act.hashCode());
            assertEquals(exp, actTString);
            assertEquals(expObj, actObj);
            assertEquals(exp, actTString.toTString());
            assertEquals(num[i], num[i].toTBinary());
        }

        // Verifies the correct behavior of the equals method with different size TBinary objects.
        TBinary a1 = new TBinary("000001101");
        TBinary a2 = new TBinary("01101");
        assertEquals(a1,a2);

        TBinary b1 = new TBinary("000001101");
        TBinary b2 = new TBinary("100001101");
        assertNotEquals(b1,b2);

        TBinary c1 = new TBinary("101");
        TBinary c2 = new TBinary("11111111101");
        assertEquals(c1,c2);

        // Examples to test the logical operations
        String[] val = {           "1"
                ,                  "0"
                ,              "11111"
                ,           "00000000"
                ,"0100100101001011101"
                ,       "100101101111"
                ,            "1010111"
                ,            "0011011"};

        TBinary[] bins = new TBinary[8];
        for (int i = 0; i < 8; i++){
            bins[i] = new TBinary(val[i]);
        }
        // Verifies the operations between TBinary and TBool objects.
        for (int i = 0; i < 8; i++){
            assertEquals(new TBinary("1"), bins[i].or(new TBool(true)));
            assertEquals(new TBinary("1"), (new TBool(true)).or(bins[i]));
            assertEquals(bins[i], bins[i].and(new TBool(true)));
            assertEquals(bins[i], (new TBool(true)).and(bins[i]));
            assertEquals(new TBinary("0"), bins[i].and(new TBool(false)));
            assertEquals(new TBinary("0"), (new TBool(false)).and(bins[i]));
            assertEquals(bins[i], bins[i].or(new TBool(false)));
            assertEquals(bins[i], (new TBool(false)).or(bins[i]));
        }

        // Verifies the logical operations between a TBinary and TBinary
        assertEquals(new TBinary("0010011"), bins[6].and(bins[7]));
        assertEquals(new TBinary("1011111"), bins[6].or(bins[7]));
        assertEquals(new TBinary("0101000"), bins[6].neg());
        assertEquals(new TBinary("1100100"), bins[7].neg());
        assertEquals(bins[0], bins[1].neg());
        assertEquals(bins[0].neg(), bins[1]);
        assertEquals(new TBinary("0100100101101111111"), bins[4].or(bins[5]));
        assertEquals(new TBinary("0100100101101111111"), bins[5].or(bins[4]));
        assertEquals(new TBinary("0100100100001001101"), bins[4].and(bins[5]));
        assertEquals(new TBinary("0100100101001011111"), bins[4].or(bins[7]));
        assertEquals(new TBinary("0100100101000011001"), bins[4].and(bins[7]));
        assertEquals(new TBinary("0100100101000011001"), bins[7].and(bins[4]));
        assertEquals(new TBinary("0"), bins[3].and(bins[2]));
        assertEquals(new TBinary("00011111"), bins[3].or(bins[2]));
    }

    /**
     * Verifies the number operations.
     */
    @RepeatedTest(20)
    void operacionesNumBinTest(){
        int n1 = rng.nextInt(1000);
        int n2 = rng.nextInt(1000);
        double n3 = rng.nextDouble();
        int n11;
        int n22;

        // Generate random non-zero values to test division
        double n33;
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

        assertEquals((new TInt(n1 + n2)).toTBinary(), bn1.suma(in2), "seed: " + seed);
        assertEquals((new TInt(n1 + n2)).toTBinary(), bn1.suma(bn2), "seed: " + seed);
        assertEquals((new TInt(n1 + n2)), in1.suma(bn2), "seed: " + seed);
        assertEquals((new TFloat(n3 + n1)), fn3.suma(bn1), "seed: " + seed);

        assertEquals((new TInt(n1 - n2)).toTBinary(), bn1.resta(in2), "seed: " + seed);
        assertEquals((new TInt(n1 - n2)).toTBinary(), bn1.resta(bn2), "seed: " + seed);
        assertEquals((new TInt(n1 - n2)), in1.resta(bn2), "seed: " + seed);
        assertEquals((new TFloat(n3 - n1)), fn3.resta(bn1), "seed: " + seed);

        assertEquals((new TInt(n1 * n2)).toTBinary(), bn1.mult(in2), "seed: " + seed);
        assertEquals((new TInt(n1 * n2)).toTBinary(), bn1.mult(bn2), "seed: " + seed);
        assertEquals((new TInt(n1 * n2)), in1.mult(bn2), "seed: " + seed);
        assertEquals((new TFloat(n3 * n1)), fn3.mult(bn1), "seed: " + seed);

        assertEquals((new TInt(n11 / n22)).toTBinary(), bn11.div(in22), "seed: " + seed);
        assertEquals((new TInt(n11 / n22)).toTBinary(), bn11.div(bn22), "seed: " + seed);
        assertEquals((new TInt(n11 / n22)), in11.div(bn22), "seed: " + seed);
        assertEquals((new TFloat(n33 / n11)), fn33.div(bn11), "seed: " + seed);
    }

    /**
     * Verifies the correct use of memory (reuse of objects).
     */
    @RepeatedTest(20)
    void flyweightOperationTest(){
        int n1 = rng.nextInt(1000);
        int n2 = rng.nextInt(1000);
        double n3 = rng.nextDouble();
        int n11;
        int n22;

        // Generate random non-zero values to test division
        double n33;
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

        assertSame((intFac.getTInt(n1 + n2)).toTBinary(), bn1.suma(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 + n2)).toTBinary(), bn1.suma(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 + n2)), in1.suma(bn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 + n1)), fn3.suma(bn1), "seed: " + seed);

        assertSame((intFac.getTInt(n1 - n2)).toTBinary(), bn1.resta(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 - n2)).toTBinary(), bn1.resta(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 - n2)), in1.resta(bn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 - n1)), fn3.resta(bn1), "seed: " + seed);

        assertSame((intFac.getTInt(n1 * n2)).toTBinary(), bn1.mult(in2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 * n2)).toTBinary(), bn1.mult(bn2), "seed: " + seed);
        assertSame((intFac.getTInt(n1 * n2)), in1.mult(bn2), "seed: " + seed);
        assertSame((floatFac.getTFloat(n3 * n1)), fn3.mult(bn1), "seed: " + seed);

        assertEquals((intFac.getTInt(n11 / n22)).toTBinary(), bn11.div(in22), "seed: " + seed);
        assertEquals((intFac.getTInt(n11 / n22)).toTBinary(), bn11.div(bn22), "seed: " + seed);
        assertEquals((intFac.getTInt(n11 / n22)), in11.div(bn22), "seed: " + seed);
        assertEquals((floatFac.getTFloat(n33 / n11)), fn33.div(bn11), "seed: " + seed);
    }

    /**
     * Verifies the correct use of memory (reuse of objects).
     */
    @RepeatedTest(20)
    void flyweightTest(){
        int n1 = rng.nextInt(20);
        String b = RandomStringUtils.random(Math.abs(n1), '0','1');
        TBinary b1 = binFac.getTBinary(b);
        assertSame(b1, binFac.getTBinary(b), "seed: " + seed);
    }
}
