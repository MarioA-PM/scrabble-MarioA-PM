package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.*;

import java.util.Objects;

/**
 * Class that represents a float number (containing a primitive double type).
 */

public class TFloat extends AbstractType {

    private final double x;

    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();

    public TFloat(double x){
        this.x = x;
    }

    public double getValue(){
        return x;
    }

    /**
     * The objects are equal if they contain the same double value.
     */
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

    @Override
    public IType suma(IType n) {
        return n.sumarAFloat(this);
    }

    @Override
    public IType resta(IType n) {
        return n.restarAFloat(this);
    }

    @Override
    public IType mult(IType n) {
        return n.multAFloat(this);
    }

    @Override
    public IType div(IType n) {
        return n.divAFloat(this);
    }

    @Override
    public TFloat neg() {
        return floatFac.getTFloat(-this.getValue());
    }

    @Override
    public TFloat toTFloat() {
        return floatFac.getTFloat(this.getValue());
    }

    @Override
    public TFloat sumarAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() + this.getValue());
    }

    @Override
    public TFloat restarAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() - this.getValue());
    }

    @Override
    public TFloat multAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() * this.getValue());
    }

    @Override
    public TFloat divAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() / this.getValue());
    }

    @Override
    public TFloat sumarAInt(TInt n) {
        return floatFac.getTFloat(n.getValue() + this.getValue());
    }

    @Override
    public TFloat restarAInt(TInt n) {
        return floatFac.getTFloat(n.getValue() - this.getValue());
    }

    @Override
    public TFloat multAInt(TInt n) {
        return floatFac.getTFloat(n.getValue() * this.getValue());
    }

    @Override
    public TFloat divAInt(TInt n) {
        return floatFac.getTFloat(n.getValue() / this.getValue());
    }
}

