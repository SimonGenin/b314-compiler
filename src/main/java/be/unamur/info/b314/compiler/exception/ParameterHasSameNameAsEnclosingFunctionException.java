package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 19/02/17.
 */
public class ParameterHasSameNameAsEnclosingFunctionException extends RuntimeException
{
    public ParameterHasSameNameAsEnclosingFunctionException (String s)
    {   super(s);
    }
}
