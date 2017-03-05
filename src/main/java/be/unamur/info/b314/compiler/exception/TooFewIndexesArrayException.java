package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 5/03/17.
 */
public class TooFewIndexesArrayException extends RuntimeException
{
    public TooFewIndexesArrayException (String name)
    {
        super(name);
    }
}
