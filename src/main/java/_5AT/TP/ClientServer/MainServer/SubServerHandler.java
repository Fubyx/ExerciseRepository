package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SubServerHandler implements Runnable{
    Socket subServer;
    SubServerHandler(Socket subServer) {
        this.subServer = subServer;
    }
    @Override
    public void run() {
        Scanner s;
        PrintWriter w;
        try {
            w = new PrintWriter(subServer.getOutputStream());
            s = new Scanner(subServer.getInputStream());
            String newCommand = s.nextLine();
            if (newCommand.equals("disconnect")) {

            }
            Server.subServerCommands.put(newCommand, subServer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
