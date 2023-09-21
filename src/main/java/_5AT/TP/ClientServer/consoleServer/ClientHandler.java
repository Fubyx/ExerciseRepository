package _5AT.TP.ClientServer.consoleServer;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket client;
    private ServerSocket server;
    private final static String password = "hello there";

    public ClientHandler(Socket client, ServerSocket server) {
        this.client = client;
        this.server = server;
    }


    @Override
    public void run() {
        Scanner in;
        PrintWriter out;
        try {
            in = new Scanner(client.getInputStream());
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean notFinished = true;
        String message;
        out.println("Enter Password (or disconnect):");
        while (true) {
            message = in.nextLine();
            if (message.equals(password)) {
                out.println("login succeeded. Enter \"help\" to see the commands");
                break;
            } else if (message.equals("disconnect")) {
                out.println("disconnected");
                return;
            } else {
                out.println("Wrong password");
            }
        }
        while (notFinished) {
            message = in.nextLine();
            switch (message) {
                case "help" -> {
                    out.println("Possible commands: stopServer, disconnect, isPrime");
                    out.flush();
                }
                case "stopServer" -> {
                    out.println("disconnected");
                    out.flush();
                    Server.serverRuns = false;
                    notFinished = false;
                    try {
                        server.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "disconnect" -> {
                    out.println("disconnected");
                    out.flush();
                    notFinished = false;
                }
                case "isPrime" -> {
                    out.println("enter a number");
                    out.flush();
                    message = (String) in.nextLine();
                    boolean val;
                    try {
                        val = isPrime(Long.parseLong(message));
                    } catch (NumberFormatException e) {
                        val = false;
                    }
                    out.println(val);
                    out.flush();
                }
                default -> {
                    out.println("Not a known Command");
                    out.flush();
                }
            }
        }
    }

    static public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num % 2 == 0) {
            return true;
        }
        for (int i = 3; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
