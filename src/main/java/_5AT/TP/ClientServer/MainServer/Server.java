package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static boolean serverRuns = true;
    public static HashMap<String, Socket> subServerCommands = new HashMap<>();
    public static ServerSocket subServerServerSocket;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            System.out.println("Trying to open Serversocket");
            serverSocket = new ServerSocket(30000);
            subServerServerSocket = new ServerSocket(40000);
            System.out.println("opened Serversocket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        Thread subServerHandler = new Thread(new Runnable() {
            @Override
            public void run() {
                ExecutorService subServerExecutor = Executors.newCachedThreadPool();

                while (serverRuns) {
                    Socket subServer;
                    try {
                        subServer = subServerServerSocket.accept();
                    } catch (IOException e) {
                        break;
                    }
                    subServerExecutor.submit(new SubServerHandler(subServer));
                }
            }
        });
        subServerHandler.start();
        while (serverRuns) {
            Socket client;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("server Closed");
                break;
            }
            executor.execute(new ClientHandler(client, serverSocket));
        }
        executor.shutdown();
    }
}
