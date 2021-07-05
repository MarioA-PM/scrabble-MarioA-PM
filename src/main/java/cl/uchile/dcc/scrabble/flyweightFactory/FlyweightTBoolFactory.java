package cl.uchile.dcc.scrabble.flyweightFactory;

import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.number.TBinary;

import java.util.Hashtable;

/**
 * Implements the TBool factory using the flyweight pattern.
 * The factory is also a singleton (only one instance of the factory).
 */
public class FlyweightTBoolFactory {

    private static FlyweightTBoolFactory instance;
    private TBool t;
    private TBool f;

    private FlyweightTBoolFactory(){ }

    public static FlyweightTBoolFactory getInstance(){
        if (instance == null){
            instance = new FlyweightTBoolFactory();
        }
        return instance;
    }

    /**
     * Returns the required TBool.
     */
    public TBool getTBool(boolean n){
        if (n){
            if (t != null){
                return t;
            }
            else{
                t = new TBool(true);
            }
            return t;
        }
        else{
            if (f != null){
                return f;
            }
            else{
                f = new TBool(false);
            }
            return f;
        }
    }
}
