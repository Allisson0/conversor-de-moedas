package br.com.Conversor.Exception;

public class APITextNotFoundException extends RuntimeException{
    private String message;

    public APITextNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
