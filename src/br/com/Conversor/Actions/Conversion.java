package br.com.Conversor.Actions;

import br.com.Conversor.Models.ExchangeRate;
import br.com.Conversor.Models.SupportedExchanges;
import br.com.Conversor.Records.ExchangeRateAPI;
import br.com.Conversor.Records.SupportedConversionRateAPI;
import br.com.Conversor.Records.SupportedExchangesDataAPI;
import com.google.gson.*;

import java.util.Scanner;

public class Conversion {

    //Cria a requisição da API
    private final APIRequisition requisition = new APIRequisition();

    public APIRequisition getRequisition() {
        return requisition;
    }

    //Converte um par de moedas - x para y
    public void conversionPair(String from, String to, double value){

        Scanner input = new Scanner(System.in);

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

        System.out.println("Deseja converter outro valor? Se sim, digite 'sim', se não, aperte enter:");
        String choose = input.nextLine();

        if (choose.equalsIgnoreCase("sim")) {
            System.out.println("Digite um valor para converter: ");
            value = NumberConvertion.toDouble(input.nextLine());
            conversionPair(from, to, value);
        }

    }

    //Mostra a conversão direta em várias moedas diferentes de uma escolhida
    public void conversionAll(String from){

        String response = getRequisition().exchangeAll(from);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        SupportedConversionRateAPI convertingJson = gson.fromJson(response, SupportedConversionRateAPI.class);
        SupportedExchanges dataExchanges = new SupportedExchanges(convertingJson);

        //Chama a conversão de moeda para todas as moedas disponíveis da API
        dataExchanges.showConvertionRatesFromOne(from);

    }

    public void showConversion(){

        String response = getRequisition().showExchanges();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        SupportedExchangesDataAPI convertingJson = gson.fromJson(response, SupportedExchangesDataAPI.class);
        SupportedExchanges dataExchanges = new SupportedExchanges(convertingJson);

        //Chama a conversão de moeda para todas as moedas disponíveis da API
        dataExchanges.showExchangesRates();

    }
}