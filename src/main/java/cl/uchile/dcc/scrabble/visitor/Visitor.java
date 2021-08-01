package cl.uchile.dcc.scrabble.visitor;

import cl.uchile.dcc.scrabble.AST.ternary.If;
import cl.uchile.dcc.scrabble.AST.binary.While;

/**
 * Declares the methods applied to the control flow nodes.
 */
public interface Visitor {

    /**
     * Implements the while statement behavior.
     */
    void visitWhile(While n);

    /**
     * Implements the if statement behavior.
     */
    void visitIf(If n);
}
