package be.unamur.info.b314.compiler.exception;

/**
 *
 * Created by Simon on 16/02/17.
 */
public class GlobalScopeHasNoParentException extends RuntimeException
{
    public GlobalScopeHasNoParentException (String s)
    {
        super(s);
    }
}
