package jokenpo.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
  public static void main(String[] args) {
    final int PORT = 12345;
    ServerSocket serverSocket;
    Socket jogadorSocket = null;

    // Criar um socket e fazer o bind
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (Exception e) {
      System.out.println("Porta "+ PORT +" já está em uso!");
      return;
    }

    // aguardar pedido de conexao
    try {
      System.out.println("Aguardando pedido de conexão...");
      jogadorSocket = serverSocket.accept();
      System.out.println("Conectado com: "+ jogadorSocket.getInetAddress().getHostAddress());

    } catch (Exception e) {
      System.out.println("Erro na conexão!");
      System.out.println(e.getMessage());
    }

    // fase de encerrar conexão
    // try{
    //   serverSocket.close();
    // }catch (Exception e){
    //   System.out.println(e.getMessage());
    // }
  }
}