package cl.uchile.dcc.scrabble.Operations;

import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.types.number.TBinary;

/**
 * Holds the operations for the binary type.
 */
public interface IBinOperand {

    /**
     * Returns the sum with the given TBinary (returns a new object).
     */
    IType sumarABin(TBinary n);

    /**
     * Returns the difference with the given TBinary (returns a new object).
     */
    IType restarABin(TBinary n);

    /**
     * Returns the product with the given TBinary (returns a new object).
     */
    IType multABin(TBinary n);

    /**
     * Returns the division with the given TBinary (returns a new object).
     */
    IType divABin(TBinary n);

    /**
     * Converts the object to a TInt (returns a new object).
     */
    IType toTInt();

    /**
     * Converts the object to a TBinary (returns a new object).
     */
    IType toTBinary();
}
