package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.*;

import java.util.Objects;

/**
 * Class that represents an integer number (containing a primitive int type).
 */
public class TInt extends AbstractType{

    private final int i;

    private final FlyweightTBinaryFactory binFac = FlyweightTBinaryFactory.getInstance();
    private final FlyweightTIntFactory intFac = FlyweightTIntFactory.getInstance();
    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();
    private final FlyweightTBoolFactory boolFac = FlyweightTBoolFactory.getInstance();

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

    /**
     * The objects are equal if they contain the same int value.
     */
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
    public IType suma(IType n) {
        return n.sumarAInt(this);
    }

    @Override
    public IType resta(IType n) {
        return n.restarAInt(this);
    }

    @Override
    public IType mult(IType n) {
        return n.multAInt(this);
    }

    @Override
    public IType div(IType n) {
        return n.divAInt(this);
    }

    @Override
    public TFloat toTFloat(){
        double x = this.getValue();
        return floatFac.getTFloat(x);
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
    public TInt sumarAInt(TInt n) {
        return intFac.getTInt(n.getValue() + this.getValue());
    }

    @Override
    public TInt restarAInt(TInt n) {
        return intFac.getTInt(n.getValue() - this.getValue());
    }

    @Override
    public TInt multAInt(TInt n) {
        return intFac.getTInt(n.getValue() * this.getValue());
    }

    @Override
    public TInt divAInt(TInt n) {
        return intFac.getTInt(n.getValue() / this.getValue());
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
        TBinary a = (binFac.getTBinary(b)).neg();

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
        return binFac.getTBinary(b);
    }

    @Override
    public TInt neg() {
        return intFac.getTInt(-this.getValue());
    }

    @Override
    public TBinary sumarABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() + this.getValue())).toTBinary();
    }

    @Override
    public TBinary restarABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() - this.getValue())).toTBinary();
    }

    @Override
    public TBinary multABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() * this.getValue())).toTBinary();
    }

    @Override
    public TBinary divABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() / this.getValue())).toTBinary();
    }

    @Override
    public TInt toTInt() {
        return intFac.getTInt(this.getValue());
    }

    @Override
    public TBool compare(IType t, int n){
        return t.compareTInt(this, -n);
    }

    @Override
    public TBool compareTInt(TInt t, int n) {
        int cp = Integer.compare(this.getValue(), t.getValue());
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }

    @Override
    public TBool compareTBin(TBinary t, int n) {
        int cp = Integer.compare(this.getValue(), t.toTInt().getValue());
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }

    @Override
    public TBool compareTFloat(TFloat t, int n) {
        int cp = Double.compare(this.getValue(), t.getValue());
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }
}
