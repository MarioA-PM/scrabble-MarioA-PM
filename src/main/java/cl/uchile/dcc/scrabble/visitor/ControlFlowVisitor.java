package cl.uchile.dcc.scrabble.visitor;

import cl.uchile.dcc.scrabble.AST.ternary.If;
import cl.uchile.dcc.scrabble.AST.binary.While;
import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBoolFactory;

/**
 * Implements the functionality of the control flow nodes: while and if statement.
 */
public class ControlFlowVisitor implements Visitor{

    @Override
    public void visitWhile(While n) {
        while ((n.getLeftChild().eval()).equals(FlyweightTBoolFactory.getInstance().getTBool(true))){
            n.getRightChild().eval();
        }
    }

    @Override
    public void visitIf(If n){
        if (n.getLc().eval().equals(FlyweightTBoolFactory.getInstance().getTBool(true))){
            n.getCc().eval();
        }
        else{
            n.getRc().eval();
        }
    }

}
