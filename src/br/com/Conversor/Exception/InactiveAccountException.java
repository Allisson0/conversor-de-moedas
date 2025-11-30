package br.com.Conversor.Exception;

public class InactiveAccountException extends RuntimeException{

    private String message;
    public InactiveAccountException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
