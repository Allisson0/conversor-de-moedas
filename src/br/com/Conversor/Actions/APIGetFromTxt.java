package br.com.Conversor.Actions;

import br.com.Conversor.Exception.APIKeyNotFoundException;
import br.com.Conversor.Exception.APITextNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class APIGetFromTxt {
    //Define o arquivo apiKey.txt como um objeto File para leitura
    private final File txtAPI = new File("apiKey.txt");

    public File getTxtAPI() {
        return txtAPI;
    }

    public String getAPIKey(){
        try {
            //Escaneia o arquivo TxtApi
            Scanner scanFile = new Scanner(getTxtAPI());

            //Verifica se há algo dentro do txt, e se há, retorna
            if (scanFile.hasNextLine()) {
                return scanFile.nextLine();
            }
            //Se não, informa que não existe chave API no .txt
            else{
                throw new APIKeyNotFoundException("Chave API não encontrada no arquivo. \n" +
                        "Você pode encontrá-la em: https://www.exchangerate-api.com");
            }

        //Caso o arquivo não seja encontrado, o sistema irá criar
        } catch (FileNotFoundException e){
            System.out.println("Arquivo apiKey.txt não encontrado.");
            createTxt();
            throw new APITextNotFoundException("Arquivo da chave api criado.\n" +
                    "Você pode encontrá-la em: https://www.exchangerate-api.com.");
        }
    }

    //Criador do apiKey.txt
    private void createTxt(){
        try {
            FileWriter createApiTxt = new FileWriter("apiKey.txt");
            createApiTxt.write("YourAPIKeyHere");
            createApiTxt.close();
        } catch (IOException e){
            throw new RuntimeException("Erro desconhecido na criação de arquivo apiKey.txt");
        }
    }
}
