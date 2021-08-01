package cl.uchile.dcc.scrabble.AST.ternary;

import cl.uchile.dcc.scrabble.AST.ITree;

/**
 * Represents a node with three children.
 */
public abstract class AbstractTerOp implements ITree {

    ITree lc;
    ITree cc;
    ITree rc;

    public AbstractTerOp(ITree lc, ITree cc, ITree rc){
        this.lc = lc;
        this.cc = cc;
        this.rc = rc;
    }

    public ITree getLc(){
        return this.lc;
    }

    public ITree getRc() {
        return rc;
    }

    public ITree getCc() {
        return cc;
    }

}
