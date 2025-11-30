package br.com.Conversor.Actions;

import br.com.Conversor.Models.ExchangeRate;
import br.com.Conversor.Records.ExchangeRateAPI;
import com.google.gson.*;

public class Conversion {

    //Cria a requisição da API
    private APIRequisition requisition = new APIRequisition();

    public APIRequisition getRequisition() {
        return requisition;
    }

    public void conversionPair(String from, String to, double value){


        //lê um json a partir da resposta da requisição
        String json = getRequisition().exchangePair(from, to);

        //cria um novo gson para converter essa requisition
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        //converte esse json num objeto da classe ExchangeRate
        ExchangeRateAPI apiConvertion = gson.fromJson(json, ExchangeRateAPI.class);
        ExchangeRate exchangeRate = new ExchangeRate(apiConvertion);

        //retorna o valor de conversão das moedas
        System.out.println(exchangeRate);

        System.out.println("O valor " + value + " convertido para a moeda é de " + exchangeRate.convertingValues(value));

    }

    //Mostra a conversão direta em várias moedas diferentes de uma escolhida
    public void conversionAll(String from){

        System.out.println(getRequisition().exchangeAll(from));
    }

    public void showConversion(){
        System.out.println(getRequisition().showExchanges());
    }
}