package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.flyweightFactory.*;

import java.util.Objects;

/**
 * Class that represents the String type.
 */
public class TString extends AbstractType{

    private final String s;

    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();


    public TString(String str){
        s = str;
    }

    public String getValue(){
        return s;
    }

    /**
     * The objects are equal if they contain the same string value.
     */
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
        return stringFac.getTString(this.getValue());
    }

    @Override
    public String toString(){
        return this.getValue();
    }

    /**
     * {@inheritDoc}
     * Returns the concatenation with the given value.
     */
    @Override
    public IType suma(IType n) {
        return n.sumaString(this);
    }
}
