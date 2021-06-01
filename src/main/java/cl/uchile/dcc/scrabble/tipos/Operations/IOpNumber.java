package cl.uchile.dcc.scrabble.tipos.Operations;

import cl.uchile.dcc.scrabble.tipos.Operations.INumber;
import cl.uchile.dcc.scrabble.tipos.numeros.TBinary;
import cl.uchile.dcc.scrabble.tipos.numeros.TFloat;
import cl.uchile.dcc.scrabble.tipos.numeros.TInt;

/**
 * Represents the TFloat type respecting the operations
 * allowed and its side (left or right of the operation).
 */
public interface IOpNumber extends INumber {

    /**
     * Returns the sum with a TInt object (returns a new object).
     */
    INumber sumaInt(TInt n);

    /**
     * Returns the sum with a TFloat object (returns a new object).
     */
    INumber sumaFloat(TFloat n);

    /**
     * Returns the sum with a TBinary object (returns a new object).
     */
    INumber sumaBin(TBinary n);

    /**
     * Returns the difference with a TInt object (returns a new object).
     */
    INumber restaInt(TInt n);

    /**
     * Returns the difference with a TFloat object (returns a new object).
     */
    INumber restaFloat(TFloat n);

    /**
     * Returns the difference with a TBinary object (returns a new object).
     */
    INumber restaBin(TBinary n);

    /**
     * Returns the product with a TInt object (returns a new object).
     */
    INumber multInt(TInt n);

    /**
     * Returns the product with a TFloat object (returns a new object).
     */
    INumber multFloat(TFloat n);

    /**
     * Returns the product with a TBinary object (returns a new object).
     */
    INumber multBin(TBinary n);

    /**
     * Returns the division with a TInt object (returns a new object).
     */
    INumber divInt(TInt n);

    /**
     * Returns the division with a TFloat object (returns a new object).
     */
    INumber divFloat(TFloat n);

    /**
     * Returns the division with a TBinary object (returns a new object).
     */
    INumber divBin(TBinary n);

}
