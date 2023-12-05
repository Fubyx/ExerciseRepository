package _5AT.TP.RMI_F.Square;

import _5AT.TP.RMI_F.Example.Text;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SquareClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the registry IP:");
        String ip = sc.nextLine();
        System.out.println("Enter the port:");
        int port = sc.nextInt();
        System.out.println("Enter the number to be squared:");
        long number = sc.nextLong();

        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            Square t_stub = (Square) registry.lookup("Square");
            long response = t_stub.square(number);
            System.out.println("Antwort vom Server: " + response);
        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }
    }
}
