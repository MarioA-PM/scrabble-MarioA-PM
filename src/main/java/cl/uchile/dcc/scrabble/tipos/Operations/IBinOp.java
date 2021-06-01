package cl.uchile.dcc.scrabble.tipos.Operations;

/**
 * Represents a TBinary object respecting the operations allowed.
 */
public interface IBinOp extends INumber {

    /**
     * Returns the sum with the given object (returns a new object).
     */
    INumber suma(IOpBin n);

    /**
     * Returns the difference with the given object (returns a new object).
     */
    INumber resta(IOpBin n);

    /**
     * Returns the product with the given object (returns a new object).
     */
    INumber mult(IOpBin n);

    /**
     * Returns the division with the given object (returns a new object).
     */
    INumber div(IOpBin n);

}
