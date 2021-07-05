package cl.uchile.dcc.scrabble.AST.unary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents an internal node that holds the transformation to a TBinary (unary operation).
 */
public class ToBinary extends AbstractUnOp{

    public ToBinary(ITree c){
        super(c);
    }

    @Override
    public IType eval() {
        IType rc = this.getChild().eval();
        return rc.toTBinary();
    }
}
