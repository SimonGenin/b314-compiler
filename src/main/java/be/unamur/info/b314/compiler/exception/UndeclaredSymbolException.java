package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 5/03/17.
 */
public class UndeclaredSymbolException extends RuntimeException
{

    public UndeclaredSymbolException ()
    {
        super();
    }

    public UndeclaredSymbolException (String message)
    {
        super(message);
    }
}
