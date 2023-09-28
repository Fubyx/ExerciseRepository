package _5AT.TP.ClientServer.MainServer;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
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
            out = new PrintWriter(client.getOutputStream(), true);

            in = new Scanner(client.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean notFinished = true;
        String[] message;
        out.println("connected to server");
        while (notFinished) {
            message = in.nextLine().split(" ", 2);
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

                case "/auth" -> {
                    if (message.length < 2 || !message[1].equals(password)) {
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
                            StringBuilder s = new StringBuilder();
                            for (Map.Entry<String, Socket> entry : Server.subServerCommands.entrySet()) {
                                s.append(entry.getKey()).append(", ");
                            }
                            out.println(s);
                        } else {
                            Socket subServer = Server.subServerCommands.get(message[0]);
                            if (subServer == null) {
                                out.println("Not a known Command");
                                break;
                            }
                            Scanner subr = null;
                            PrintWriter subw = null;
                            try {
                                subw = new PrintWriter(subServer.getOutputStream(), true);
                                subr = new Scanner(subServer.getInputStream());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (message.length == 1) {
                                subw.println(message[0]);
                            } else {
                                subw.println(message[0] + " " + message[1]);
                            }
                            out.println(subr.nextLine());
                        }
                    } else {
                        out.println("Not a known Command");
                    }
                }
            }
        }
    }


}
