package cl.uchile.dcc.scrabble.tipos.transformations;

import cl.uchile.dcc.scrabble.tipos.numeros.TFloat;
/**
 * Represents the types that can be converted to a TFloat.
 */
public interface ITransToFloat {
    /**
     * Converts the object to a TFloat object (returns a new object).
     */
    TFloat toTFloat();
}
