package cl.uchile.dcc.scrabble.tipos;

import cl.uchile.dcc.scrabble.tipos.Operations.IStringSum;
import cl.uchile.dcc.scrabble.tipos.Operations.ISumString;
import java.util.Objects;

/**
 * Class that represents the String type.
 */
public class TString extends AbstractType implements IStringSum{

    private final String s;

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
    public TString toTString() {
        return new TString(this.getValue());
    }

    @Override
    public String toString(){
        return this.getValue();
    }

    @Override
    public TString sumaString(TString n) {
        return new TString(n.toString().concat(this.toString()));
    }

    @Override
    public TString suma(ISumString n) {
        return n.sumaString(this);
    }
}
