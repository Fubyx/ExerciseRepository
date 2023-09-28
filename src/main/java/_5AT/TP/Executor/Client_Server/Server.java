package _5AT.TP.Executor.Client_Server;

import TP.JUnitTesting.u2.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static boolean active = true;
    public static ServerSocket serverSocket;
    public static ArrayList<Userinformation> users = new ArrayList<>(List.of(new Userinformation[]{new Userinformation("admin", "admin", "admin")}));

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(50000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            while(active) {
                Socket connection = serverSocket.accept();
                if(!active)break;

                RequestHandler requestHandler = new RequestHandler(connection);
                executor.execute(requestHandler);
            }
        } catch (IOException ignored) {
        }
        finally {
            try {
                executor.shutdown();
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
