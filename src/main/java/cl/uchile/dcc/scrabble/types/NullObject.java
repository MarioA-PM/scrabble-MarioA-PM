package cl.uchile.dcc.scrabble.types;

/**
 * Represents a null object from Scrabble (following the null object
 * pattern and singleton pattern).
 */
public class NullObject extends AbstractType{

    private static NullObject uniqueInstance;

    private NullObject(){ }

    public static NullObject getUniqueInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new NullObject();
        }
        return uniqueInstance;
    }

    @Override
    public String toString(){
        return "null Scrabble object";
    }

    @Override
    public IType toTString(){
        return this;
    }
}
