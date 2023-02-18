package se.lexicon.MuhaIsa.BookLender.Exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
