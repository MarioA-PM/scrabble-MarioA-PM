package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.*;
import cl.uchile.dcc.scrabble.tipos.Operations.*;
import java.util.Objects;

/**
 * Class that represents a float number (containing a primitive double type).
 */

public class TFloat extends AbstractNumber
        implements INumberOp{

    private final double x;

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

    @Override
    public TString toTString(){
        return new TString(this.toString());
    }

    @Override
    public INumber suma(IOpBin n) {
        return n.sumaFloat(this);
    }

    @Override
    public INumber resta(IOpBin n) {
        return n.restaFloat(this);
    }

    @Override
    public INumber mult(IOpBin n) {
        return n.multFloat(this);
    }

    @Override
    public INumber div(IOpBin n) {
        return n.divFloat(this);
    }

    @Override
    public INumber suma(IOpNumber n) {
        return n.sumaFloat(this);
    }

    @Override
    public INumber resta(IOpNumber n) {
        return n.restaFloat(this);
    }

    @Override
    public INumber mult(IOpNumber n) {
        return n.multFloat(this);
    }

    @Override
    public INumber div(IOpNumber n) {
        return n.divFloat(this);
    }

    @Override
    public INumber sumaInt(TInt n) {
        return new TFloat(n.getValue() + this.getValue());
    }

    @Override
    public TFloat sumaFloat(TFloat n) {
        return new TFloat(n.getValue() + this.getValue());
    }

    /**
     * {@inheritDoc}
     * This method is never used given the order of the operands, therefore, it cannot
     * be called by a suma() method.
     * Needs to be implemented as the interface requires it.
     */
    @Override
    public INumber sumaBin(TBinary n) {
        return null;
    }

    @Override
    public INumber restaInt(TInt n) {
        return new TFloat(n.getValue() - this.getValue());
    }

    @Override
    public INumber restaFloat(TFloat n) {
        return new TFloat(n.getValue() - this.getValue());
    }

    /**
     * {@inheritDoc}
     * This method is never used given the order of the operands, therefore, it cannot
     * be called by a resta() method.
     * Needs to be implemented as the interface requires it.
     */
    @Override
    public INumber restaBin(TBinary n) {
        return null;
    }

    @Override
    public INumber multInt(TInt n) {
        return new TFloat(n.getValue() * this.getValue());
    }

    @Override
    public INumber multFloat(TFloat n) {
        return new TFloat(n.getValue() * this.getValue());
    }

    /**
     * {@inheritDoc}
     * This method is never used given the order of the operands, therefore, it cannot
     * be called by a mult() method.
     * Needs to be implemented as the interface requires it.
     */
    @Override
    public INumber multBin(TBinary n) {
        return null;
    }

    @Override
    public INumber divInt(TInt n) {
        return new TFloat(n.getValue() / this.getValue());
    }

    @Override
    public INumber divFloat(TFloat n) {
        return new TFloat(n.getValue() / this.getValue());
    }

    /**
     * {@inheritDoc}
     * This method is never used given the order of the operands, therefore, it cannot
     * be called by a div() method.
     * Needs to be implemented as the interface requires it.
     */
    @Override
    public INumber divBin(TBinary n) {
        return null;
    }

    @Override
    public TString sumaString(TString n) {
        return new TString(n.toString().concat(this.toString()));
    }

    @Override
    public INegation neg() {
        return new TFloat(-this.getValue());
    }

    @Override
    public TFloat toTFloat() {
        return new TFloat(this.getValue());
    }
}

