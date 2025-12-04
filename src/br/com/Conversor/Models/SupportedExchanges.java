package br.com.Conversor.Models;

import br.com.Conversor.Actions.NumberConvertion;
import br.com.Conversor.Records.SupportedConversionRateAPI;
import br.com.Conversor.Records.SupportedExchangesDataAPI;

import java.util.*;

public class SupportedExchanges {
    private LinkedHashMap<String, String> codes;
    private final static int lengthPage = 20;
    private Scanner input = new Scanner(System.in);

    public LinkedHashMap<String, String> getCodes(){
        return this.codes;
    }

    public SupportedExchanges(SupportedConversionRateAPI exchangeApi){
        this.codes = exchangeApi.conversion_rates();
    }

    public SupportedExchanges(SupportedExchangesDataAPI exchangesDataAPI){
        this.codes = exchangesDataAPI.supported_codes();
    }

    //Mostra as conversões disponíveis da moeda
    public void showConvertionRatesFromOne(String from){
        //Cria uma lista baseada no LinkedHashMap criado a partir do item recebido da API
        List<Map.Entry<String, String>> list = new ArrayList<>(getCodes().entrySet());

        //Recupera a quantidade de itens da lista
        int amountItems = list.size();
        //Realiza uma conta matemática para descobrir a quantidade de página pelo conteúdo total dividido pela
        //quantidade de itens por páginas
        int amountPages = (int) Math.ceil((double) amountItems / lengthPage);

        //Cria um menu para visualização do menu interativo de páginas
        interactiveMenu(list, amountItems, amountPages, from);

    }

    //Mostra a lista de moedas disponíveis
    public void showExchangesRates(){
        List<Map.Entry<String, String>> list = new ArrayList<>(getCodes().entrySet());

        //Recupera a quantidade de itens da lista
        int amountItems = list.size();
        //Realiza uma conta matemática para descobrir a quantidade de página pelo conteúdo total dividido pela
        //quantidade de itens por páginas
        int amountPages = (int) Math.ceil((double) amountItems / lengthPage);

        //Cria um menu para visualização do menu interativo de páginas
        interactiveMenu(list, amountItems, amountPages, "");
    }

    private void interactiveMenu(List<Map.Entry<String, String>> list, int amountItems, int amountPages, String from){
        //Define a página inicial como 1
        int actualPage = 1;
        //Define um layout de impressão de duas colunas
        String layout = from.isEmpty() ? "%-50s %s%n" : "%-20s %s%n";
        //Distância do espaçamento da amostragem da página
        String space = from.isEmpty() ? "\t\t\t\t" : "";
        while(true){
            System.out.println("Foram encontradas " + list.size() + " moedas disponíveis para conversão.");
            System.out.println(from.isEmpty() ?
                    "Conversões gerais da moeda: " + from :
            "\nLista de moedas disponíveis para conversão: ");
            System.out.println("\n" + space + "========== Página " +actualPage + " de " + amountPages + " ==========");

            //Define o começo da listagem como a página atual - 1 vezes a quantidade de itens por página
            int start = (actualPage - 1) * lengthPage;
            //Define um limite mínimo para o tamanho da listagem da página atual
            //Ou seja, se o limite for 10 (lengthPage + start (inicio da listagem)) e queremos um máximo
            //de 10 itens (amountItems) e temos apenas 9, o programa não dará erro. Pois Math.min() pede
            //o valor mínimo dessa contagem.
            int end = Math.min(start + lengthPage, amountItems);

            //Para cada i entre start e end, mapeia uma entrada de caracteres e imprimi ele
            //Assim, lista conforme a página atual
            for (int i = start; i < end; i+=2){
                //Salva a entrada da primeira coluna
                Map.Entry<String, String> entry = list.get(i);
                //Cria a primeira coluna
                String firstColumn = entry.getKey() + " : " + entry.getValue();

                //Cria a string que armazena a segunda coluna
                String secondColumn = "";
                //Se o i + 1 não ultrapassar o tamanho da lista
                if (i + 1 < end) {
                    //Salva a entrada da segunda coluna
                    Map.Entry<String, String> secondEntry = list.get(i + 1);
                    //Atribui ao String da segunda coluna, os argumentos dela
                    secondColumn = secondEntry.getKey() + " : " + secondEntry.getValue();
                }

                //Imprime as colunas
                System.out.printf(layout, firstColumn, secondColumn);
            }

            //Paginação
            System.out.println(space + "========== Página " +actualPage + " de " + amountPages + " ==========");

            //Menu de escolha de página
            System.out.println("Escolha uma página ou digite 0 para sair: ");

            //Escolha da página
            int choose = NumberConvertion.toInteger(input.nextLine());

            //verifica a escolha da página
            if (choose == 0){
                System.out.println("Saindo...");
                break;
            } else if (choose <= amountPages && choose > 0){
                actualPage = choose;
            } else {
                System.out.println("Por favor, insira uma opção válida.");
            }
        }
    }
}