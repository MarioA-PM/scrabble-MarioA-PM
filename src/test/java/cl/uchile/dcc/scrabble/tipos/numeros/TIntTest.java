package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TIntTest {

    private int seed;
    private Random rng;

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
    }

    @RepeatedTest(10)
    void toTFloatTest(){
        int n = rng.nextInt();
        TInt res = new TInt(n);
        TFloat expected = new TFloat((double) n);
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
        int integers[] = {0, 1, 2, 4, 11, 15, 1568, 8000002, -1, -2, -4, -7, -10, -12, -134135324, -28};
        TInt num[] = new TInt[l];
        for (int i = 0; i < l; i++){
            num[i] = new TInt(integers[i]);
        }

        String expected[] = {"00000000000000000000000000000000"
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
            TBinary a1 = new TBinary(expected[i]);
            assertEquals(expected[i], a.toString());
            assertEquals(a1, a);
            assertEquals(a1.hashCode(), a.hashCode());
        }

    }
}
