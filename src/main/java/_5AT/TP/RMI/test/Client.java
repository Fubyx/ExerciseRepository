package _5AT.TP.RMI.test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Zu welchen Server verbinden? (zB 127.0.0.1)"); //10.171.154.90
            String serverName = s.nextLine();
            System.out.println("Welche Zahl soll quadriert werden?");
            Long number = s.nextLong();
            Registry registry = LocateRegistry.getRegistry(serverName);
            TaskRemote stub = (TaskRemote) registry.lookup("GenericTask");

            Task myTask = new Task() {
                @Override
                public Object doTask(Object arg) {
                    long num = (long) arg;
                    return num * num;
                }
            };

            System.out.println("Antwort vom Server: " + (Long) (stub.doTask(number, myTask)));

        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }
    }
}
