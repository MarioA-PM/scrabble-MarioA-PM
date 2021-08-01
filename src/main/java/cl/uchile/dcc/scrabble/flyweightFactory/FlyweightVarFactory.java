package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.IType;
import java.util.Hashtable;

/**
 * Implements the Var factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightVarFactory {

    private static FlyweightVarFactory instance;
    private final Hashtable<String, IType> hashtableVar = new Hashtable<>();

    private FlyweightVarFactory(){ }

    public static FlyweightVarFactory getInstance(){
        if (instance == null){
            instance = new FlyweightVarFactory();
        }
        return instance;
    }

    /**
     * Sets the value to the given variable in the hash table.
     * @param id Name of the variable
     * @param value Value assign to the variable
     */
    public void assign(String id, IType value){
        hashtableVar.put(id, value);
    }

    /**
     * Returns the value assign to the variable.
     * @param id Name of the variable
     */
    public IType getValue(String id){
        return hashtableVar.get(id);
    }
}
