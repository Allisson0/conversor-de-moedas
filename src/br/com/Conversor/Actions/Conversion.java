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

    //Cria um build padrão do gson para conversão da API para objeto
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public APIRequisition getRequisition() {
        return requisition;
    }

    //Converte um par de moedas - x para y
    public void conversionPair(String from, String to, double value){

        Scanner input = new Scanner(System.in);

        //lê um json a partir da resposta da requisição
        String json = getRequisition().exchangePair(from, to);

        //converte esse json num objeto da classe ExchangeRate
        ExchangeRateAPI apiConvertion = gson.fromJson(json, ExchangeRateAPI.class);
        ExchangeRate exchangeRate = new ExchangeRate(apiConvertion);

        //retorna o valor de conversão das moedas
        System.out.println(exchangeRate);

        //Printa o valor convertido
        System.out.printf("O valor %.2f convertido para a moeda é de %.2f\n"
                , value, exchangeRate.convertingValues(value));

        //Realiza uma pergunta se o usuário quer converter outro valor da mesma conversão
        System.out.println("Deseja converter outro valor? \nPara sim, digite 'sim', para não, aperte enter:");
        String choose = input.nextLine();

        //Se sim, realiza a conversão
        if (choose.equalsIgnoreCase("sim")) {
            System.out.println("Digite um valor para converter: ");
            //Define que o valor para conversão é o inserido
            value = NumberConvertion.toDouble(input.nextLine());
            //Chama o metodo de conversão de pares novamente
            conversionPair(from, to, value);
        }
    }

    //Mostra a conversão direta em várias moedas diferentes de uma escolhida
    public void conversionAll(String from){

        //Pega a requisição da conversão de todas as moedas
        String response = getRequisition().exchangeAll(from);

        SupportedConversionRateAPI convertingJson = gson.fromJson(response, SupportedConversionRateAPI.class);
        SupportedExchanges dataExchanges = new SupportedExchanges(convertingJson);

        //Chama a conversão de moeda para todas as moedas disponíveis da API
        dataExchanges.showConvertionRatesFromOne(from);
    }

    //Mostra a lista de moedas disponíveis para conversão
    public void showConversion(){

        //Pega a requisição de todas as moedas
        String response = getRequisition().showExchanges();

        //conversão do valor da api para o valor do objeto suportado (dataExchanges)
        SupportedExchangesDataAPI convertingJson = gson.fromJson(response, SupportedExchangesDataAPI.class);
        SupportedExchanges dataExchanges = new SupportedExchanges(convertingJson);

        //Mostra as moedas possíveis em forma de lista
        dataExchanges.showExchangesRates();
    }
}