package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.Operations.IOpLogical;
import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.number.TBinary;

import java.util.Objects;

/**
 *  Class that represents the boolean type.
 */
public class TBool extends AbstractType{

    private final boolean b;

    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();
    private final FlyweightTBoolFactory boolFac = FlyweightTBoolFactory.getInstance();

    public TBool(boolean b){
        this.b = b;
    }

    @Override
    public String toString(){
        return String.valueOf(b);
    }

    /**
     * The objects are equal if they contain the same boolean value.
     */
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

    @Override
    public TString toTString(){
        return stringFac.getTString(this.toString());
    }

    @Override
    public TBool neg() {
        return boolFac.getTBool(!this.getValue());
    }

    @Override
    public IType and(IOpLogical p) {
        return p.andBool(this);
    }

    @Override
    public IType andBool(TBool p) {
        return boolFac.getTBool(p.getValue() & this.getValue());
    }

    @Override
    public IType andBinary(TBinary p) {
        return p.andBool(this);
    }

    @Override
    public IType or(IOpLogical p) {
        return p.orBool(this);
    }

    @Override
    public IType orBool(TBool p) {
        return boolFac.getTBool(this.getValue() | p.getValue());
    }

    @Override
    public IType orBinary(TBinary p) {
        return p.orBool(this);
    }

    @Override
    public TBool toTBool() {
        return boolFac.getTBool(this.getValue());
    }
}
