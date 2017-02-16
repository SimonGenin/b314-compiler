package be.unamur.info.b314.compiler.exception;

/**
 *
 * Created by Simon on 16/02/17.
 */
public class UnknownTypeException extends RuntimeException
{
    public UnknownTypeException(String message, Exception e) {
        super(message, e);
    }
    public UnknownTypeException(String message){
        super(message);
    }
}
