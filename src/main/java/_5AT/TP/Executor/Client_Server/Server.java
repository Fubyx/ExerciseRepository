package _5AT.TP.Executor.Client_Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static boolean active = true;

    public static void main(String[] args) {
        ServerSocket serverSocket;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                serverSocket.close();
                executor.shutdown();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
