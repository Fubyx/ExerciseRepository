package _5AT.TP.ClientServer.consoleServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static boolean serverRuns = true;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            System.out.println("Trying to open Serversocket");
            serverSocket = new ServerSocket(30000);
            System.out.println("opened Serversocket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executor = Executors.newCachedThreadPool();

        while (serverRuns) {
            Socket client;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            executor.execute(new ClientHandler(client, serverSocket));
        }
        executor.shutdown();
    }
}
