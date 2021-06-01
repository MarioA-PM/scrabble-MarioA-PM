package cl.uchile.dcc.scrabble.tipos.Operations;

import cl.uchile.dcc.scrabble.tipos.TString;

/**
 * Implements the addition operation between a TString and the other types.
 */
public interface ISumString {

    /**
     * Returns the concatenation between the value ot the TString and the
     * String representation of the content of the receiver object.
     */
    TString sumaString(TString n);
}
