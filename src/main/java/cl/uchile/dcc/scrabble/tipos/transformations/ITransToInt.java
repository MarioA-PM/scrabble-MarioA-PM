package cl.uchile.dcc.scrabble.tipos.transformations;

import cl.uchile.dcc.scrabble.tipos.numeros.TInt;
/**
 * Represents the types that can be converted to a TInt.
 */
public interface ITransToInt {
    /**
     * Converts the object to a TInt object (returns a new object).
     */
    TInt toTInt();
}
