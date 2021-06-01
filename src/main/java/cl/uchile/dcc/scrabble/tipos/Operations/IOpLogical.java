package cl.uchile.dcc.scrabble.tipos.Operations;

import cl.uchile.dcc.scrabble.tipos.TBool;
import cl.uchile.dcc.scrabble.tipos.numeros.TBinary;

/**
 * Implements the methods required for logical operations.
 */
public interface IOpLogical extends INegation {

    /**
     * Returns the logical conjunction.
     */
    IOpLogical and(IOpLogical p);

    /**
     * Returns the logical conjunction with a TBool object.
     */
    IOpLogical andBool(TBool p);

    /**
     * Returns the logical conjunction with a TBinary object.
     */
    IOpLogical andBinary(TBinary p);

    /**
     * Returns the logical disjunction.
     */
    IOpLogical or(IOpLogical p);

    /**
     * Returns the logical disjunction with a TBool object.
     */
    IOpLogical orBool(TBool p);

    /**
     * Returns the logical disjunction with a TBinary object.
     */
    IOpLogical orBinary(TBinary p);

}
