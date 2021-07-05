package cl.uchile.dcc.scrabble.AST.unary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents an internal node that holds the transformation to a TInt (unary operation).
 */
public class ToInt extends AbstractUnOp{

    public ToInt(ITree c){
        super(c);
    }

    @Override
    public IType eval() {
        IType rc = this.getChild().eval();
        return rc.toTInt();
    }
}
