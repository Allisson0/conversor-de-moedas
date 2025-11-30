package br.com.Conversor.Exception;

public class APIKeyNotFoundException extends RuntimeException{

    private String message;
    public APIKeyNotFoundException(String mensagem) {
        this.message = mensagem;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
