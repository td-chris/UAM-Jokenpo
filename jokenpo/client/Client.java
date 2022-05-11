package jokenpo.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        final String IP = "127.0.0.1";
        final int PORT = 1234;
        Socket socket;
        Scanner teclado = null;
        Scanner entrada = new Scanner(System.in);
        Scanner input = null;
        PrintStream output = null;
        char escolha;
        boolean accept = false;

        try {
            socket = new Socket(IP, PORT);
            System.out.println("***************************");
            System.out.println("* Bem vindo(a) ao Jokenpo *");
            System.out.println("***************************");
        } catch (Exception e) {
            System.out.println("Não foi possivel conectar ao servidor.");
            entrada.close();
            return;
        }

        // fase de escolha do modo de jogo
        try {
            output = new PrintStream(socket.getOutputStream());
            do {
              System.out.println("Faça sua ecolha:");
              System.out.println("(1) - para jogar contra CPU | (2) - para jogar online");
              escolha = entrada.nextLine().charAt(0);
              output.println(escolha);
              if(escolha == '1'){
                System.out.println("Você jogará contra a CPU! Boa sorte!\n");
                accept = true;
              }else if(escolha == '2'){
                System.out.println("Você jogará outro jogador! Boa sorte!\n");
                accept = true;
              }else{
                System.out.println("Comando não identificado!\n");
              }
              
            } while (!accept);
        } catch (Exception e) {
            System.out.println(e);
        }

        // fase de comunicação
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            teclado = new Scanner(System.in);
            // Escuta escuta = new Escuta(socket);
            // escuta.start();

            String msg;
            String resultado;
            String escolhaCPU;
            String placar;
            do {
                System.out.print("Digite: (0) - Pedra | (1) - Papel | (2) - Tesoura | (exit) - Sair \n");
                msg = teclado.nextLine();
                output.println(msg);
                escolhaCPU = input.nextLine();
                System.out.println(escolhaCPU);
                resultado = input.nextLine();
                System.out.println(resultado);
                placar = input.nextLine();
                System.out.println(placar);
            } while (!msg.equalsIgnoreCase("exit"));

            // escuta.parar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // fase de encerramento a conexão
        try {
            input.close();
            entrada.close();
            output.close();
            teclado.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
