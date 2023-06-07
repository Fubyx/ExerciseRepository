package Sockets.Examples;

import Lernen_Noel.TestInt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
    public String testString;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(50000);
        Socket client = serverSocket.accept();

        while (true) {
            Scanner in = new Scanner(client.getInputStream());
            String text = in.nextLine();
            System.out.println(text);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("twfdtwfdujwfdzwd" + text);
        }

    }
}
