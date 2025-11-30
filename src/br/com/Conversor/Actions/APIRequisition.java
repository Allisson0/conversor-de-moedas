package br.com.Conversor.Actions;

import br.com.Conversor.Exception.APIKeyNotFoundException;
import br.com.Conversor.Exception.InactiveAccountException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequisition {

    //Cria uma atribuição de um objeto da classe APIGetFromTxt que pega a chave API de um .txt
    APIGetFromTxt fileAPIKey = new APIGetFromTxt();

    //Cria um acesso de comunicação do cliente para uma API
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpClient getClient(){
        return this.client;
    }

    //Requisição de conversão entre duas moedas diferentes
    public String exchangePair(String fromTarget, String toTarget){

        //Busca a chave API do apiKey.txt
        String apiKey = fileAPIKey.getAPIKey();

        //Cria o endereço de procura de conversão de uma moeda para outra
        String address = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+fromTarget+"/"+toTarget;

        //Realiza a requisição com base no endereço
        return requisition(address);

    }

    //Requisição de conversão de uma para várias moedas
    public String exchangeAll(String fromTarget){

        //Pega a chave api
        String apiKey = fileAPIKey.getAPIKey();

        //Cria o endereço de procura:
        String address = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+fromTarget;

        //Realiza a requisição de todas as conversões da moeda
        return requisition(address);

    }

    //Mostra todas as moedas disponíveis para conversão
    public String showExchanges(){

        //Pega a chave api
        String apiKey = fileAPIKey.getAPIKey();

        //Cria o endereço de procura:
        String address = "https://v6.exchangerate-api.com/v6/"+apiKey+"/codes";

        //Realiza a requisição de amostragem de moedas disponíveis
        return requisition(address);

    }

     private String requisition(String address){
        //Realiza a requisição de todas as moedas suportadas
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .GET()
                    .build();

            //Cria uma resposta com base nessa requisição
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            //Verifica se deu erro e tenta encontrar o tipo de erro
            findError(response);

            //Se não houve erro na busca, retorna essa resposta
            return response.body();

            //Qualquer outro tipo de erro:
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //Se houve erro, verifica o tipo de erro e dá um retorno em throw new
    private void findError(HttpResponse<String> validacao){
        //Código para pegar um elemento chamado "result" e verificar se deu erro
        //Salva num objeto json chamado exchangeObject
        JsonElement element = JsonParser.parseString(validacao.body());
        JsonObject exchangeObject = element.getAsJsonObject();

        //Pega esse resultado e salva em uma String
        String verificationAPIkey = exchangeObject.get("result").getAsString();

        //verifica se há erro e verifica o tipo em error-type
        if (verificationAPIkey.equals("error")){
            String errorType = exchangeObject.get("error-type").getAsString();

            //Chave inválida
            if (errorType.equals("invalid-key")){
                throw new APIKeyNotFoundException("Chave API inválida. Por favor, verifique a chave inserida.");
            }
            //Moeda inválida
            else if(errorType.equals("inactive-account") || errorType.equals("unsupported-code")){
                throw new InactiveAccountException("Moeda de conversão inválida, por favor, tente novamente.");
            }
            else{
                throw new RuntimeException("Erro desconhecido na busca da API.");
            }
        }
    }
}