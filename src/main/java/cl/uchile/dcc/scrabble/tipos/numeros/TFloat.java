package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.TString;

import java.util.Objects;

/**
 * Class that represents a float number (containing a double type)
 */

public class TFloat {

    private double x;

    public TFloat(double x){
        this.x = x;
    }

    public double getValue(){
        return x;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof TFloat)){
            return false;
        }
        TFloat num = (TFloat) o;
        return num.getValue() == this.x;
    }

    @Override
    public int hashCode(){
        return Objects.hash(TFloat.class, this.getValue());
    }

    @Override
    public String toString(){
        return String.valueOf(x);
    }

    /**
     * Converts the TFloat object to TString object (returns
     * a new TString).
     */
    public TString toTString(){
        return new TString(this.toString());
    }
}

