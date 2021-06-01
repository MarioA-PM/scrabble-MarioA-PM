package cl.uchile.dcc.scrabble.tipos;

import cl.uchile.dcc.scrabble.tipos.Operations.IOpLogical;
import cl.uchile.dcc.scrabble.tipos.numeros.TBinary;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToBool;
import java.util.Objects;

/**
 *  Class that represents the boolean type.
 */
public class TBool extends AbstractType implements IOpLogical, ITransToBool {

    private final boolean b;

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

    @Override
    public TString toTString(){
        return new TString(this.toString());
    }

    @Override
    public TBool neg() {
        return new TBool(!this.getValue());
    }

    @Override
    public IOpLogical and(IOpLogical p) {
        return p.andBool(this);
    }

    @Override
    public IOpLogical andBool(TBool p) {
        return new TBool(p.getValue() & this.getValue());
    }

    @Override
    public IOpLogical andBinary(TBinary p) {
        return p.andBool(this);
    }

    @Override
    public IOpLogical or(IOpLogical p) {
        return p.orBool(this);
    }

    @Override
    public IOpLogical orBool(TBool p) {
        return new TBool(this.getValue() | p.getValue());
    }

    @Override
    public IOpLogical orBinary(TBinary p) {
        return p.orBool(this);
    }

    @Override
    public TString sumaString(TString n) {
        return new TString(n.toString().concat(this.toString()));
    }

    @Override
    public TBool toTBool() {
        return new TBool(this.getValue());
    }
}
