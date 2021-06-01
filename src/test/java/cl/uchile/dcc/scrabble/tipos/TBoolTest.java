package cl.uchile.dcc.scrabble.tipos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TBoolTest {

    private boolean t = true;
    private boolean f = false;

    @Test
    void equals_hash_toTString_Operations_Test(){
        TBool s = new TBool(t);
        TBool s1 = new TBool(t);
        TBool f1 = new TBool(f);
        TBool f2 = new TBool(f);
        TString a = new TString("true");
        TString a1 = new TString("false");

        // Verifies equals(), hashCode() and transformations methods
        assertEquals(s, s1);
        assertEquals(f1, f2);
        assertEquals(s.hashCode(), s1.hashCode());
        assertEquals(f1.hashCode(), f2.hashCode());
        assertEquals("true", s.toString());
        assertEquals("false", f1.toString());
        assertEquals(a, s.toTString());
        assertEquals(a1, f1.toTString());

        // Verifies logical operations
        assertEquals(s, s.or(s1));
        assertEquals(s, s.or(f1));
        assertEquals(f1, f1.or(f2));
        assertEquals(s, s.and(s1));
        assertEquals(f1, s.and(f1));
        assertEquals(f1, f1.and(f2));
        assertEquals(s, f1.neg());
        assertEquals(f1, s.neg());
        assertEquals(s, s1.toTBool());

    }
}
