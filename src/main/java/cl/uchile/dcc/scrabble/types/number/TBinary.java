package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.flyweightFactory.*;
import cl.uchile.dcc.scrabble.types.*;
import cl.uchile.dcc.scrabble.Operations.*;

import java.util.Objects;

/**
 * Class that represents a binary number.
 */
public class TBinary extends AbstractType {

    private final String b;

    private final FlyweightTBinaryFactory binFac = FlyweightTBinaryFactory.getInstance();
    private final FlyweightTIntFactory intFac = FlyweightTIntFactory.getInstance();
    private final FlyweightTFloatFactory floatFac = FlyweightTFloatFactory.getInstance();
    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();
    private final FlyweightTBoolFactory boolFac = FlyweightTBoolFactory.getInstance();


    public TBinary(String b) {
        this.b = b;
    }

    public String getValue() {
        return b;
    }

    @Override
    public String toString() {
        return getValue();
    }

    /**
     * The objects are equal if they represent the same number
     * Example: 0110 = 0000110
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TBinary)) {
            return false;
        }
        TBinary bin = (TBinary) o;
        if (this.getValue().equals(bin.getValue())) {
            return true;
        }
        if (!((this.getValue()).charAt(0) == ((bin.getValue()).charAt(0)))) {
            return false;
        }
        return this.toInt(this.getValue()) == this.toInt(bin.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(TBinary.class, this.toInt(getValue()));
    }

    @Override
    public TString toTString() {
        return stringFac.getTString(this.getValue());
    }

    @Override
    public IType suma(IType n) {
        return n.sumarABin(this);
    }

    @Override
    public IType resta(IType n) {
        return n.restarABin(this);
    }

    @Override
    public IType mult(IType n) {
        return n.multABin(this);
    }

    @Override
    public IType div(IType n) {
        return n.divABin(this);
    }

    /**
     * Returns the integer representation of the binary.
     */
    private int toInt(String binary) {
        if (bitToInt(binary.charAt(0)) == 0) {
            return positiveBinToInt(binary);
        } else {
            return negativeBinaryToInt(binary);
        }
    }

    /**
     * Converts the binary String representing a negative integer to integer.
     */
    private int negativeBinaryToInt(String binary) {
        int n = binary.length() - 1;
        int w = (int) (-bitToInt(binary.charAt(0)) * Math.pow(2, n));
        for (int i = n, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * (binary.charAt(i) == '1' ? 1 : 0);
        }
        return w;
    }

    /**
     * Converts the binary String representing a positive integer to integer.
     */
    private int positiveBinToInt(String binary) {
        int w = 0;
        for (int i = binary.length() - 1, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(binary.charAt(i));
        }
        return w;
    }

    /**
     * Converts the char value to int.
     */
    private int bitToInt(char bit) {
        return bit == '0' ? 0 : 1;
    }

