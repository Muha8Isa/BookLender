package se.lexicon.MuhaIsa.BookLender.Exceptions;

public class DataDuplicateException extends RuntimeException{
    public DataDuplicateException(String message){
        super(message);
    }
}
