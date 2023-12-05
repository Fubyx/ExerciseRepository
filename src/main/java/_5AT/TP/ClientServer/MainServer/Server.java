package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static boolean serverRuns = true;
    public static HashMap<String, Socket> subServerCommands = new HashMap<>();
    public static ServerSocket subServerServerSocket;
    public static Users users = new Users();
    public static UDPUsers udpUsers = new UDPUsers();
    public static UDPGroups udpGroups = new UDPGroups();

    public static void main(String[] args) {
        users.addUser("admin", "admin", "admin");
        users.addUser("david", "david", "admin");
        users.addUser("fabian", "fabian", "admin");

        ServerSocket serverSocket;
        try {
            System.out.println("Trying to open ServerSocket");
            serverSocket = new ServerSocket(30000);
            subServerServerSocket = new ServerSocket(40000);
            System.out.println("opened ServerSocket");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        Thread subServerHandler = new Thread(() -> {
            ExecutorService subServerExecutor = Executors.newCachedThreadPool();

            while (serverRuns) {
                Socket subServer;
                try {
                    subServer = subServerServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                System.out.println("connected sub server");
                subServerExecutor.submit(new SubServerHandler(subServer));
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
            System.out.println("connected client");
            executor.execute(new ClientHandler(client, serverSocket));
        }
        executor.shutdown();
    }
}
