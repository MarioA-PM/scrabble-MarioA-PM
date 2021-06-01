package cl.uchile.dcc.scrabble.tipos.numeros;

import cl.uchile.dcc.scrabble.tipos.AbstractType;
import cl.uchile.dcc.scrabble.tipos.Operations.IBinOp;
import cl.uchile.dcc.scrabble.tipos.Operations.INegation;
import cl.uchile.dcc.scrabble.tipos.Operations.IOpNumber;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToFloat;

/**
 * Represents the general number type (contains integer, float and binary).
 * This class does not provide any methods, but helps organizing the implementation
 * of different interfaces.
 */
public abstract class AbstractNumber extends AbstractType
        implements INegation, ITransToFloat, IBinOp, IOpNumber {
}
