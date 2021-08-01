package cl.uchile.dcc.scrabble.AST;

import cl.uchile.dcc.scrabble.flyweightFactory.FlyweightTBoolFactory;
import cl.uchile.dcc.scrabble.types.IType;

import java.util.List;

/**
 * Represents a node with an undefined number of children.
 */
public class Seq implements ITree{

    private final List<ITree> list;

    /**
     * @param list List of AST.
     */
    public Seq(List<ITree> list){
        this.list = list;
    }

    public List <ITree> getList(){
        return list;
    }

    /**
     * {@inheritDoc}
     * Evaluates the AST of the list in order.
     */
    @Override
    public IType eval() {
        for (ITree t: this.getList()){
            t.eval();
        }
        return FlyweightTBoolFactory.getInstance().getTBool(true);
    }
}
