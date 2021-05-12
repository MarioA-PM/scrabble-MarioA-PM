package cl.uchile.dcc.scrabble.tipos;

import java.util.Objects;

/**
 * Class that represents the String type.
 */
public class TString {

    private String s;

    public TString(String str){
        s = str;
    }

    public String getValue(){
        return s;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof TString)){
            return false;
        }
        TString obj = (TString) o;
        return obj.getValue().equals(this.getValue());
    }

    @Override
    public int hashCode(){
        return Objects.hash(TString.class, this.getValue());
    }

    @Override
    public String toString(){
        return this.getValue();
    }
}
