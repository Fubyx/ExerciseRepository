package _5AT.TP.ClientServer.MainServer;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket client;
    private final ServerSocket server;
    private String udpUserName = "";
    private String privileges = "";

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
        out.println("connected to server");
        while (notFinished) {
            message = in.nextLine().split(" ", 2);
            switch (message[0]) {
                case "/help" -> {
                    String result = "Possible commands: " + "/stopServer, " +
                            "/quit, /isPrime, /auth, /authhelp (if you're authenticated)";
                    if (!privileges.equals("")) {
                        result = result + ", /enableUDP";
                        if (privileges.equals("admin")) {
                            result = result + ", /addUser";
                        }
                    }
                    if (!udpUserName.equals("")) {
                        result = result + ", /userlist";
                    }
                    StringBuilder s = new StringBuilder();
                    for (Map.Entry<String, Socket> entry : Server.subServerCommands.entrySet()) {
                        s.append(entry.getKey()).append(", ");
                    }
                    result = result + s;
                    out.println(result);
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
                    in.close();
                    out.close();
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/auth" -> {
                    if (!privileges.equals("")) {
                        out.println("You are already signed in. Do you want to change user? (Y/N");
                        message = in.nextLine().split(" ");
                        if (message[0].equals("N")) {
                            break;
                        }
                        Server.udpUsers.removeUser(udpUserName);
                    }
                    out.println("Enter Username");
                    message = in.nextLine().split(" ");
                    String userName = message[0];
                    do {
                        out.println("Enter Password:");
                        message = in.nextLine().split(" ");
                        String password = message[0];
                        privileges = Server.users.checkInformation(userName, password);
                    } while (privileges.equals(""));
                }
                case "/enableUDP" -> {
                    out.println("Enter UDP Username:");
                    message = in.nextLine().split(" ");
                    String udpUserName = message[0];
                    out.println("Enter IP-Address:");
                    message = in.nextLine().split(" ");
                    String ipAddress = message[0];
                    out.println("Enter Port:");
                    message = in.nextLine().split(" ");
                    int port = Integer.parseInt(message[0]);
                    if (Server.udpUsers.addUser(udpUserName, ipAddress, port)) {
                        out.println("You are registered for UDP");
                        this.udpUserName = udpUserName;
                    } else {
                        out.println("The Username or IP/Port combination you entered is invalid.");
                    }
                }
                case "/addUser" -> {
                    if (!privileges.equals("admin")) {
                        out.println("You do not have sufficient rights for this action.");
                        break;
                    }
                    out.println("Enter the userName:");
                    String newUsername = in.nextLine();
                    /* Immediately check if the username is already in use.
                    while (Server.users.exists(newUsername)) {
                        out.println("This username is already in use. Enter a different name:");
                        newUsername = in.nextLine();
                    }//*/
                    out.println("Enter the password:");
                    String newPassword = in.nextLine();
                    out.println("Enter the privilege level: (user/user+/admin)");
                    String newPrivileges = in.nextLine();
                    if (Server.users.addUser(newUsername, newPassword, newPrivileges)) {
                        out.println("User created");
                    } else {
                        out.println("The information you entered does not result in a valid user. Enter /addUser to try again.");
                    }
                }
                case "/userlist" -> {
                    if (!privileges.equals("") && !udpUserName.equals("")) {
                        out.println(Server.udpUsers.getUserList(udpUserName));
                    } else {
                        out.println("You are not a registered UDP-User.");
                    }
                }
                default -> {
                    if (!privileges.equals("")) {
                        Socket subServer = Server.subServerCommands.get(message[0]);
                        if (subServer == null) {
                            out.println("Not a known Command");
                            break;
                        }
                        if (!subServer.isConnected()) {
                            out.println("Command no longer exists. (server that offers that service disconnected)");
                            Server.subServerCommands.remove(message[0]);
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


                    } else {
                        out.println("Not a known Command");
                    }
                }
            }
        }
    }
}
