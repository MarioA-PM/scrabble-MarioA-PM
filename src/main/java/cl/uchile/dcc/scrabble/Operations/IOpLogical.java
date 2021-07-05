package cl.uchile.dcc.scrabble.Operations;

import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.number.TBinary;

/**
 * Implements the methods required for logical operations.
 */
public interface IOpLogical {

    /**
     * Returns the logical conjunction.
     */
    IType and(IOpLogical p);

    /**
     * Returns the logical conjunction with a TBool object.
     */
    IType andBool(TBool p);

    /**
     * Returns the logical conjunction with a TBinary object.
     */
    IType andBinary(TBinary p);

    /**
     * Returns the logical disjunction.
     */
    IType or(IOpLogical p);

    /**
     * Returns the logical disjunction with a TBool object.
     */
    IType orBool(TBool p);

    /**
     * Returns the logical disjunction with a TBinary object.
     */
    IType orBinary(TBinary p);

    /**
     * Returns the negation (returns a new object).
     */
    IType neg();

}
