package be.unamur.info.b314.compiler.exception;

/**
 * Created by Simon on 11/02/17.
 */
public class ParsingException extends Exception
{
    public ParsingException(String message, Exception e) {
        super(message, e);
    }
    public ParsingException(String message){
        super(message);
    }
}
