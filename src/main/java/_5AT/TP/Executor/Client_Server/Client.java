package _5AT.TP.Executor.Client_Server;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 50000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectOutputStream out;
        ObjectInputStream in;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.println("Input your next request:");
                String request = br.readLine();
                System.out.println(request);

                if (request.equals("end"))break;

                out.writeObject(request);

                System.out.println(in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            br.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
