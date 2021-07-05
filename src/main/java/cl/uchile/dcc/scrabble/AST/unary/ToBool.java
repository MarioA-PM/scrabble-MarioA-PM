package cl.uchile.dcc.scrabble.AST.unary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents an internal node that holds the transformation to a TBool (unary operation).
 */
public class ToBool extends AbstractUnOp{

    public ToBool(ITree c){
        super(c);
    }

    @Override
    public IType eval() {
        IType rc = this.getChild().eval();
        return rc.toTBool();
    }
}
