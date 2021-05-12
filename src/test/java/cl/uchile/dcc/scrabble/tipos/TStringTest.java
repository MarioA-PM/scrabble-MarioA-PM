package cl.uchile.dcc.scrabble.tipos;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TStringTest {

    @RepeatedTest(10)
    void hash_toStringTest(){
        int seed = new Random().nextInt();
        Random rng = new Random(seed);
        int strSize = rng.nextInt(40);
        String v = RandomStringUtils.random(strSize);
        TString w1 = new TString(v);
        TString w2 = new TString(v);
        assertEquals(w1, w2);
        assertEquals(w1.hashCode(), w2.hashCode());
        assertEquals(v, w1.toString());
    }
}
