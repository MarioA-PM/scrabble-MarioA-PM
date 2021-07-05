package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.number.TFloat;
import java.util.Hashtable;

/**
 * Implements the TFloat factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightTFloatFactory {

    private static FlyweightTFloatFactory instance;
    private final Hashtable<Double, TFloat> hashtable = new Hashtable<>();

    private FlyweightTFloatFactory(){ }

    public static FlyweightTFloatFactory getInstance(){
        if (instance == null){
            instance = new FlyweightTFloatFactory();
        }
        return instance;
    }

    /**
     * Returns the required TFloat from the hashtable if the object has already been created,
     * if not, creates a new object.
     */
    public TFloat getTFloat(double n){
        TFloat v = hashtable.get(n);
        if (v != null){
            return v;
        }
        else{
            v = new TFloat(n);
            hashtable.put(n, v);
        }
        return v;
    }
}
