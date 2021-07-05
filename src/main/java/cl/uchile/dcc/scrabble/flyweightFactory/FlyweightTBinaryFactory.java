package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.number.TBinary;
import java.util.Hashtable;

/**
 * Implements the TBinary factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightTBinaryFactory {

    private static FlyweightTBinaryFactory instance;
    private final Hashtable<String, TBinary> hashtable = new Hashtable<>();

    private FlyweightTBinaryFactory(){ }

    public static FlyweightTBinaryFactory getInstance(){
        if (instance == null){
            instance = new FlyweightTBinaryFactory();
        }
        return instance;
    }

    /**
     * Returns the required TBinary from the hashtable if the object has already been created,
     * if not, creates a new object.
     */
    public TBinary getTBinary(String s){
        TBinary v = hashtable.get(s);
        if (v != null){
            return v;
        }
        else{
            v = new TBinary(s);
            hashtable.put(s, v);
        }
        return v;
    }
}
