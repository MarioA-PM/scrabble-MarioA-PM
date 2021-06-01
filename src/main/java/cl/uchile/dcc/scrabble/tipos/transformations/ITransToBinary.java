package cl.uchile.dcc.scrabble.tipos.transformations;

import cl.uchile.dcc.scrabble.tipos.numeros.TBinary;
/**
 * Represents the types that can be converted to a TBinary.
 */
public interface ITransToBinary {
    /**
     * Converts the object to a TBinary object (returns a new object).
     */
    TBinary toTBinary();
}
