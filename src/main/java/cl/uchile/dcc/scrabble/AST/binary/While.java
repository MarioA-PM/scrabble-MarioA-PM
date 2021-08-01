package cl.uchile.dcc.scrabble.AST.binary;

import cl.uchile.dcc.scrabble.AST.ITree;
import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.visitor.ControlFlowVisitor;

/**
 * Represents the while loop statement as an AST
 */
public class While extends AbstractBinOp {

    private final ControlFlowVisitor v = new ControlFlowVisitor();

    public While(ITree cond,ITree whileTrue){
        super(cond, whileTrue);
    }

    @Override
    public IType eval() {
        v.visitWhile(this);
        return null;
    }
}
