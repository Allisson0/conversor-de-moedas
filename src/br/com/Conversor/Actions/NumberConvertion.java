package br.com.Conversor.Actions;

import java.util.Scanner;

public class NumberConvertion {
    private static Scanner input = new Scanner(System.in);

    public static int toInteger(String value){
        try{
            return Integer.parseInt(value);
        } catch (NumberFormatException e){
            System.out.println("Valor inválido, por favor, insira um valor válido:");
            return toInteger(input.nextLine());
        }
    }

    public static double toDouble(String value){
        try{
            return Double.parseDouble(value);
        } catch (NumberFormatException e){
            System.out.println("Valor inválido, por favor, insira um valor válido:");
            return toInteger(input.nextLine());
        }
    }
}
