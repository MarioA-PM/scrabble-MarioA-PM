package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.*;
import cl.uchile.dcc.scrabble.tipos.Operations.*;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToBinary;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToInt;
import java.util.Objects;

/**
 * Class that represents an integer number (containing a primitive int type).
 */

public class TInt extends AbstractNumber
        implements INumberOp, IOpBin, ITransToInt, ITransToBinary{

    private final int i;

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

    @Override
    public TString toTString(){
        return new TString(this.toString());
    }

    @Override
    public TFloat toTFloat(){
        double x = this.getValue();
        return new TFloat(x);
    }

    /**
     * Returns the binary representation of the number as a String.
     */
    private String positiveIntToBinary(int k){
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
            rest = rest/2;
        }
        return bin.toString();
    }

    /**
     * Returns the two's complement of a binary number.
     */
    private String twosComplement(String b){
        // 1's complement
        TBinary a = (new TBinary(b)).neg();

        StringBuilder bin = new StringBuilder(a.getValue());
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
        return null;
    }

    @Override
    public TBinary toTBinary(){
        int abs = Math.abs(this.getValue());
        String b = positiveIntToBinary(abs);
        if (this.getValue() < 0){
            b = twosComplement(b);
        }
        return new TBinary(b);
    }

    @Override
    public TString sumaString(TString n) {
        return new TString(n.toString().concat(this.toString()));
    }

    @Override
    public INumber suma(IOpNumber n) {
        return n.sumaInt(this);
    }

    @Override
    public INumber resta(IOpNumber n) {
        return n.restaInt(this);
    }

    @Override
    public INumber mult(IOpNumber n) {
        return n.multInt(this);
    }

    @Override
    public INumber div(IOpNumber n) {
        return n.divInt(this);
    }

    @Override
    public INumber sumaInt(TInt n) {
        return new TInt(n.getValue() + this.getValue());
    }

    @Override
    public INumber sumaFloat(TFloat n) {
        return new TFloat(n.getValue() + this.getValue());
    }

    @Override
    public INumber sumaBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() + this.getValue())).toTBinary();
    }

    @Override
    public INumber restaInt(TInt n) {
        return new TInt(n.getValue() - this.getValue());
    }

    @Override
    public INumber restaFloat(TFloat n) {
        return new TFloat(n.getValue() - this.getValue());
    }

    @Override
    public INumber restaBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() - this.getValue())).toTBinary();
    }

    @Override
    public INumber multInt(TInt n) {
        return new TInt(n.getValue() * this.getValue());
    }

    @Override
    public INumber multFloat(TFloat n) {
        return new TFloat(n.getValue() * this.getValue());
    }

    @Override
    public INumber multBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() * this.getValue())).toTBinary();
    }

    @Override
    public INumber divInt(TInt n) {
        return new TInt(n.getValue() / this.getValue());
    }

    @Override
    public INumber divFloat(TFloat n) {
        return new TFloat(n.getValue() / this.getValue());
    }

    @Override
    public INumber divBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() / this.getValue())).toTBinary();
    }

    @Override
    public INumber suma(IOpBin n) {
        return n.sumaInt(this);
    }

    @Override
    public INumber resta(IOpBin n) {
        return n.restaInt(this);
    }

    @Override
    public INumber mult(IOpBin n) {
        return n.multInt(this);
    }

    @Override
    public INumber div(IOpBin n) {
        return n.divInt(this);
    }

    @Override
    public INegation neg() {
        return new TInt(-this.getValue());
    }

    @Override
    public TInt toTInt() {
        return new TInt(this.getValue());
    }
}
