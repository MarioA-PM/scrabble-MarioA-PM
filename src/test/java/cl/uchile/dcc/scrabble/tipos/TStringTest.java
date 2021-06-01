package cl.uchile.dcc.scrabble.tipos;

import cl.uchile.dcc.scrabble.tipos.numeros.TBinary;
import cl.uchile.dcc.scrabble.tipos.numeros.TFloat;
import cl.uchile.dcc.scrabble.tipos.numeros.TInt;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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
}
