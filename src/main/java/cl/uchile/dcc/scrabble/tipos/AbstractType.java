package cl.uchile.dcc.scrabble.tipos;

import cl.uchile.dcc.scrabble.tipos.Operations.ISumString;
import cl.uchile.dcc.scrabble.tipos.transformations.ITransToString;

/**
 * Represents the general types of the project (strings, booleans and numbers).
 * This class does not provide any methods, but helps organizing the implementation
 * of different interfaces.
 */
public abstract class AbstractType implements ITransToString, ISumString {
}
