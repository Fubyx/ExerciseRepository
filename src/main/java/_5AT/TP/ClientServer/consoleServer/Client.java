package _5AT.TP.ClientServer.consoleServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 30000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner in;
        PrintWriter out;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = in.nextLine();
        while (!message.equals("disconnected")) {
            System.out.print(message + "\n> ");
            try {
                message = br.readLine();
                out.println(message);
                out.flush();
                message = in.nextLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
