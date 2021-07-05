package cl.uchile.dcc.scrabble.AST.binary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents the addition operation as an internal node of the tree (with 2 children).
 */
public class Add extends AbstractBinOp {

    public Add(ITree lc, ITree rc){
        super(lc, rc);
    }

    @Override
    public IType eval() {
        IType rlc = getLeftChild().eval();
        IType rlr = getRightChild().eval();
        return rlc.suma(rlr);
    }
}
