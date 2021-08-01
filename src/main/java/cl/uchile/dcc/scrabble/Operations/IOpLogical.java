package cl.uchile.dcc.scrabble.Operations;

import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.number.TBinary;
import cl.uchile.dcc.scrabble.types.number.TFloat;
import cl.uchile.dcc.scrabble.types.number.TInt;

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

    /**
     * Returns the truth value of comparing the object with the given IType object.
     * n = -1 indicates less than.
     * n = 0 indicates equals.
     * n = 1 indicates greater than.
     */
    TBool compare(IType t, int n);

    /**
     * Returns the truth value of comparing the object with the given TInt object.
     * n = -1 indicates less than.
     * n = 0 indicates equals.
     * n = 1 indicates greater than.
     */
    TBool compareTInt(TInt t, int n);

    /**
     * Returns the truth value of comparing the object with the given TBinary object.
     * n = -1 indicates less than.
     * n = 0 indicates equals.
     * n = 1 indicates greater than.
     */
    TBool compareTBin(TBinary t, int n);

    /**
     * Returns the truth value of comparing the object with the given TFloat object.
     * n = -1 indicates less than.
     * n = 0 indicates equals.
     * n = 1 indicates greater than.
     */
    TBool compareTFloat(TFloat t, int n);

}
