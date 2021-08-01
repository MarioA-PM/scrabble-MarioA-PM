package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.Operations.IOpLogical;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBoolFactory;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTStringFactory;
import cl.uchile.dcc.scrabble.types.number.TBinary;
import cl.uchile.dcc.scrabble.types.number.TFloat;
import cl.uchile.dcc.scrabble.types.number.TInt;

/**
 * Represents the general types of the project (strings, booleans and numbers).
 */
public class AbstractType implements IType {

    private final FlyweightTStringFactory stringFac = FlyweightTStringFactory.getInstance();

    @Override
    public IType sumaString(TString n) {
        return stringFac.getTString(n.toString().concat(this.toString()));
    }

    @Override
    public IType toTString() {
        return stringFac.getTString(this.toString());
    }

    @Override
    public IType suma(IType n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType resta(IType n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType mult(IType n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType div(IType n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType toTBool() {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType sumarABin(TBinary n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType restarABin(TBinary n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType multABin(TBinary n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType divABin(TBinary n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType toTInt() {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType toTBinary() {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType neg() {
        return NullObject.getUniqueInstance();
    }

    @Override
    public TBool compare(IType t, int n) {
        return FlyweightTBoolFactory.getInstance().getTBool(false);
    }

    @Override
    public TBool compareTInt(TInt t, int n) {
        return FlyweightTBoolFactory.getInstance().getTBool(false);
    }

    @Override
    public TBool compareTBin(TBinary t, int n) {
        return FlyweightTBoolFactory.getInstance().getTBool(false);
    }

    @Override
    public TBool compareTFloat(TFloat t, int n) {
        return FlyweightTBoolFactory.getInstance().getTBool(false);
    }

    @Override
    public IType and(IOpLogical p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType andBool(TBool p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType andBinary(TBinary p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType or(IOpLogical p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType orBool(TBool p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType orBinary(TBinary p) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType toTFloat() {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType sumarAFloat(TFloat n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType restarAFloat(TFloat n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType multAFloat(TFloat n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType divAFloat(TFloat n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType sumarAInt(TInt n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType restarAInt(TInt n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType multAInt(TInt n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType divAInt(TInt n) {
        return NullObject.getUniqueInstance();
    }

    @Override
    public IType eval() {
        return this;
    }
}
