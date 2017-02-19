package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 16/02/17.
 */
public class AlreadyUsedIdentifierException extends RuntimeException
{

    public AlreadyUsedIdentifierException(String message, Exception e) {
        super(message, e);
    }
    public AlreadyUsedIdentifierException(String message){
        super(message);
    }

}
