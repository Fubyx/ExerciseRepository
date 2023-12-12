package _5AT.TP.RMI_F.Example;

import java.rmi.registry.*;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Text t_stub = (Text) registry.lookup("TextMessage");
            String response = t_stub.getText();
            System.out.println("Antwort vom Server: " + response);
        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }
    }
}