    @Override
    public TBinary sumarABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() + this.toTInt().getValue())).toTBinary();
    }

    @Override
    public TBinary restarABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() - this.toTInt().getValue())).toTBinary();
    }

    @Override
    public TBinary multABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() * this.toTInt().getValue())).toTBinary();
    }

    @Override
    public TBinary divABin(TBinary n) {
        return (intFac.getTInt(n.toTInt().getValue() / this.toTInt().getValue())).toTBinary();
    }

    @Override
    public TInt toTInt(){
        return intFac.getTInt(this.toInt(this.getValue()));
    }

    @Override
    public TFloat toTFloat(){
        return floatFac.getTFloat(this.toTInt().getValue());
    }

    @Override
    public TFloat sumarAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() + this.toTInt().getValue());
    }

    @Override
    public TFloat restarAFloat(TFloat n) {
        return floatFac.getTFloat(n.getValue() - this.toTInt().getValue());
    }

    @Override
    public TFloat multAFloat(TFloat n) {
        return floatFac.getTFloat(n.toTFloat().getValue() * this.toTInt().getValue());
    }

    @Override
    public TFloat divAFloat(TFloat n) {
        return floatFac.getTFloat(n.toTFloat().getValue() / this.toTInt().getValue());
    }

    @Override
    public TInt sumarAInt(TInt n) {
        return intFac.getTInt(n.getValue() + this.toTInt().getValue());
    }

    @Override
    public TInt restarAInt(TInt n) {
        return intFac.getTInt(n.getValue() - this.toTInt().getValue());
    }

    @Override
    public TInt multAInt(TInt n) {
        return intFac.getTInt(n.getValue() * this.toTInt().getValue());
    }

    @Override
    public TInt divAInt(TInt n) {
        return intFac.getTInt(n.getValue() / this.toTInt().getValue());
    }

    /**
     * {@inheritDoc}
     * Implements the negation bit to bit.
     */
    @Override
    public TBinary neg() {
        StringBuilder bin = new StringBuilder(this.getValue());
        int l = this.getValue().length();
        for (int j = 0; j < l; j++){
            bin.replace(j, j + 1, String.valueOf((((int) bin.charAt(j))+1)%2));
        }
        return binFac.getTBinary(bin.toString());
    }

    @Override
    public IType and(IOpLogical p) {
        return p.andBinary(this);
    }

    /**
     * Implements the disjunction bit to bit between a binary String and the given boolean.
     */
    private String orBoolean(boolean b){
        if (!b){
            return this.getValue();
        }
        else{
            int l = this.getValue().length();
            return "1".repeat(l);
        }
    }

    /**
     * Implements the conjunction bit to bit between a binary String and the given boolean.
     */
    private String andBoolean(boolean b){
        if (b){
            return this.getValue();
        }
        else{
            int l = this.getValue().length();
            return "0".repeat(l);
        }
    }

    @Override
    public IType andBool(TBool p) {
        return binFac.getTBinary(this.andBoolean(p.getValue()));
    }

    /**
     * Implements the disjunction bit to bit between two binary strings with the same length.
     */
    private String orSameLength(String b){
        int l = this.getValue().length();
        String a = this.getValue();
        StringBuilder bin = new StringBuilder(l);
        for (int i = 0; i < l; i++){
            if (a.charAt(i) == b.charAt(i) & a.charAt(i) == '0'){
                bin.append("0");
            }
            else{
                bin.append("1");
            }
        }
        return bin.toString();
    }

    /**
     * Implements the conjunction bit to bit between two binary strings with the same length.
     */
    private String andSameLength(String b){
        int l = this.getValue().length();
        String a = this.getValue();
        StringBuilder bin = new StringBuilder(l);
        for (int i = 0; i < l; i++){
            if (a.charAt(i) == b.charAt(i) & a.charAt(i) == '1'){
                bin.append("1");
            }
            else{
                bin.append("0");
            }
        }
        return bin.toString();
    }

    /**
     * {@inheritDoc}
     * Implements the conjunction bit to bit.
     */
    @Override
    public IType andBinary(TBinary p) {
        int l1 = p.getValue().length();
        int l2 = this.getValue().length();
        if (l1 == l2){
            return binFac.getTBinary(this.andSameLength(p.getValue()));
        }
        else if(l2 > l1){
            String a = this.getValue().substring(0,l2 - l1);
            String b = this.getValue().substring(l2 - l1, l2);
            String c = a.concat(p.andSameLength(b));
            return binFac.getTBinary(c);
        }
        else {
            String a = p.getValue().substring(0,l1 - l2);
            String b = p.getValue().substring(l1 - l2, l1);
            String c = a.concat(this.andSameLength(b));
            return binFac.getTBinary(c);
        }
    }

    @Override
    public IType or(IOpLogical p) {
        return p.orBinary(this);
    }

    @Override
    public IType orBool(TBool p) {
        return binFac.getTBinary(this.orBoolean(p.getValue()));
    }

    /**
     * {@inheritDoc}
     * Implements the disjunction bit to bit.
     */
    @Override
    public IType orBinary(TBinary p) {
        int l1 = p.getValue().length();
        int l2 = this.getValue().length();
        if (l1 == l2){
            return binFac.getTBinary(this.orSameLength(p.getValue()));
        }
        else if(l2 > l1){
            String a = this.getValue().substring(0,l2 - l1);
            String b = this.getValue().substring(l2 - l1, l2);
            String c = a.concat(p.orSameLength(b));
            return binFac.getTBinary(c);
        }
        else {
            String a = p.getValue().substring(0,l1 - l2);
            String b = p.getValue().substring(l1 - l2, l1);
            String c = a.concat(this.orSameLength(b));
            return binFac.getTBinary(c);
        }
    }

    @Override
    public TBinary toTBinary() {
        return binFac.getTBinary(this.getValue());
    }

    @Override
    public TBool compare(IType t, int n){
        return t.compareTBin(this, -n);
    }

    @Override
    public TBool compareTInt(TInt t, int n) {
        int cp = Integer.compare(this.toInt(this.getValue()), t.getValue());
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }

    @Override
    public TBool compareTBin(TBinary t, int n) {
        int cp = Integer.compare(this.toInt(this.getValue()), t.toInt(t.getValue()));
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }

    @Override
    public TBool compareTFloat(TFloat t, int n) {
        int cp = Double.compare(this.toInt(this.getValue()), t.getValue());
        return boolFac.getTBool(cp*n > 0 | cp == n);
    }
}
