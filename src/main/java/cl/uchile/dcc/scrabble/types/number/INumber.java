package cl.uchile.dcc.scrabble.types.number;

import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents a general number type (TBinary, TInt and TFloat).
 */
public interface INumber{

    /**
     * Converts the object to a TFloat object (returns a new object).
     */
    IType toTFloat();

    /**
     * Returns the sum with the given TFloat (returns a new object).
     */
    IType sumarAFloat(TFloat n);

    /**
     * Returns the difference with the given TFloat (returns a new object).
     */
    IType restarAFloat(TFloat n);

    /**
     * Returns the product with the given object (returns a new object).
     */
    IType multAFloat(TFloat n);

    /**
     * Returns the division with the given object (returns a new object).
     */
    IType divAFloat(TFloat n);

    /**
     * Returns the sum with the given TInt (returns a new object).
     */
    IType sumarAInt(TInt n);

    /**
     * Returns the difference with the given TInt (returns a new object).
     */
    IType restarAInt(TInt n);

    /**
     * Returns the product with the given TInt (returns a new object).
     */
    IType multAInt(TInt n);

    /**
     * Returns the division with the given TInt (returns a new object).
     */
    IType divAInt(TInt n);

}
