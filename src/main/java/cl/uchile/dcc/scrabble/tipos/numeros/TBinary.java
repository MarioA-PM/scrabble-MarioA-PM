package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.*;
import cl.uchile.dcc.scrabble.tipos.Operations.*;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToBinary;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToInt;
import java.util.Objects;

/**
 * Class that represents a binary number.
 */
public class TBinary extends AbstractNumber
        implements IOpBin, IOpLogical, ITransToBinary, ITransToInt{

    private final String b;

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
        return new TString(this.getValue());
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
    public TInt toTInt(){
        return new TInt(this.toInt(this.getValue()));
    }

    @Override
    public TFloat toTFloat(){
        return new TFloat(this.toTInt().getValue());
    }

    @Override
    public INumber suma(IOpBin n) {
        return n.sumaBin(this);
    }

    @Override
    public INumber resta(IOpBin n) {
        return n.restaBin(this);
    }

    @Override
    public INumber mult(IOpBin n) {
        return n.multBin(this);
    }

    @Override
    public INumber div(IOpBin n) {
        return n.divBin(this);
    }

    @Override
    public INumber sumaInt(TInt n) {
        return new TInt(n.getValue() + this.toTInt().getValue());
    }

    @Override
    public INumber sumaFloat(TFloat n) {
        return new TFloat(n.getValue() + this.toTInt().getValue());
    }

    @Override
    public INumber sumaBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() + this.toTInt().getValue())).toTBinary();
    }

    @Override
    public INumber restaInt(TInt n) {
        return new TInt(n.getValue() - this.toTInt().getValue());
    }

    @Override
    public INumber restaFloat(TFloat n) {
        return new TFloat(n.getValue() - this.toTInt().getValue());
    }

    @Override
    public INumber restaBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() - this.toTInt().getValue())).toTBinary();
    }

    @Override
    public INumber multInt(TInt n) {
        return new TInt(n.getValue() * this.toTInt().getValue());
    }

    @Override
    public INumber multFloat(TFloat n) {
        return new TFloat(n.getValue() * this.toTInt().getValue());
    }

    @Override
    public INumber multBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() * this.toTInt().getValue())).toTBinary();
    }

    @Override
    public INumber divInt(TInt n) {
        return new TInt(n.getValue() / this.toTInt().getValue());
    }

    @Override
    public INumber divFloat(TFloat n) {
        return new TFloat(n.getValue() / this.toTInt().getValue());
    }

    @Override
    public INumber divBin(TBinary n) {
        return (new TInt(n.toTInt().getValue() / this.toTInt().getValue())).toTBinary();
    }

    @Override
    public TString sumaString(TString n) {
        return new TString(n.toString().concat(this.toString()));
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
        return new TBinary(bin.toString());
    }

    @Override
    public IOpLogical and(IOpLogical p) {
        return p.andBinary(this);
    }

    /**
     * Implements the disjunction bit to bit between a binary String and giver boolean.
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
     * Implements the conjunction bit to bit between a binary String and giver boolean.
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
    public IOpLogical andBool(TBool p) {
        return new TBinary(this.andBoolean(p.getValue()));
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
     * Implements the conjunction bit to bit. If the operands do not have the same size,
     * the shorter is filled with the sign digit.
     */
    @Override
    public IOpLogical andBinary(TBinary p) {
        int l1 = p.getValue().length();
        int l2 = this.getValue().length();
        if (l1 == l2){
            return new TBinary(this.andSameLength(p.getValue()));
        }
        else if(l2 > l1){
            String a = this.getValue().substring(0,l2 - l1);
            String b = this.getValue().substring(l2 - l1, l2);
            boolean d = p.getValue().charAt(0) == '1';
            String c = ((new TBinary(a)).andBoolean(d)).concat(p.andSameLength(b));
            return new TBinary(c);
        }
        else {
            String a = p.getValue().substring(0,l1 - l2);
            String b = p.getValue().substring(l1 - l2, l1);
            boolean d = this.getValue().charAt(0) == '1';
            String c = (new TBinary(a)).andBoolean(d).concat(this.andSameLength(b));
            return new TBinary(c);
        }
    }

    @Override
    public IOpLogical or(IOpLogical p) {
        return p.orBinary(this);
    }

    @Override
    public IOpLogical orBool(TBool p) {
        return new TBinary(this.orBoolean(p.getValue()));
    }

    /**
     * {@inheritDoc}
     * Implements the disjunction bit to bit. If the operands do not have the same size,
     * the shorter is filled with the sign digit.
     */
    @Override
    public IOpLogical orBinary(TBinary p) {
        int l1 = p.getValue().length();
        int l2 = this.getValue().length();
        if (l1 == l2){
            return new TBinary(this.orSameLength(p.getValue()));
        }
        else if(l2 > l1){
            String a = this.getValue().substring(0,l2 - l1);
            String b = this.getValue().substring(l2 - l1, l2);
            boolean d = p.getValue().charAt(0) == '1';
            String c = (new TBinary(a)).orBoolean(d).concat(p.orSameLength(b));
            return new TBinary(c);
        }
        else {
            String a = p.getValue().substring(0,l1 - l2);
            String b = p.getValue().substring(l1 - l2, l1);
            boolean d = this.getValue().charAt(0) == '1';
            String c = (new TBinary(a)).orBoolean(d).concat(this.orSameLength(b));
            return new TBinary(c);
        }
    }

    @Override
    public TBinary toTBinary() {
        return new TBinary(this.getValue());
    }
}
