package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SubServerHandler implements Runnable {
    Socket subServer;

    SubServerHandler(Socket subServer) {
        this.subServer = subServer;
    }

    @Override
    public void run() {
        ArrayList<String> commands = new ArrayList<>();
        Scanner s;
        PrintWriter w;
        try {
            w = new PrintWriter(subServer.getOutputStream(), true);
            s = new Scanner(subServer.getInputStream());

            while(true) {
                String newCommand = s.nextLine();
                if (newCommand.equals("done")) {
//                    for (int i = 0; i < commands.size(); i++) {
//                        Server.subServerCommands.remove(commands.get(i));
//                    }
                    break;
                }
                commands.add(newCommand);
                Server.subServerCommands.put(newCommand, subServer);
            }
//            subServer.close();
        } catch (IOException e) {
            System.out.println("what tf happened");
        }
    }
}
