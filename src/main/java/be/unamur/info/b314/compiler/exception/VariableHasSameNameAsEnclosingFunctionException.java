package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 19/02/17.
 */
public class VariableHasSameNameAsEnclosingFunctionException extends RuntimeException
{
    public VariableHasSameNameAsEnclosingFunctionException (String s)
    {
        super(s);
    }
}
