package cl.uchile.dcc.scrabble.AST.ternary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.visitor.ControlFlowVisitor;

/**
 * Represents a node that implements the if statement.
 */
public class If extends AbstractTerOp {

    private final ControlFlowVisitor v = new ControlFlowVisitor();

    public If(ITree cond, ITree ifTrue, ITree ifFalse){
        super(cond, ifTrue, ifFalse);
    }

    @Override
    public IType eval() {
        v.visitIf(this);
        return null;
    }
}
