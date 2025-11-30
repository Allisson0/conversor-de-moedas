package br.com.Conversor.Models;

import br.com.Conversor.Records.ExchangeRateAPI;

public class ExchangeRate {
    private double conversionRate;
    private String from;
    private String to;

    public ExchangeRate(ExchangeRateAPI fromAPI){
        this.conversionRate = fromAPI.conversion_rate();
        this.from = fromAPI.base_code();
        this.to = fromAPI.target_code();
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    //Realiza a conversão dos valores
    public double convertingValues(double value){
        return value * this.getConversionRate();
    }

    @Override
    public String toString(){
        return "A conversão da moeda " + this.getFrom() + " para a " + this.getTo() + " é de " + this.getConversionRate();
    }
}
