package cl.uchile.dcc.scrabble.AST;

import cl.uchile.dcc.scrabble.types.IType;

/**
 * Interface (component in the composite pattern) that represent the trees.
 */
public interface ITree {

    /**
     * Evaluates recursively the tree.
     */
    IType eval();
}
