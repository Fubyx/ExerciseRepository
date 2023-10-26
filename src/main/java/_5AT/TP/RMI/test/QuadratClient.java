package _5AT.TP.RMI.test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class QuadratClient {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Zu welchen Server verbinden? (zB 127.0.0.1");
            String serverName = s.nextLine();
            System.out.println("Welche Zahl soll quadriert werden?");
            long number = s.nextLong();
            Registry registry = LocateRegistry.getRegistry(serverName, 30000);
            Quadrat t_stub = (Quadrat) registry.lookup("Quadrat");
            System.out.println("Antwort vom Server: " + t_stub.quadrat(number));

        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }

    }
}
