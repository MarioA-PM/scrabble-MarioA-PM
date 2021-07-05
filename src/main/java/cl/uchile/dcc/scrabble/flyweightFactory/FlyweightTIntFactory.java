package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.number.TInt;
import java.util.Hashtable;

/**
 * Implements the TInt factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightTIntFactory {

    private static FlyweightTIntFactory instance;
    private final Hashtable<Integer, TInt> hashtable = new Hashtable<>();

    private FlyweightTIntFactory(){ }

    public static FlyweightTIntFactory getInstance(){
        if (instance == null){
            instance = new FlyweightTIntFactory();
        }
        return instance;
    }

    /**
     * Returns the required TInt from the hashtable if the object has already been created,
     * if not, creates a new object.
     */
    public TInt getTInt(int n){
        TInt v = hashtable.get(n);
        if (v != null){
            return v;
        }
        else{
            v = new TInt(n);
            hashtable.put(n, v);
        }
        return v;
    }

}
