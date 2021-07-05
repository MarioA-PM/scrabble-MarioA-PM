package cl.uchile.dcc.scrabble.types;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.Operations.IBinOperand;
import cl.uchile.dcc.scrabble.Operations.IOpLogical;
import cl.uchile.dcc.scrabble.types.number.INumber;

/**
 * Implements the operations common to all types.
 */
public interface IType extends INumber, IOpLogical, IBinOperand, ITree {

    /**
     * Returns the concatenation between the value ot the TString and the
     * String representation of the content of the receiver object.
     */
    IType sumaString(TString n);

    /**
     * Converts the object to a TString object (returns a new object).
     */
    IType toTString();

    /**
     * Returns the sum with the given object (returns a new object).
     */
    IType suma(IType n);

    /**
     * Returns the difference with the given object (returns a new object).
     */
    IType resta(IType n);

    /**
     * Returns the product with the given object (returns a new object).
     */
    IType mult(IType n);

    /**
     * Returns the division with the given object (returns a new object).
     */
    IType div(IType n);

    /**
     * Converts the object to a TBool object (returns a new object).
     */
    IType toTBool();
}
