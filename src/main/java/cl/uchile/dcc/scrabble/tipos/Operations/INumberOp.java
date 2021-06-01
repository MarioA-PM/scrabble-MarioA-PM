package cl.uchile.dcc.scrabble.tipos.Operations;

/**
 * Represents the TInt and TFloat object respecting the operations allowed.
 */
public interface INumberOp extends IBinOp {

    /**
     * Returns the sum with the given object (returns a new object).
     */
    INumber suma(IOpNumber n);

    /**
     * Returns the difference with the given object (returns a new object).
     */
    INumber resta(IOpNumber n);

    /**
     * Returns the product with the given object (returns a new object).
     */
    INumber mult(IOpNumber n);

    /**
     * Returns the division with the given object (returns a new object).
     */
    INumber div(IOpNumber n);

}
