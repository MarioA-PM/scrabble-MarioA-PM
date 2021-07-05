package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.TString;
import java.util.Hashtable;

/**
 * Implements the TString factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightTStringFactory {

    private static FlyweightTStringFactory instance;
    private final Hashtable<String, TString> hashtable = new Hashtable<>();

    private FlyweightTStringFactory(){ }

    public static FlyweightTStringFactory getInstance(){
        if (instance == null){
            instance = new FlyweightTStringFactory();
        }
        return instance;
    }

    /**
     * Returns the required TString from the hashtable if the object has already been created,
     * if not, creates a new object.
     */
    public TString getTString(String s){
        TString v = hashtable.get(s);
        if (v != null){
            return v;
        }
        else{
            v = new TString(s);
            hashtable.put(s, v);
        }
        return v;
    }
}
