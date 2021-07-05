package cl.uchile.dcc.scrabble.AST.binary;

import cl.uchile.dcc.scrabble.AST.ITree;

/**
 * Abstract class that represents a node with two children.
 */
public abstract class AbstractBinOp implements ITree {

    private final ITree lc;
    private final ITree rc;

    public AbstractBinOp(ITree lc, ITree rc){
        this.lc = lc;
        this.rc = rc;
    }

    public ITree getLeftChild(){
        return this.lc;
    }

    public ITree getRightChild(){
        return this.rc;
    }
}
