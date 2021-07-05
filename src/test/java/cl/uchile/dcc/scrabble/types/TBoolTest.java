package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBoolFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTStringFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TBoolTest {

    private final FlyweightTBoolFactory boolFac = FlyweightTBoolFactory.getInstance();
    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();

    @Test
    void equals_hash_toTString_Operations_Flyweight_Test(){
        TBool v1 = new TBool(true);
        TBool v2 = new TBool(true);

        TBool m1 = new TBool(false);
        TBool m2 = new TBool(false);

        assertEquals(v1, v2);
        assertEquals(m1, m2);

        TBool s = boolFac.getTBool(true);
        TBool s1 = boolFac.getTBool(true);
        TBool f1 = boolFac.getTBool(false);
        TBool f2 = boolFac.getTBool(false);
        TString a = stringFac.getTString("true");
        TString a1 = stringFac.getTString("false");

        // Verifies equals(), hashCode() and transformations methods
        assertSame(s, s1);
        assertSame(f1, f2);
        assertEquals(s.hashCode(), s1.hashCode());
        assertEquals(f1.hashCode(), f2.hashCode());
        assertEquals("true", s.toString());
        assertEquals("false", f1.toString());
        assertSame(a, s.toTString());
        assertSame(a1, f1.toTString());

        // Verifies logical operations
        assertSame(s, s.or(s1));
        assertSame(s, s.or(f1));
        assertSame(f1, f1.or(f2));
        assertSame(s, s.and(s1));
        assertSame(f1, s.and(f1));
        assertSame(f1, f1.and(f2));
        assertSame(s, f1.neg());
        assertSame(f1, s.neg());
        assertSame(s, s1.toTBool());

    }
}
