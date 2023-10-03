package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SubServer {
    public static void main(String[] args) {
        SubServerTemplate template = new IsPrimeTemplate();
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 40000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner in;
        PrintWriter out;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] commands = template.getCommands();
        for (String s : commands) {
            out.println(s);
        }
        while (true) {
            String[] command = in.nextLine().split(" ", 2);
            out.println(template.doCommand(command));
        }


    }

}
