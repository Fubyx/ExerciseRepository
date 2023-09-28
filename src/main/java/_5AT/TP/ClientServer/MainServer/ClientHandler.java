package _5AT.TP.ClientServer.MainServer;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket client;
    private ServerSocket server;
    private final static String password = "hello there";
    private boolean authenticated = false;

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
        String[] message;
//        out.println("Enter Password (or /quit):");
//        while (true) {
//            message = in.nextLine().split(" ");
//            if (message[0].equals(password)) {
//                out.println("connection succeeded. Enter \"/help\" to see the commands");
//                break;
//            } else if (message[0].equals("/quit")) {
//                out.println("disconnected");
//                return;
//            } else {
//                out.println("Wrong password");
//            }
//        }
        while (notFinished) {
            message = in.nextLine().split(" ", 1);
            switch (message[0]) {
                case "/help" -> {
                    out.println("Possible commands: /stopServer, /quit, /isPrime, /auth, /authhelp (if you're authenticated)");

                }
                case "/stopServer" -> {
                    out.println("disconnected");
                    Server.serverRuns = false;
                    notFinished = false;
                    try {
                        server.close();
                        Server.subServerServerSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/quit" -> {
                    out.println("disconnected");
                    notFinished = false;
                }
                case "/isPrime" -> {
                    if (message.length < 2) {
                        out.println("also enter a number (example: /isPrime 5)");
                        break;
                    }
                    boolean val;
                    try {
                        val = isPrime(Long.parseLong(message[1]));
                    } catch (NumberFormatException e) {
                        val = false;
                    }
                    out.println(val);
                }
                case "/auth" -> {
                    if (message.length != 2 && !message[1].equals(password)) {
                        out.println("wrong password");
                    } else {
                        authenticated = true;
                        out.println("authenticated");
                    }
                }
                default -> {
                    if (authenticated) {
                        if (message[0].equals("/userlist")) {
                            out.println("user: 0823ygb");
                        } else if (message[0].equals("/authhelp")) {
                        } else {
                            boolean found = false;
                            for (int i = 0; i < Server.subServerCommands.size(); i++) {
                                if (message[0].equals(Server.subServerCommands.get(i))) {
                                    found = true;

                                }
                            }
                            if (!found) {
                                out.println("Not a known Command");
                            }
                        }


                    } else {
                        out.println("Not a known Command");
                    }
                }
            }
        }
    }

    static public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
