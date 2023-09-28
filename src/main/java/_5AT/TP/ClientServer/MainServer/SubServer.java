package _5AT.TP.ClientServer.MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SubServer {
    public static void main(String[] args) {
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
        String[] commands = {"/isPrime", "done"};
        for (String s : commands) {
            out.println(s);
        }
        while (true) {
            String[] command = in.nextLine().split(" ", 2);
            switch (command[0]) {
                case "/isPrime" -> {
                    if (command.length < 2) {
                        out.println("also enter a number (example: /isPrime 5)");
                        break;
                    }
                    boolean val;
                    try {
                        val = isPrime(Long.parseLong(command[1]));
                    } catch (NumberFormatException e) {
                        val = false;
                    }
                    out.println(val);
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
