package br.com.Conversor.Main;

import br.com.Conversor.Actions.Conversion;
import br.com.Conversor.Actions.NumberConvertion;
import br.com.Conversor.Exception.APIKeyNotFoundException;
import br.com.Conversor.Exception.APITextNotFoundException;
import br.com.Conversor.Exception.InactiveAccountException;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        String menu = """
                \n**************** Conversor De Moedas ****************
              
                1. Exibir lista de moedas disponíveis para conversão;
                2. Converter moeda em outra;
                3. Exibir conversões de moeda;
                4. Sair da aplicação.
                
                Escolha uma das opções acima:""";
        String from = "USD";
        String to = "BRL";
        double value = 0.0;
        String choose;
        int chooseInt = 0;
        Scanner input = new Scanner(System.in);
        Conversion conversao = new Conversion();

        while(true) {
            System.out.println(menu);

            choose = input.nextLine();
            chooseInt = NumberConvertion.toInteger(choose);

            if(chooseInt == 4){
                System.out.println("Saindo do programa...");
                break;
            }

            try {
                switch (chooseInt){
                    case 1:
                        conversao.showConversion();
                        break;

                    case 2:
                        System.out.println("Informe a moeda que deseja converter: ");
                        from = input.nextLine();
                        System.out.println("Informe a moeda de destino: ");
                        to = input.nextLine();
                        System.out.println("Informe o valor que será convertido: ");
                        value = NumberConvertion.toDouble(input.nextLine());
                        conversao.conversionPair(from, to, value);
                        break;

                    case 3:
                        System.out.println("Insira uma moeda para ver as suas conversões: ");
                        from = input.nextLine();
                        conversao.conversionAll(from);
                        break;

                    default:
                        System.out.println("Por favor, insira uma opção válida.");
                }

            //Erro de API
            } catch (APIKeyNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Acesse o arquivo 'apiKey.txt' para alterar a chave API.");
                break;

            //Erro ao encontrar o apiKey.txt
            } catch (APITextNotFoundException e){
                System.out.println(e.getMessage());
                System.out.println("Acesse o arquivo 'apiKet.txt' para inserir a chave API.");
                break;

            //Erro no tipo de moeda buscado
            } catch (InactiveAccountException e) {
                System.out.println(e.getMessage());
            }

            //Erro desconhecido
            catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
                break;
            }
        }
        System.out.println("Programa finalizado com sucesso!");
    }
}