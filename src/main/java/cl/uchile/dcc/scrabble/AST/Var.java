package cl.uchile.dcc.scrabble.AST;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightVarFactory;
import cl.uchile.dcc.scrabble.types.IType;

/**
 * Node that implements the use of variables.
 */
public class Var implements ITree {

    private ITree t;
    private final FlyweightVarFactory varFac = FlyweightVarFactory.getInstance();
    private final String id;

    /**
     *
     * @param id Name of the variable.
     * @param t AST assigned to the variable.
     */
    public Var(String id, ITree t){
        this.id = id;
        this.t = t;
    }

    /**
     * Declares a variable.
     * @param id Name of the variable.
     */
    public Var(String id){
        this.id = id;
    }

    public ITree getChild(){
        return this.t;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public IType eval() {
        if (getChild() != null){
            varFac.assign(id, t.eval());
        }
        return varFac.getValue(this.getId());
    }
}
