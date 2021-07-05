package cl.uchile.dcc.scrabble.AST.unary;

import cl.uchile.dcc.scrabble.AST.ITree;

/**
 * Abstract class that represents a node with one child.
 */
public abstract class AbstractUnOp implements ITree {

    private final ITree c;

    public AbstractUnOp(ITree c){
        this.c = c;
    }

    public ITree getChild(){
        return this.c;
    }
}
