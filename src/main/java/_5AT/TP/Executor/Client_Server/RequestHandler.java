package _5AT.TP.Executor.Client_Server;

import _5AT.TP.Executor.PrimeChecker.PrimeCheckerThreadController;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class RequestHandler implements Runnable {
    private final Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        String userName = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            out.writeObject("Username:");
            out.flush();
            userName = (String) (in.readObject());
            int i;
            for (i = 0; i <= Server.users.size(); ++i) {
                if (userName.equals(Server.users.get(i).getUserName())) {
                    break;
                }
            }
            out.writeObject("Password:");
            out.flush();
            String password = (String) (in.readObject());
            if (!password.equals(Server.users.get(i).getPassword())) {
                throw new IOException();
            }

            out.writeObject("Type 'help' for a list of available commands.");
            out.flush();
        } catch (IOException | ClassNotFoundException | IndexOutOfBoundsException e) {
            try {
                out.writeObject("Your user information is incorrect, the server closes the connection.");
                connection.close();
            } catch (IOException | NullPointerException ignored) {
            } finally {
                Platform.exit();
            }
        }

        boolean skipEOF = false;
        while (!connection.isClosed()) {
            String request;
            String args = "";
            try {
                out.writeObject("Enter your next request:");
                out.flush();
                String requestString = (String) (in.readObject());
                String[] text = requestString.split(" ", 2);
                request = text[0];
                try {
                    args = text[1];
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
            System.out.println(request);
            switch (request) {
                case "close" -> {
                    Server.active = false;
                    try {
                        out.writeObject("Server is shutting down.");
                        out.flush();
                        connection.close();
                        Server.serverSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "help" -> {
                    try {
                        out.writeObject(
                                "isPrime [number(s)]: Returns a boolean array for each number stating whether it is a prime number or not. (Ex. isPrime 44 34 --> [false, false])\n" +
                                        "help: lists information about available commands\n" +
                                        "close: Closes the Server\n"
                        );
                        out.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "isPrime" -> {
                    String[] numberStrings = args.split(" ");
                    long[] numbers = new long[numberStrings.length];
                    for (int i = 0; i < numberStrings.length; ++i) {
                        numbers[i] = Long.parseLong(numberStrings[i]);
                    }
                    PrimeCheckerThreadController controller = new PrimeCheckerThreadController(numbers);
                    try {
                        boolean[] result = controller.checkNumbers();
                        try {
                            out.writeObject(Arrays.toString(result));
                            out.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "addUser" -> {
                    try {
                        if (!Server.users.get(Server.users.indexOf(new Userinformation(userName, "", ""))).getPrivileges().equals("admin")) {
                            out.writeObject("You do not have the privileges for this action.");
                            out.flush();
                            skipEOF = true;
                            break;
                        }
                        out.writeObject("Enter the user name");
                        out.flush();
                        String newUserName = (String) in.readObject();
                        out.writeObject("Enter the password");
                        out.flush();
                        String password = (String) in.readObject();
                        out.writeObject("Enter the privileges (admin/user)");
                        out.flush();
                        String privileges = (String) in.readObject();
                        Userinformation newUser = new Userinformation(newUserName, password, privileges);
                        Server.users.add(newUser);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> {
                    try {
                        out.writeObject("The server does not know this command, refer to 'help' for a list of commands.");
                        //System.out.println(Arrays.toString(result));
                        out.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!skipEOF) {
                try {
                    out.writeObject("EOF");
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                skipEOF = false;
            }
        }
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
