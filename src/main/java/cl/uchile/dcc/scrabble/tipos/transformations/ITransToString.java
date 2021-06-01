package cl.uchile.dcc.scrabble.tipos.transformations;

import cl.uchile.dcc.scrabble.tipos.TString;
/**
 * Represents the types that can be converted to a TString.
 */
public interface ITransToString {
    /**
     * Converts the object to a TString object (returns a new object).
     */
    TString toTString();
}
