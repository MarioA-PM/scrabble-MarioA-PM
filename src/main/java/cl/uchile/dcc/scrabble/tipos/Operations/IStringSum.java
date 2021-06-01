package cl.uchile.dcc.scrabble.tipos.Operations;

import cl.uchile.dcc.scrabble.tipos.TString;

/**
 * Implements the operation allowed for a TString object.
 */
public interface IStringSum {

    /**
     * Returns a new TString object containing the concatenation between
     * the value ot the TString and the String representation of the content of the given object.
     */
    TString suma(ISumString n);
}
