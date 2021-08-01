package cl.uchile.dcc.scrabble.AST.ternary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.number.TInt;

/**
 * Represents a node that implements the comparison of numbers.
 */
public class Compare extends AbstractTerOp{

    /**
     *
     * @param a AST that has to be evaluated to a number
     * @param b AST that has to be evaluated to a number
     * @param cond if 1: Returns true if a>b, if not returns false
     *             if 0: Returns true if a=b, if not returns false
     *             if -1: Returns true if a<b, if not returns false
     */
    public Compare(ITree a, ITree b, TInt cond){
        super(a, b, cond);
    }

    @Override
    public TBool eval() {
        return this.getLc().eval().compare(this.getCc().eval(), ((TInt) (this.getRc())).getValue());
    }
}
