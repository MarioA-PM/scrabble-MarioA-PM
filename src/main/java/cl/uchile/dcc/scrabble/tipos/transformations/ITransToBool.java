package cl.uchile.dcc.scrabble.tipos.transformations;

import cl.uchile.dcc.scrabble.tipos.TBool;

/**
 * Represents the types that can be converted to a TBool.
 */
public interface ITransToBool {
    /**
     * Converts the object to a TBool object (returns a new object).
     */
    TBool toTBool();
}
