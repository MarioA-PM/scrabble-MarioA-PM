package cl.uchile.dcc.scrabble.AST.binary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents the subtraction operation as an internal node of the tree (with 2 children).
 */
public class Sub extends AbstractBinOp {

    public Sub(ITree lc, ITree rc){
        super(lc, rc);
    }

    @Override
    public IType eval() {
        IType rlc = getLeftChild().eval();
        IType rlr = getRightChild().eval();
        return rlc.resta(rlr);
    }
}
