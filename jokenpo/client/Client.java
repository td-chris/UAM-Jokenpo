package jokenpo.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    final String IP = "127.0.0.1";
    final int PORT = 12345;
    Socket socket;
    PrintStream output = null;
    Scanner teclado = null;
    String nome;
    int jogo = 0;
    boolean accept = false;

    // criação do socket e pedido de conexão
    try {
      socket = new Socket(IP, PORT);
    } catch (Exception e) {
      System.out.println("Não foi possível se conectar ao servidor!");
      return;
    }

    // fase de comunicação
    try{
      output = new PrintStream(socket.getOutputStream());
      teclado = new Scanner(System.in);

      System.out.println("*********************************");
      System.out.println("********** Jokenpo UAM **********");
      System.out.println("*********************************");

      System.out.println("Digite o seu nome:");
      nome = teclado.nextLine();
      System.out.println("Seja bem vindo(a) "+ nome);
      
      do{
        System.out.println("Você deseja jogar contra CPU ou outro Jogador?");
        System.out.println("digite 1 - vs CPU | digite 2 - vs Jogador");
        jogo = Integer.parseInt(teclado.nextLine());

        if(jogo == 1){
          System.out.println("Você jogará contra a CPU! Boa Sorte!!");
          accept = true;
        }if(jogo == 2){
          System.out.println("Você jogará contra a Outro jogador! Boa Sorte!!");
          accept = true;
        }
      }while (!accept);


      String msg;

      do {
        System.out.println("Digite uma mensagem: ");
        msg = teclado.nextLine();
      } while (!msg.equalsIgnoreCase("exit"));

    }catch (Exception e){
      System.out.println(e.getMessage());
    }

    // fase de encerramento da conexão
    try{
      output.close();
      socket.close();
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
