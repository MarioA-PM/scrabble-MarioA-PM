package cl.uchile.dcc.scrabble.AST;

import cl.uchile.dcc.scrabble.AST.binary.*;
import cl.uchile.dcc.scrabble.AST.unary.*;
import cl.uchile.dcc.scrabble.types.IType;
import cl.uchile.dcc.scrabble.types.NullObject;
import cl.uchile.dcc.scrabble.types.TBool;
import cl.uchile.dcc.scrabble.types.TString;
import cl.uchile.dcc.scrabble.types.number.TBinary;
import cl.uchile.dcc.scrabble.types.number.TFloat;
import cl.uchile.dcc.scrabble.types.number.TInt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ASTTest {

    private final IType nullValue = NullObject.getUniqueInstance();

    @Test
    void exampleTreeTest(){
        assertEquals("null Scrabble object", nullValue.toString());

        Add tree1 =
          new Add(
            new Or(
              new TBinary("1000"),
              new ToBinary(
                new Sub(
                  new TInt(25),
                  new TBinary("0101")
                )
              )

            ),
            new TFloat(6.9)
          );
        assertEquals( nullValue ,tree1.eval());
        assertEquals( nullValue ,(new ToString(tree1)).eval());

        Add tree2 =
          new Add(
            new TFloat(6.9),
            new Or(
              new TBinary("1000"),
              new ToBinary(
                new Sub(
                  new TInt(25),
                  new TBinary("0101")
                )
              )
            )
          );
        assertEquals(new TFloat(34.9), tree2.eval());
        assertEquals( nullValue ,(new Div(tree1, tree2)).eval());

        TBool aBool = new TBool(true);
        TInt aInt = new TInt(7);
        TFloat aFloat = new TFloat(4.325);
        TBinary aBinary = new TBinary("1101");//-3

        ITree tree3 =
          new Negation(
            new Mult(
              new ToInt(
                aBinary
              ),
              new Div(
                aFloat,
                new Sub(
                  new And(
                    aBool,
                    aBinary
                  ),
                  aInt
                )
              )
            )
          );
        IType exp = (aBinary.toTInt().mult(aFloat.div((aBool.and(aBinary)).resta(aInt)))).neg();
        assertEquals(exp, tree3.eval());

        ITree tree4 =
          new Negation(
            new Mult(
              new ToInt(
                aBinary
              ),
              new Div(
                aFloat,
                new Sub(
                  new And(
                    aBool,
                    aBinary
                  ),
                  tree1
                )
              )
            )
          );
        assertEquals( nullValue ,tree4.eval());

        Add tree5 =
          new Add(
            new TFloat(6.9),
            new Or(
              new TBinary("1000"),
              new ToBinary(
                new Sub(
                  new TInt(25),
                  tree4
                )
              )
            )
          );
        assertEquals( nullValue ,tree5.eval());

        ITree tree6 =
          new Negation(
            new Mult(
              new ToInt(
                aBinary
              ),
              new Div(
                aFloat,
                new Sub(
                  new And(
                    aBool,
                    tree1
                  ),
                  tree1
                )
              )
            )
          );
        assertEquals( nullValue ,tree6.eval());

        ITree tree7 = new And(tree2, tree3);
        assertEquals( nullValue ,tree7.eval());
        assertEquals( nullValue ,(new ToFloat(tree1)).eval());
        assertEquals( nullValue ,(new ToInt(tree1)).eval());
        assertEquals( nullValue ,(new ToString(tree1)).eval());
        assertEquals( nullValue ,(new ToBool(tree1)).eval());
        assertEquals(new TString("34.9"), (new ToString(tree2)).eval());
    }

    @Test
    void invalidOperationsTreeTest(){
        TString aString = new TString("word");
        TBool aBool = new TBool(true);
        TInt aInt = new TInt(7);
        TFloat aFloat = new TFloat(4.325);
        TBinary aBinary = new TBinary("1101");//-3

        IType[] a = {aString, aBool, aInt, aFloat, aBinary};
        for (int i = 1; i < 5; i++){
            ITree t1 = new Add(a[i], aString);
            ITree t2 = new Sub(a[i], aString);
            ITree t3 = new Mult(a[i], aString);
            ITree t4 = new Div(a[i], aString);
            ITree t5 = new Or(a[i], aString);
            ITree t6 = new And(a[i], aString);
            assertEquals( nullValue ,t1.eval());
            assertEquals( nullValue ,t2.eval());
            assertEquals( nullValue ,t3.eval());
            assertEquals( nullValue ,t4.eval());
            assertEquals( nullValue ,t5.eval());
            assertEquals( nullValue ,t6.eval());
        }
        assertEquals( nullValue ,(new Negation(aString).eval()));
        assertEquals( nullValue ,(new ToBinary(aFloat)).eval());
        assertEquals( nullValue ,(new ToInt(aFloat)).eval());
        assertEquals( nullValue ,(new ToBool(aBinary)).eval());
        assertEquals( nullValue ,(new ToFloat(aString)).eval());

    }
}
