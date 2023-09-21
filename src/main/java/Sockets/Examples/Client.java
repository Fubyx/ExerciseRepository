package Sockets.Examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket;

        try {
            socket = new Socket("10.10.30.66", 50000);
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

        try {
            out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String text = in.nextLine();
        System.out.println(text);

    }
}
