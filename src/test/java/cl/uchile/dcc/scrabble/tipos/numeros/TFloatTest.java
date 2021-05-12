package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class TFloatTest {

    private int seed;
    private Random rng;

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
    }
}
