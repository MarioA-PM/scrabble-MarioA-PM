package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TBinaryTest {

    @Test
    void toTInt_equals_hash_toTString_Test(){
        int l = 16;
        int expected[] = {0, 1, 2, 4, 11, 15, 1568, 8000002, -1, -2, -4, -7, -10, -12, -134135324, -28};

        String values[] = {"00000000000000000000000000000000"
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
        TBinary num[] = new TBinary[l];
        for (int i = 0; i < l; i++){
            num[i] = new TBinary(values[i]);
        }

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
            assertNotEquals(actTString, act);
            assertNotEquals(actObj, exp);
            assertNotEquals(dir, expObj);
        }

        TBinary a1 = new TBinary("000001101");
        TBinary a2 = new TBinary("01101");
        assertEquals(a1,a2);

        TBinary b1 = new TBinary("000001101");
        TBinary b2 = new TBinary("100001101");
        assertNotEquals(b1,b2);
    }
}
