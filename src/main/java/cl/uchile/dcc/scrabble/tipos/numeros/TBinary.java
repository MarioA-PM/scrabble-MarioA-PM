package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;

import java.util.Objects;

/**
 * Class that represents a binary number.
 */

public class TBinary {

    private String b;

    public TBinary(String b) {
        this.b = b;
    }

    public String getValue() {
        return b;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TBinary)) {
            return false;
        }
        TBinary bin = (TBinary) o;
        if (this.getValue().equals(bin.getValue())) {
            return true;
        }
        if (!((this.getValue()).charAt(0) == ((bin.getValue()).charAt(0)))) {
            return false;
        }
        return this.toInt(this.getValue()) == this.toInt(bin.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(TBinary.class, this.toInt(getValue()));
    }

    /**
     * Transforms the object to a TString (returns a new object)
     */

    public TString toTString() {
        return new TString(this.getValue());
    }

    private int toInt(String binary) {
        if (bitToInt(binary.charAt(0)) == 0) {
            return positiveBinToInt(binary);
        } else {
            return negativeBinaryToInt(binary);
        }
    }

    private int negativeBinaryToInt(String binary) {
        int n = binary.length() - 1;
        int w = (int) (-bitToInt(binary.charAt(0)) * Math.pow(2, n));
        for (int i = n, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * (binary.charAt(i) == '1' ? 1 : 0);
        }
        return w;
    }
    private int positiveBinToInt(String binary) {
        int w = 0;
        for (int i = binary.length() - 1, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(binary.charAt(i));
        }
        return w;
    }
    private int bitToInt(char bit) {
        return bit == '0' ? 0 : 1;
    }

    /**
     * Converts the object to a TInt (returns a new object)
     */
    public TInt toTInt(){
        return new TInt(this.toInt(this.getValue()));
    }

    /**
     * Converts the object to a TFloat (returns a new object)
     */
    public TFloat toTFloat(){
        return new TFloat(this.toTInt().getValue());
    }
}
