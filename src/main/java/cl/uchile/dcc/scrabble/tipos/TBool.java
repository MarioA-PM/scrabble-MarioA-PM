package cl.uchile.dcc.scrabble.tipos;

import java.util.Objects;

/**
 *  Class that represents boolean type.
 */
public class TBool {

    private boolean b;

    public TBool(boolean b){
        this.b = b;
    }

    @Override
    public String toString(){
        return String.valueOf(b);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof TBool)){
            return false;
        }
        TBool n = (TBool) o;
        return this.getValue() == n.getValue();
    }

    @Override
    public int hashCode(){
        return Objects.hash(TBool.class, this.getValue());
    }

    public boolean getValue(){
        return this.b;
    }

    /**
     * Converts from TBool to TString returning a new object.
     */
    public TString toTString(){
        return new TString(this.toString());
    }
}
