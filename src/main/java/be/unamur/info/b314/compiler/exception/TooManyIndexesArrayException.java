package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 5/03/17.
 */
public class TooManyIndexesArrayException extends RuntimeException
{
    public TooManyIndexesArrayException (String name)
    {
        super(name);
    }
}
