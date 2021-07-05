package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTStringFactory;
import cl.uchile.dcc.scrabble.types.number.TBinary;
import cl.uchile.dcc.scrabble.types.number.TFloat;
import cl.uchile.dcc.scrabble.types.number.TInt;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TStringTest {

    private Random rng;
    private int seed;

    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();

    @BeforeEach
    void setUp(){
        seed = new Random().nextInt();
        rng = new Random(seed);
    }

    @RepeatedTest(10)
    void hash_toStringTest(){
        int strSize = rng.nextInt(40);
        String v = RandomStringUtils.random(strSize);
        TString w1 = stringFac.getTString(v);
        TString w2 = stringFac.getTString(v);
        assertSame(w1, w2);
        assertEquals(w1.hashCode(), w2.hashCode());
        assertEquals(v, w1.toString());
    }

    /**
     * Verifies the addition operation (concatenation) between TString and other types.
     */
    @Test
    void stringSum_Test(){
        TString vString = new TString("EsteString");
        TBool vBool = new TBool(true);
        TFloat vFloat = new TFloat(4.42493536);
        TInt vInt = new TInt(424624);
        TBinary vBin = new TBinary("01010011");
        TString vString2 = new TString("Otro String");

        assertEquals(new TString("EsteStringtrue"), vString.suma(vBool));
        assertEquals(new TString("EsteString4.42493536"), vString.suma(vFloat));
        assertEquals(new TString("EsteString424624"), vString.suma(vInt));
        assertEquals(new TString("EsteString01010011"), vString.suma(vBin));
        assertEquals(new TString("EsteStringOtro String"), vString.suma(vString2));
        assertEquals(vString, vString.toTString());
    }

    @Test
    void stringSumFlyweightTest(){
        TString vString = stringFac.getTString("EsteString");
        TBool vBool = new TBool(true);
        TFloat vFloat = new TFloat(4.42493536);
        TInt vInt = new TInt(424624);
        TBinary vBin = new TBinary("01010011");
        TString vString2 = stringFac.getTString("Otro String");

        assertSame(stringFac.getTString("EsteStringtrue"), vString.suma(vBool));
        assertSame(stringFac.getTString("EsteString4.42493536"), vString.suma(vFloat));
        assertSame(stringFac.getTString("EsteString424624"), vString.suma(vInt));
        assertSame(stringFac.getTString("EsteString01010011"), vString.suma(vBin));
        assertSame(stringFac.getTString("EsteStringOtro String"), vString.suma(vString2));
        assertSame(vString, vString.toTString());
    }

    @RepeatedTest(20)
    void FlyweightTest(){
        int n1 = rng.nextInt(40);
        String b = RandomStringUtils.random(n1,32,126,true,true);
        TString b1 = stringFac.getTString(b);
        assertSame(b1, stringFac.getTString(b), "seed: " + seed);
    }
}
