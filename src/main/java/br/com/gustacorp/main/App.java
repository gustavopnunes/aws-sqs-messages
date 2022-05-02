package br.com.gustacorp.main;


import br.com.gustacorp.consumer.Consumer;
import br.com.gustacorp.producer.Producer;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a opcao desejada:");
        System.out.println("1 - Enviar mensagem");
        System.out.println("2 - Receber mensagem");

        int opcao = input.nextInt();
        switch (opcao){
            case 1:
                while (true)
                producer.sendMessage();

            case 2:
                while (true) {
                    consumer.readMessages();
                }
            default:
                System.out.println("Opcao invalida!");
        }
    }
}
