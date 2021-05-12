package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;

import java.util.Objects;

/**
 * Class that represents an integer number (containing an int type)
 */

public class TInt {

    private int i;

    public TInt(int i){
        this.i = i;
    }

    public int getValue(){
        return this.i;
    }

    @Override
    public String toString(){
        return String.valueOf(this.getValue());
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof TInt)){
            return false;
        }
        TInt n = (TInt) o;
        return this.getValue() == n.getValue();
    }

    @Override
    public int hashCode(){
        return Objects.hash(TInt.class, this.getValue());
    }

    /**
     * Converts the TInt object to TString object (returns
     * a new TString).
     */
    public TString toTString(){
        return new TString(this.toString());
    }

    /**
     * Converts the TInt object to TFloat object (returns
     * a new TString).
     */
    public TFloat toTFloat(){
        double x = this.getValue();
        return new TFloat(x);
    }

    /**
     * Returns the binary representation of the number as a String.
     */
    public String positiveIntToBinary(int k){
        String fourBytes = "00000000000000000000000000000000";
        StringBuilder bin = new StringBuilder(fourBytes);
        if (k == 0){
            return fourBytes;
        }
        int length = ((int)(Math.log(k)/Math.log(2))) + 1;
        int rest = k;
        for (int j = 0; j < length; j++){
            String digit = String.valueOf(rest%2);
            bin.replace(32 - j - 1, 32 - j, digit);
            rest = (int) rest/2;
        }
        return bin.toString();
    }

    /**
     * Returns the two's complement of a binary number.
     */
    public String twosComplement(String b){
        // 1's complement
        StringBuilder bin = new StringBuilder(b);
        for (int j = 0; j < 32; j++){
            bin.replace(j, j + 1, String.valueOf((((int) bin.charAt(j))+1)%2));
        }

        //plus 1
        int j = 31;
        while (j >= 0){
            if (bin.toString().charAt(j) == '1'){
                bin.replace(j, j+1, "0");
            }
            else{
                bin.replace(j, j+1, "1");
                return bin.toString();
            }
            j -= 1;
        }
        return "Invalid Value";
    }

    /**
     * Returns a new TBinary object with the binary
     * representation of que given number.
     */
    public TBinary toTBinary(){
        int abs = Math.abs(this.getValue());
        String b = positiveIntToBinary(abs);
        if (this.getValue() < 0){
            b = twosComplement(b);
        }
        return new TBinary(b);
    }


}
