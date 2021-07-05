package cl.uchile.dcc.scrabble.AST.unary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Represents and internal node that calculates the negation (unary operation).
 */
public class Negation extends AbstractUnOp {

    public Negation(ITree c){
        super(c);
    }

    @Override
    public IType eval() {
        IType rc = this.getChild().eval();
        return rc.neg();
    }
}
